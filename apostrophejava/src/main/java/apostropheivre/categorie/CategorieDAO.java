package apostropheivre.categorie;
import apostropheivre.ApostropheIvre;
import apostropheivre.dao.BDDservice;
import apostropheivre.dao.IDAO;
import apostropheivre.exception.DaoException;
import apostropheivre.exception.JsonException;
import apostropheivre.exception.ModelException;
import apostropheivre.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * [CategorieDAO] - class
 * @author Mathaus
 */
public class CategorieDAO {
	// Instance unique
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
		static {
			CategorieDAO.setForcerRequete(true);
			ApostropheIvre.CATEGORIES = CategorieDAO.listerCategories();
			CategorieDAO.setForcerRequete(false);
        }
	}
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IDAO<Categorie> {
		private static final Lock lock = new ReentrantLock();
		@Override
		public Categorie create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Categorie nouvelleCategorie;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Categorie.ValiderJson(json)) throw new JsonException(JsonException.JSON_INCORRECT); // validation  du json
				// creation d'une nouvelle Categorie
				nouvelleCategorie = new Categorie();
				nouvelleCategorie.setLibelle(json.getString(Categorie.JSON_LIBELLE));
				// creation de la requete
				Connection connectionBDD = BDDservice.getInstance().getConnection();
				PreparedStatement requetePreparee = CategorieRequetes.Insert(connectionBDD, nouvelleCategorie);
				int idNouvelleCategorie = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouvelleCategorie.setId(idNouvelleCategorie);
				// validation de la Categorie et mise en cache
				if (Categorie.ValiderCategorie(nouvelleCategorie)) {
					chargerCategories = true;  // sert a indiquer une modification de la base
					CATEGORIES.put(nouvelleCategorie.getId(), nouvelleCategorie); // permet d'eviter une requete findAll
					return nouvelleCategorie;
				} else throw new ModelException(ModelException.INSTANCE_INVALIDE);
			} catch (JSONException | SQLException | JsonException | ModelException e) {
				String message = "CategorieDAO.DAO.create() : " + e.getClass().getSimpleName();
				throw new DaoException(message);
			} finally { cloturerAccesBdd(); }
		}
		@Override
		public Categorie find(int id) throws DaoException {
			Categorie categorie = null;
			if (forcerRequete) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = BDDservice.getInstance().getConnection();
					PreparedStatement requetePreparee = CategorieRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						categorie = new Categorie();
						lireCategorie(reponse, categorie); // validation de la Categorie et mise en cache
					}
				} catch (SQLException | ModelException e) {
					String message = "CategorieDAO.DAO.find() : " + e.getClass().getSimpleName();
					throw new DaoException(message);
				} finally { cloturerAccesBdd(); }
			} else { categorie = CATEGORIES.get(id); } // depuis le cache CATEGORIES
			return categorie;
		}
		@Override
		public List<Categorie> findAll() throws DaoException {
			if (forcerRequete) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				CATEGORIES.clear(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = BDDservice.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(CategorieRequetes.SELECT_ALL);
					while (reponse.next()) {
						Categorie categorie = new Categorie();
						lireCategorie(reponse, categorie); // validation de la Categorie et mise en cache
					}
				} catch (SQLException | ModelException e) {
					String message = "CategorieDAO.DAO.findAll() : " + e.getClass().getSimpleName();
					throw new DaoException(message);
				} finally { cloturerAccesBdd(); }
			}
			return new ArrayList<>(CATEGORIES.values());
		}
		@Override
		public Categorie update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Categorie categorie;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Categorie.ValiderJson(json) && json.has(Categorie.JSON_ID)) throw new JsonException(JsonException.JSON_INCORRECT); // validation  du json
				// modification d'un Categorie
				categorie = CATEGORIES.get(json.getInt(Categorie.JSON_ID));
				categorie.setLibelle(json.getString(Categorie.JSON_LIBELLE));
				// validation de la Categorie et mise en cache
				if (Categorie.ValiderCategorie(categorie)) { CATEGORIES.put(categorie.getId(), categorie);
				} else throw new ModelException(ModelException.INSTANCE_INVALIDE);
				// Requete// creation de la requete
				Connection connectionBDD = BDDservice.getInstance().getConnection();
				PreparedStatement requetePreparee = CategorieRequetes.Update(connectionBDD, categorie);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException(DaoException.DONNEE_INVALIDE); } // verifie l'impact de la modification
				else { chargerCategories = true; } // sert en cas de nouyvelle modification de la base
			} catch (JSONException | SQLException | JsonException | ModelException e) {
				String message = "CategorieDAO.DAO.update() : " + e.getClass().getSimpleName();
				throw new DaoException(message);
			} finally { cloturerAccesBdd(); }
			return categorie;
		}
		@Override
		public boolean delete(int id) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Categorie categorie = CATEGORIES.get(id);
			int lignesSupprimees;
			if (categorie == null) return false;
			else {
				try {
					Connection connectionBDD = BDDservice.getInstance().getConnection();
					PreparedStatement requetePreparee = CategorieRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					String message = "CategorieDAO.DAO.delete() : " + e.getClass().getSimpleName();
					throw new DaoException(message);
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					CATEGORIES.remove(id);
					return true;
				}
				else return false;
			}
		}
		private void lireCategorie(ResultSet reponse, Categorie categorie) throws SQLException, ModelException {
			categorie.setId(reponse.getInt(CategorieRequetes.CHAMPS_ID));
			categorie.setLibelle(reponse.getString(CategorieRequetes.CHAMPS_LIBELLE));
			if (Categorie.ValiderCategorie(categorie)) { CATEGORIES.put(categorie.getId(), categorie); }
			else { throw new ModelException(ModelException.INSTANCE_INVALIDE); }
		}
		private void cloturerAccesBdd() {
			if (!maintenirConnection) BDDservice.getInstance().closeConnection(); // pour les requetes multiples, ou fermer la connection
			lock.unlock(); // Liberation du verrou pour permettre d'autres accès
		}

	}
	// Constructeur privé pour empêcher l'instanciation
	private CategorieDAO() {}
	private final static Map<Integer, Categorie> CATEGORIES = new ConcurrentHashMap<>();
	private static boolean maintenirConnection = false;
	private static boolean forcerRequete = false;
	private static boolean chargerCategories = true;
	//
	public static Categorie creerCategorie(String fromJson) {
        try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
    }
	public static Categorie recipererCategorie(int id) {
        try { return dao().find(id); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
    }
	public static List<Categorie> listerCategories() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Categorie mettreAJourCategorie(String fromJson) {
		try { return dao().update(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static boolean supprimerCategorie(int id) {
		try { return dao().delete(id); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return false;
	}
	//
	public static boolean isMaintenirConnection() {
		return maintenirConnection;
	}
	public static void setMaintenirConnection(boolean maintenirConnection) {
		CategorieDAO.maintenirConnection = maintenirConnection;
	}
	public static boolean isForcerRequete() {
		return forcerRequete;
	}
	public static void setForcerRequete(boolean forcerRequete) {
		CategorieDAO.forcerRequete = forcerRequete;
	}
	//
}