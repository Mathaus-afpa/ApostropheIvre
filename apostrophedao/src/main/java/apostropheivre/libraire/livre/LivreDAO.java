package apostropheivre.libraire.livre;
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
 * [LivreDAO] - class
 * @author Mathaus
 */
public class LivreDAO {
	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="InnerDAO">
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Livre> {
		private static final Lock lock = new ReentrantLock();
		//<editor-fold defaultstate="expanded" desc="CREATE">
		@Override
		public Livre create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Livre nouvelLivre;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Livre.ValiderJson(json)) throw new JsonException("IApostropheInnerDAO<Livre>.create"); // validation  du json
				// creation d'un nouvel Livre
				nouvelLivre = new Livre();
				nouvelLivre.setTitre(json.getString(Livre.JSON_TITRE));
				nouvelLivre.setResume(json.getString(Livre.JSON_RESUME));
				nouvelLivre.setImage(json.getString(Livre.JSON_IMAGE));
				nouvelLivre.setIsbn(json.getString(Livre.JSON_ISBN));
				nouvelLivre.setQuantite(json.getInt(Livre.JSON_QUANTITE));
				nouvelLivre.setCategorie(json.getInt(Livre.JSON_CATEGORIE));
				nouvelLivre.setAuteur(json.getInt(Livre.JSON_AUTEUR));
				// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = LivreRequetes.Insert(connectionBDD, nouvelLivre);
				int idNouvelLivre = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouvelLivre.setId(idNouvelLivre);
				// validation de l'Livre et mise en cache
				if (Livre.ValiderLivre(nouvelLivre)) {
					Cache.ajouter(nouvelLivre);
					return nouvelLivre;
				} else throw new ModelException("IApostropheInnerDAO<Livre>.create", ModelException.LIVRE);
			} catch (JSONException | SQLException | JsonException | ModelException | RegExException | NullDataException e) {
				throw new DaoException("IApostropheInnerDAO<Livre>.create", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND">
		@Override
		public Livre find(int id) throws DaoException, NullDataException {
			Livre livre = null;
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = LivreRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						livre = new Livre();
						lireLivre(reponse, livre); // validation de l'Livre et mise en cache
					}
				} catch (SQLException | ModelException | RegExException e) {
					throw new DaoException("IApostropheInnerDAO<Livre>.find", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			} else { livre = Cache.getLivre(id); } // depuis le cache LIVRES
			return livre;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND ALL">
		@Override
		public List<Livre> findAll() throws DaoException {
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				Cache.clearLivres(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(LivreRequetes.SELECT_ALL);
					while (reponse.next()) {
						Livre livre = new Livre();
						lireLivre(reponse, livre); // validation de l'Livre et mise en cache
					}
				} catch (SQLException | ModelException | RegExException | NullDataException e) {
					throw new DaoException("IApostropheInnerDAO<Livre>.findAll", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			}
			return Cache.listerLivres();
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="UPDATE">
		@Override
		public Livre update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Livre livre;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Livre.ValiderJson(json) && json.has(Livre.JSON_ID)) throw new JsonException("IApostropheInnerDAO<Livre>.update"); // validation  du json
				// modification d'un Livre
				livre = Cache.getLivre(json.getInt(Livre.JSON_ID));
				livre.setTitre(json.getString(Livre.JSON_TITRE));
				livre.setResume(json.getString(Livre.JSON_RESUME));
				livre.setImage(json.getString(Livre.JSON_IMAGE));
				livre.setIsbn(json.getString(Livre.JSON_ISBN));
				livre.setQuantite(json.getInt(Livre.JSON_QUANTITE));
				livre.setCategorie(json.getInt(Livre.JSON_CATEGORIE));
				livre.setAuteur(json.getInt(Livre.JSON_AUTEUR));
				// validation de l'Livre et mise en cache
				if (Livre.ValiderLivre(livre)) {
					Cache.ajouter(livre);
				} else throw new ModelException("IApostropheInnerDAO<Livre>.update", ModelException.LIVRE);
				// Requete// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = LivreRequetes.Update(connectionBDD, livre);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException("IApostropheInnerDAO<Livre>.update", "lignes affectee : " + lignesModifiees); } // verifie l'impact de la modification
			} catch (JSONException | SQLException | JsonException | ModelException | NullDataException | RegExException e) {
				throw new DaoException("IApostropheInnerDAO<Livre>.update", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
			return livre;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="DELETE">
		@Override
		public boolean delete(int id) throws DaoException, NullDataException, ModelException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Livre livre = Cache.getLivre(id);
			int lignesSupprimees;
			if (livre == null) return false; //todo: redondant ?
			else {
				try {
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = LivreRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("IApostropheInnerDAO<Livre>.delete", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					Cache.retirer(livre);
					return true;
				}
				else return false;
			}
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="Factorisations">
		private void lireLivre(ResultSet reponse, Livre livre) throws SQLException, ModelException, RegExException, NullDataException {
			livre.setId(reponse.getInt(LivreRequetes.CHAMPS_ID));
			livre.setTitre(reponse.getString(LivreRequetes.CHAMPS_TITRE));
			livre.setResume(reponse.getString(LivreRequetes.CHAMPS_RESUME));
			livre.setImage(reponse.getString(LivreRequetes.CHAMPS_IMAGE));
			livre.setIsbn(reponse.getString(LivreRequetes.CHAMPS_ISBN));
			livre.setQuantite(reponse.getInt(LivreRequetes.CHAMPS_QUANTITE));
			livre.setCategorie(reponse.getInt(LivreRequetes.CHAMPS_CATEGORIE));
			livre.setAuteur(reponse.getInt(LivreRequetes.CHAMPS_AUTEUR));
			if (Livre.ValiderLivre(livre)) { Cache.ajouter(livre); }
			else { throw new ModelException("IApostropheInnerDAO<Livre>.lireLivre", ModelException.LIVRE); }
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
	private LivreDAO() {}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="CRUD">
	public static Livre creer(String fromJson) {
		try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Livre rechercherParId(int id) {
		try { return dao().find(id); }
		catch (DaoException | NullDataException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static List<Livre> rechercherTout() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public  static Livre modifier(String fromJson) {
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