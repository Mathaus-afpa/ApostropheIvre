package apostropheivre.libraire.auteur;
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
 * [AuteurDAO] - class
 * @author Mathaus
 */
public class AuteurDAO {
	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="InnerDAO">
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Auteur> {
		private static final Lock lock = new ReentrantLock();
		//<editor-fold defaultstate="expanded" desc="CREATE">
		@Override
		public Auteur create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Auteur nouvelAuteur;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Auteur.ValiderJson(json)) throw new JsonException("IApostropheInnerDAO<Auteur>.create"); // validation  du json
				// creation d'un nouvel Auteur
				nouvelAuteur = new Auteur();
				nouvelAuteur.setNom(json.getString(Auteur.JSON_NOM));
				nouvelAuteur.setPrenom(json.getString(Auteur.JSON_PRENOM));
				nouvelAuteur.setUrl(json.getString(Auteur.JSON_URL));
				// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = AuteurRequetes.Insert(connectionBDD, nouvelAuteur);
				int idNouvelAuteur = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouvelAuteur.setId(idNouvelAuteur);
				// validation de l'Auteur et mise en cache
				if (Auteur.ValiderAuteur(nouvelAuteur)) {
					Cache.ajouter(nouvelAuteur);
					return nouvelAuteur;
				} else throw new ModelException("IApostropheInnerDAO<Auteur>.create", ModelException.AUTEUR);
			} catch (JSONException | SQLException | JsonException | ModelException | RegExException | NullDataException e) {
				throw new DaoException("IApostropheInnerDAO<Auteur>.create", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND">
		@Override
		public Auteur find(int id) throws DaoException, NullDataException {
			Auteur auteur = null;
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = AuteurRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						auteur = new Auteur();
						lireAuteur(reponse, auteur); // validation de l'Auteur et mise en cache
					}
				} catch (SQLException | ModelException | RegExException e) {
					throw new DaoException("IApostropheInnerDAO<Auteur>.find", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			} else { auteur = Cache.getAuteur(id); } // depuis le cache AUTEURS
			return auteur;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND ALL">
		@Override
		public List<Auteur> findAll() throws DaoException {
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				Cache.clearAuteurs(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(AuteurRequetes.SELECT_ALL);
					while (reponse.next()) {
						Auteur auteur = new Auteur();
						lireAuteur(reponse, auteur); // validation de l'Auteur et mise en cache
					}
				} catch (SQLException | ModelException | RegExException | NullDataException e) {
					throw new DaoException("IApostropheInnerDAO<Auteur>.findAll", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			}
			return Cache.listerAuteurs();
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="UPDATE">
		@Override
		public Auteur update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Auteur auteur;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Auteur.ValiderJson(json) && json.has(Auteur.JSON_ID)) throw new JsonException("IApostropheInnerDAO<Auteur>.update"); // validation  du json
				// modification d'un Auteur
				auteur = Cache.getAuteur(json.getInt(Auteur.JSON_ID));
				auteur.setNom(json.getString(Auteur.JSON_NOM));
				auteur.setPrenom(json.getString(Auteur.JSON_PRENOM));
				auteur.setUrl(json.getString(Auteur.JSON_URL));
				// validation de l'Auteur et mise en cache
				if (Auteur.ValiderAuteur(auteur)) {
					Cache.ajouter(auteur);
				} else throw new ModelException("IApostropheInnerDAO<Auteur>.update", ModelException.AUTEUR);
				// Requete// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = AuteurRequetes.Update(connectionBDD, auteur);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException("IApostropheInnerDAO<Auteur>.update", "lignes affectee : " + lignesModifiees); } // verifie l'impact de la modification
			} catch (JSONException | SQLException | JsonException | ModelException | NullDataException | RegExException e) {
				throw new DaoException("IApostropheInnerDAO<Auteur>.update", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
			return auteur;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="DELETE">
		@Override
		public boolean delete(int id) throws DaoException, NullDataException, ModelException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Auteur auteur = Cache.getAuteur(id);
			int lignesSupprimees;
			if (auteur == null) return false; //todo: redondant ?
			else {
				try {
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = AuteurRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("IApostropheInnerDAO<Auteur>.delete", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					Cache.retirer(auteur);
					return true;
				}
				else return false;
			}
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="Factorisations">
		private void lireAuteur(ResultSet reponse, Auteur auteur) throws SQLException, ModelException, RegExException, NullDataException {
			auteur.setId(reponse.getInt(AuteurRequetes.CHAMPS_ID));
			auteur.setNom(reponse.getString(AuteurRequetes.CHAMPS_NOM));
			auteur.setPrenom(reponse.getString(AuteurRequetes.CHAMPS_PRENOM));
			auteur.setUrl(reponse.getString(AuteurRequetes.CHAMPS_URL));
			if (Auteur.ValiderAuteur(auteur)) { Cache.ajouter(auteur); }
			else { throw new ModelException("IApostropheInnerDAO<Auteur>.lireAuteur", ModelException.AUTEUR); }
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
	private AuteurDAO() {}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="CRUD">
	public static Auteur creer(String fromJson) {
		try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Auteur rechercherParId(int id) {
		try { return dao().find(id); }
		catch (DaoException | NullDataException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static List<Auteur> rechercherTout() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public  static Auteur modifier(String fromJson) {
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