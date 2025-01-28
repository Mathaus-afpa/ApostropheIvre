package apostropheivre.administrateur.compte;
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
 * [CompteDAO] - class
 * @author Mathaus
 */
public class CompteDAO {
	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="InnerDAO">
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Compte> {
		private static final Lock lock = new ReentrantLock();
		//<editor-fold defaultstate="expanded" desc="CREATE">
		@Override
		public Compte create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Compte nouveauCompte;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Compte.ValiderJson(json)) throw new JsonException("IApostropheInnerDAO<Compte>.create"); // validation  du json
				// creation d'un nouvel Compte
				nouveauCompte = new Compte();
				nouveauCompte.setLogin(json.getString(Compte.JSON_LOGIN));
				// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = CompteRequetes.Insert(connectionBDD, nouveauCompte);
				int idNouvelCompte = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouveauCompte.setId(idNouvelCompte);
				// validation de l'Compte et mise en cache
				if (Compte.ValiderCompte(nouveauCompte)) {
					Cache.ajouter(nouveauCompte);
					return nouveauCompte;
				} else throw new ModelException("IApostropheInnerDAO<Compte>.create", ModelException.COMPTE);
			} catch (JSONException | SQLException | JsonException | ModelException | RegExException |
					 NullDataException e) {
				throw new DaoException("IApostropheInnerDAO<Compte>.create", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND">
		@Override
		public Compte find(int id) throws DaoException, NullDataException {
			Compte compte = null;
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = CompteRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						compte = new Compte();
						lireCompte(reponse, compte); // validation de l'Compte et mise en cache
					}
				} catch (SQLException | ModelException | RegExException e) {
					throw new DaoException("IApostropheInnerDAO<Compte>.find", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			} else { compte = Cache.getCompte(id); } // depuis le cache COMPTES
			return compte;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND ALL">
		@Override
		public List<Compte> findAll() throws DaoException {
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				Cache.clearComptes(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(CompteRequetes.SELECT_ALL);
					while (reponse.next()) {
						Compte compte = new Compte();
						lireCompte(reponse, compte); // validation de l'Compte et mise en cache
					}
				} catch (SQLException | ModelException | RegExException | NullDataException e) {
					throw new DaoException("IApostropheInnerDAO<Compte>.findAll", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			}
			return Cache.listerComptes();
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="UPDATE">
		@Override
		public Compte update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Compte compte;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Compte.ValiderJson(json) && json.has(Compte.JSON_ID)) throw new JsonException("IApostropheInnerDAO<Compte>.update"); // validation  du json
				// modification d'un Compte
				compte = Cache.getCompte(json.getInt(Compte.JSON_ID));
				compte.setLogin(json.getString(Compte.JSON_LOGIN));
				// validation de l'Compte et mise en cache
				if (Compte.ValiderCompte(compte)) {
					Cache.ajouter(compte);
				} else throw new ModelException("IApostropheInnerDAO<Compte>.update", ModelException.COMPTE);
				// Requete// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = CompteRequetes.Update(connectionBDD, compte);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException("IApostropheInnerDAO<Compte>.update", "lignes affectee : " + lignesModifiees); } // verifie l'impact de la modification
			} catch (JSONException | SQLException | JsonException | ModelException | NullDataException | RegExException e) {
				throw new DaoException("IApostropheInnerDAO<Compte>.update", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
			return compte;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="DELETE">
		@Override
		public boolean delete(int id) throws DaoException, NullDataException, ModelException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Compte compte = Cache.getCompte(id);
			int lignesSupprimees;
			if (compte == null) return false; //todo: redondant ?
			else {
				try {
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = CompteRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("IApostropheInnerDAO<Compte>.delete", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					Cache.retirer(compte);
					return true;
				}
				else return false;
			}
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="Factorisations">
		private void lireCompte(ResultSet reponse, Compte compte) throws SQLException, ModelException, RegExException, NullDataException {
			compte.setId(reponse.getInt(CompteRequetes.CHAMPS_ID));
			compte.setLogin(reponse.getString(CompteRequetes.CHAMPS_LOGIN));
			if (Compte.ValiderCompte(compte)) { Cache.ajouter(compte); }
			else { throw new ModelException("IApostropheInnerDAO<Compte>.lireCompte", ModelException.COMPTE); }
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
	private CompteDAO() {}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="CRUD">
	public static Compte creer(String fromJson) {
		try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Compte rechercherParId(int id) {
		try { return dao().find(id); }
		catch (DaoException | NullDataException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static List<Compte> rechercherTout() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public  static Compte modifier(String fromJson) {
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