package apostropheivre.libraire.categorie;
import apostropheivre.Cache;
import apostropheivre.exceptions.*;
import apostropheivre.services.DataDB;
import apostropheivre.services.IApostropheInnerDAO;
import apostropheivre.utilitaires.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * [CategorieDAO] - class
 * @author Mathaus
 */
public class CategorieDAO {
	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="InnerDAO">
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Categorie> {
		private static final Lock lock = new ReentrantLock();
		//<editor-fold defaultstate="expanded" desc="CREATE">
		@Override
		public Categorie create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Categorie nouvelleCategorie;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Categorie.ValiderJson(json)) throw new JsonException("IApostropheInnerDAO<Categorie>.create"); // validation  du json
				// creation d'un nouvel Categorie
				nouvelleCategorie = new Categorie();
				nouvelleCategorie.setLibelle(json.getString(Categorie.JSON_LIBELLE));
				// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = CategorieRequetes.Insert(connectionBDD, nouvelleCategorie);
				int idNouvelCategorie = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouvelleCategorie.setId(idNouvelCategorie);
				// validation de l'Categorie et mise en cache
				if (Categorie.ValiderCategorie(nouvelleCategorie)) {
					Cache.ajouter(nouvelleCategorie);
					return nouvelleCategorie;
				} else throw new ModelException("IApostropheInnerDAO<Categorie>.create", ModelException.CATEGORIE);
			} catch (JSONException | SQLException | JsonException | ModelException | RegExException | NullDataException e) {
				throw new DaoException("IApostropheInnerDAO<Categorie>.create", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND">
		@Override
		public Categorie find(int id) throws DaoException, NullDataException {
			Categorie categorie = null;
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = CategorieRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						categorie = new Categorie();
						lireCategorie(reponse, categorie); // validation de l'Categorie et mise en cache
					}
				} catch (SQLException | ModelException | RegExException e) {
					throw new DaoException("IApostropheInnerDAO<Categorie>.find", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			} else { categorie = Cache.getCategorie(id); } // depuis le cache CATEGORIES
			return categorie;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND ALL">
		@Override
		public List<Categorie> findAll() throws DaoException {
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				Cache.clearCategories(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(CategorieRequetes.SELECT_ALL);
					while (reponse.next()) {
						Categorie categorie = new Categorie();
						lireCategorie(reponse, categorie); // validation de l'Categorie et mise en cache
					}
				} catch (SQLException | ModelException | RegExException | NullDataException e) {
					throw new DaoException("IApostropheInnerDAO<Categorie>.findAll", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			}
			return Cache.listerCategories();
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="UPDATE">
		@Override
		public Categorie update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Categorie categorie;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Categorie.ValiderJson(json) && json.has(Categorie.JSON_ID)) throw new JsonException("IApostropheInnerDAO<Categorie>.update"); // validation  du json
				// modification d'un Categorie
				categorie = Cache.getCategorie(json.getInt(Categorie.JSON_ID));
				categorie.setLibelle(json.getString(Categorie.JSON_LIBELLE));
				// validation de l'Categorie et mise en cache
				if (Categorie.ValiderCategorie(categorie)) {
					Cache.ajouter(categorie);
				} else throw new ModelException("IApostropheInnerDAO<Categorie>.update", ModelException.CATEGORIE);
				// Requete// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = CategorieRequetes.Update(connectionBDD, categorie);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException("IApostropheInnerDAO<Categorie>.update", "lignes affectee : " + lignesModifiees); } // verifie l'impact de la modification
			} catch (JSONException | SQLException | JsonException | ModelException | NullDataException | RegExException e) {
				throw new DaoException("IApostropheInnerDAO<Categorie>.update", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
			return categorie;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="DELETE">
		@Override
		public boolean delete(int id) throws DaoException, NullDataException, ModelException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Categorie categorie = Cache.getCategorie(id);
			int lignesSupprimees;
			if (categorie == null) return false; //todo: redondant ?
			else {
				try {
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = CategorieRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("IApostropheInnerDAO<Categorie>.delete", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					Cache.retirer(categorie);
					return true;
				}
				else return false;
			}
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="Factorisations">
		private void lireCategorie(ResultSet reponse, Categorie categorie) throws SQLException, ModelException, RegExException, NullDataException {
			categorie.setId(reponse.getInt(CategorieRequetes.CHAMPS_ID));
			categorie.setLibelle(reponse.getString(CategorieRequetes.CHAMPS_LIBELLE));
			if (Categorie.ValiderCategorie(categorie)) { Cache.ajouter(categorie); }
			else { throw new ModelException("IApostropheInnerDAO<Categorie>.lireCategorie", ModelException.CATEGORIE); }
		}
		private void cloturerAccesBdd() {
			if (!DataDB.isMaintenirConnection()) DataDB.getInstance().closeConnection(); // pour les requetes multiples, ou fermer la connection
			lock.unlock(); // Liberation du verrou pour permettre d'autres accès
		}
		//</editor-fold>
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DAO">
	// Constructeur privé pour empêcher l'instanciation
	private CategorieDAO() {}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="CRUD">
	public static Categorie creer(String fromJson) {
		try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Categorie rechercherParId(int id) {
		try { return dao().find(id); }
		catch (DaoException | NullDataException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static List<Categorie> rechercherTout() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public  static Categorie modifier(String fromJson) {
		try { return dao().update(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static boolean supprimer(int id) {
		try { return dao().delete(id); }
		catch (DaoException | NullDataException | ModelException e) {  Log.error(e.getMessage(), e.getCause()); }
		return false;
	}
	//</editor-fold>
}