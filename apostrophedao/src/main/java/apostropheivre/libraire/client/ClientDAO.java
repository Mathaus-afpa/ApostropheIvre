package apostropheivre.libraire.client;
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
 * [ClientDAO] - class
 * @author Mathaus
 */
public class ClientDAO {
	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="InnerDAO">
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Client> {
		private static final Lock lock = new ReentrantLock();
		//<editor-fold defaultstate="expanded" desc="CREATE">
		@Override
		public Client create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Client nouveauClient;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Client.ValiderJson(json)) throw new JsonException("IApostropheInnerDAO<Client>.create"); // validation  du json
				// creation d'un nouvel Client
				nouveauClient = new Client();
				nouveauClient.setNom(json.getString(Client.JSON_NOM));
				nouveauClient.setPrenom(json.getString(Client.JSON_PRENOM));
				nouveauClient.setEmail(json.getString(Client.JSON_EMAIL));
				nouveauClient.setAdresse(json.getString(Client.JSON_ADRESSE));
				nouveauClient.setCodePostal(json.getString(Client.JSON_CODE_POSTAL));
				nouveauClient.setVille(json.getString(Client.JSON_VILLE));
				// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = ClientRequetes.Insert(connectionBDD, nouveauClient);
				int idNouvelClient = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouveauClient.setId(idNouvelClient);
				// validation de l'Client et mise en cache
				if (Client.ValiderClient(nouveauClient)) {
					Cache.ajouter(nouveauClient);
					return nouveauClient;
				} else throw new ModelException("IApostropheInnerDAO<Client>.create", ModelException.CLIENT);
			} catch (JSONException | SQLException | JsonException | ModelException | RegExException | NullDataException e) {
				throw new DaoException("IApostropheInnerDAO<Client>.create", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND">
		@Override
		public Client find(int id) throws DaoException, NullDataException {
			Client client = null;
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = ClientRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						client = new Client();
						lireClient(reponse, client); // validation de l'Client et mise en cache
					}
				} catch (SQLException | ModelException | RegExException e) {
					throw new DaoException("IApostropheInnerDAO<Client>.find", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			} else { client = Cache.getClient(id); } // depuis le cache CLIENTS
			return client;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="FIND ALL">
		@Override
		public List<Client> findAll() throws DaoException {
			if (DataDB.isForcerRequete()) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				Cache.clearClients(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = DataDB.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(ClientRequetes.SELECT_ALL);
					while (reponse.next()) {
						Client client = new Client();
						lireClient(reponse, client); // validation de l'Client et mise en cache
					}
				} catch (SQLException | ModelException | RegExException | NullDataException e) {
					throw new DaoException("IApostropheInnerDAO<Client>.findAll", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
			}
			return Cache.listerClients();
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="UPDATE">
		@Override
		public Client update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Client client;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Client.ValiderJson(json) && json.has(Client.JSON_ID)) throw new JsonException("IApostropheInnerDAO<Client>.update"); // validation  du json
				// modification d'un Client
				client = Cache.getClient(json.getInt(Client.JSON_ID));
				client.setNom(json.getString(Client.JSON_NOM));
				client.setPrenom(json.getString(Client.JSON_PRENOM));
				client.setEmail(json.getString(Client.JSON_EMAIL));
				client.setAdresse(json.getString(Client.JSON_ADRESSE));
				client.setCodePostal(json.getString(Client.JSON_CODE_POSTAL));
				client.setVille(json.getString(Client.JSON_VILLE));
				// validation de l'Client et mise en cache
				if (Client.ValiderClient(client)) {
					Cache.ajouter(client);
				} else throw new ModelException("IApostropheInnerDAO<Client>.update", ModelException.CLIENT);
				// Requete// creation de la requete
				Connection connectionBDD = DataDB.getInstance().getConnection();
				PreparedStatement requetePreparee = ClientRequetes.Update(connectionBDD, client);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException("IApostropheInnerDAO<Client>.update", "lignes affectee : " + lignesModifiees); } // verifie l'impact de la modification
			} catch (JSONException | SQLException | JsonException | ModelException | NullDataException | RegExException e) {
				throw new DaoException("IApostropheInnerDAO<Client>.update", e.getClass().getSimpleName());
			} finally { cloturerAccesBdd(); }
			return client;
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="DELETE">
		@Override
		public boolean delete(int id) throws DaoException, NullDataException, ModelException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Client client = Cache.getClient(id);
			int lignesSupprimees;
			if (client == null) return false; //todo: redondant ?
			else {
				try {
					Connection connectionBDD = DataDB.getInstance().getConnection();
					PreparedStatement requetePreparee = ClientRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("IApostropheInnerDAO<Client>.delete", e.getClass().getSimpleName());
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					Cache.retirer(client);
					return true;
				}
				else return false;
			}
		}
		//</editor-fold>
		//<editor-fold defaultstate="expanded" desc="Factorisations">
		private void lireClient(ResultSet reponse, Client client) throws SQLException, ModelException, RegExException, NullDataException {
			client.setId(reponse.getInt(ClientRequetes.CHAMPS_ID));
			client.setNom(reponse.getString(ClientRequetes.CHAMPS_NOM));
			client.setPrenom(reponse.getString(ClientRequetes.CHAMPS_PRENOM));
			client.setEmail(reponse.getString(ClientRequetes.CHAMPS_EMAIL));
			client.setAdresse(reponse.getString(ClientRequetes.CHAMPS_ADRESSE));
			client.setCodePostal(reponse.getString(ClientRequetes.CHAMPS_CODE_POSTAL));
			client.setVille(reponse.getString(ClientRequetes.CHAMPS_VILLE));
			if (Client.ValiderClient(client)) { Cache.ajouter(client); }
			else { throw new ModelException("IApostropheInnerDAO<Client>.lireClient", ModelException.CLIENT); }
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
	private ClientDAO() {}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="CRUD">
	public static Client creer(String fromJson) {
		try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Client rechercherParId(int id) {
		try { return dao().find(id); }
		catch (DaoException | NullDataException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static List<Client> rechercherTout() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public  static Client modifier(String fromJson) {
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