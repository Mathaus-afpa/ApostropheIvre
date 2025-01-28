package apostropheivre.administrateur.libraire;
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
 * [LibraireDAO] - class
 * @author Mathaus
 */
public class LibraireDAO {
	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="InnerDAO">
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Libraire> {
		private static final Lock lock = new ReentrantLock();
		//<editor-fold defaultstate="expanded" desc="CREATE">
		@Override
		public Libraire create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Libraire nouvelLibraire;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Libraire.ValiderJson(json)) throw new JsonException("IApostropheInnerDAO<Libraire>.create"); // validation  du json
				// creation d'un nouvel Libraire
				nouvelLibraire = new Libraire();
				nouvelLibraire.setNom(json.getString(Libraire.JSON_NOM));
				nouvelLibraire.setPrenom(json.getString(Libraire.JSON_PRENOM));
				// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = LibraireRequetes.Insert(connectionBDD, nouvelLibraire);
				int idNouvelLibraire = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouvelLibraire.setId(idNouvelLibraire);
				// validation de l'Libraire et mise en cache
				if (Libraire.ValiderLibraire(nouvelLibraire)) {
					Cache.ajouter(nouvelLibraire);
					return nouvelLibraire;
				} else throw new ModelException("IApostropheInnerDAO<Libraire>.create", ModelException.LIBRAIRE);
			} catch (JSONException | SQLException | JsonException | ModelException | RegExException | NullDataException e) {
				throw new DaoException("IApostropheInnerDAO<Libraire>.create", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND">
		@Override
		public Libraire find(int id) throws DaoException, NullDataException {
			Libraire libraire = null;
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = LibraireRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						libraire = new Libraire();
						lireLibraire(reponse, libraire); // validation de l'Libraire et mise en cache
					}
				} catch (SQLException | ModelException | RegExException e) {
					throw new DaoException("IApostropheInnerDAO<Libraire>.find", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			} else { libraire = Cache.getLibraire(id); } // depuis le cache LIBRAIRES
			return libraire;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND ALL">
		@Override
		public List<Libraire> findAll() throws DaoException {
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				Cache.clearLibraires(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(LibraireRequetes.SELECT_ALL);
					while (reponse.next()) {
						Libraire libraire = new Libraire();
						lireLibraire(reponse, libraire); // validation de l'Libraire et mise en cache
					}
				} catch (SQLException | ModelException | RegExException | NullDataException e) {
					throw new DaoException("IApostropheInnerDAO<Libraire>.findAll", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			}
			return Cache.listerLibraires();
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="UPDATE">
		@Override
		public Libraire update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Libraire libraire;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Libraire.ValiderJson(json) && json.has(Libraire.JSON_ID)) throw new JsonException("IApostropheInnerDAO<Libraire>.update"); // validation  du json
				// modification d'un Libraire
				libraire = Cache.getLibraire(json.getInt(Libraire.JSON_ID));
				libraire.setNom(json.getString(Libraire.JSON_NOM));
				libraire.setPrenom(json.getString(Libraire.JSON_PRENOM));
				// validation de l'Libraire et mise en cache
				if (Libraire.ValiderLibraire(libraire)) {
					Cache.ajouter(libraire);
				} else throw new ModelException("IApostropheInnerDAO<Libraire>.update", ModelException.LIBRAIRE);
				// Requete// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = LibraireRequetes.Update(connectionBDD, libraire);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException("IApostropheInnerDAO<Libraire>.update", "lignes affectee : " + lignesModifiees); } // verifie l'impact de la modification
			} catch (JSONException | SQLException | JsonException | ModelException | NullDataException | RegExException e) {
				throw new DaoException("IApostropheInnerDAO<Libraire>.update", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
			return libraire;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="DELETE">
		@Override
		public boolean delete(int id) throws DaoException, NullDataException, ModelException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Libraire libraire = Cache.getLibraire(id);
			int lignesSupprimees;
			if (libraire == null) return false; //todo: redondant ?
			else {
				try {
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = LibraireRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("IApostropheInnerDAO<Libraire>.delete", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					Cache.retirer(libraire);
					return true;
				}
				else return false;
			}
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="Factorisations">
		private void lireLibraire(ResultSet reponse, Libraire libraire) throws SQLException, ModelException, RegExException, NullDataException {
			libraire.setId(reponse.getInt(LibraireRequetes.CHAMPS_ID));
			libraire.setNom(reponse.getString(LibraireRequetes.CHAMPS_NOM));
			libraire.setPrenom(reponse.getString(LibraireRequetes.CHAMPS_PRENOM));
			if (Libraire.ValiderLibraire(libraire)) { Cache.ajouter(libraire); }
			else { throw new ModelException("IApostropheInnerDAO<Libraire>.lireLibraire", ModelException.LIBRAIRE); }
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
	private LibraireDAO() {}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="CRUD">
	public static Libraire creer(String fromJson) {
		try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Libraire rechercherParId(int id) {
		try { return dao().find(id); }
		catch (DaoException | NullDataException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static List<Libraire> rechercherTout() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public  static Libraire modifier(String fromJson) {
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