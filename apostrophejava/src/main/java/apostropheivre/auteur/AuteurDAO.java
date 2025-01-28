package apostropheivre.auteur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import apostropheivre.ApostropheIvre;
import apostropheivre.dao.BDDservice;
import apostropheivre.dao.IDAO;
import apostropheivre.exception.DaoException;
import apostropheivre.exception.JsonException;
import apostropheivre.exception.ModelException;
import apostropheivre.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * [Auteur.Auteur.AuteurDAO] - class
 * @author Mathaus
 */
public class AuteurDAO {
	// Instance unique
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IDAO<Auteur> {
		private static final Lock lock = new ReentrantLock();
		@Override
		public Auteur create(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Auteur nouvelAuteur;
			try {
				JSONObject json = new JSONObject(fromJson); // conversion en json
				if (!Auteur.ValiderJson(json)) throw new JsonException(JsonException.JSON_INCORRECT); // validation  du json
				// creation d'un nouvel Auteur
				nouvelAuteur = new Auteur();
				nouvelAuteur.setNom(json.getString(Auteur.JSON_NOM));
				nouvelAuteur.setPrenom(json.getString(Auteur.JSON_PRENOM));
				nouvelAuteur.setUrl(json.getString(Auteur.JSON_URL));
				// creation de la requete
				Connection connectionBDD = BDDservice.getInstance().getConnection();
				PreparedStatement requetePreparee = AuteurRequetes.Insert(connectionBDD, nouvelAuteur);
				int idNouvelAuteur = requetePreparee.executeUpdate();
				// attribution de l'ID genere
				nouvelAuteur.setId(idNouvelAuteur);
				// validation de l'Auteur et mise en cache
				if (Auteur.ValiderAuteur(nouvelAuteur)) {
					chargerAuteurs = true;  // sert a indiquer une modification de la base
					AUTEURS.put(nouvelAuteur.getId(), nouvelAuteur); // permet d'eviter une requete findAll
					return nouvelAuteur;
				} else throw new ModelException(ModelException.INSTANCE_INVALIDE);
			} catch (JSONException | SQLException | JsonException | ModelException e) {
				String message = "AuteurDAO.DAO.create() : " + e.getClass().getSimpleName();
				throw new DaoException(message);
			} finally { cloturerAccesBdd(); }
		}
		@Override
		public Auteur find(int id) throws DaoException {
			Auteur auteur = null;
			if (forcerRequete) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				try {
					// creation de la requete
					Connection connectionBDD = BDDservice.getInstance().getConnection();
					PreparedStatement requetePreparee = AuteurRequetes.SelectById(connectionBDD, id);
					ResultSet reponse = requetePreparee.executeQuery();
					if (reponse.next()) {
						auteur = new Auteur();
						lireAuteur(reponse, auteur); // validation de l'Auteur et mise en cache
					}
				} catch (SQLException | ModelException e) {
					String message = "AuteurDAO.DAO.find() : " + e.getClass().getSimpleName();
					throw new DaoException(message);
				} finally { cloturerAccesBdd(); }
			} else { auteur = AUTEURS.get(id); } // depuis le cache AUTEURS
			return auteur;
		}
		@Override
		public List<Auteur> findAll() throws DaoException {
			if (forcerRequete) { // depuis la base de donnee
				lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
				AUTEURS.clear(); // effacer le cache
				try {
					// creation de la requete
					Connection connectionBDD = BDDservice.getInstance().getConnection();
					Statement requete = connectionBDD.createStatement();
					ResultSet reponse = requete.executeQuery(AuteurRequetes.SELECT_ALL);
					while (reponse.next()) {
						Auteur auteur = new Auteur();
						lireAuteur(reponse, auteur); // validation de l'Auteur et mise en cache
					}
				} catch (SQLException | ModelException e) {
					String message = "AuteurDAO.DAO.findAll() : " + e.getClass().getSimpleName();
					throw new DaoException(message);
				} finally { cloturerAccesBdd(); }
			}
			return new ArrayList<>(AUTEURS.values());
		}
		@Override
		public Auteur update(String fromJson) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Auteur auteur;
			try {
				JSONObject json = new JSONObject(fromJson);  // conversion en json
				if (!Auteur.ValiderJson(json) && json.has(Auteur.JSON_ID)) throw new JsonException(JsonException.JSON_INCORRECT); // validation  du json
				// modification d'un Auteur
				auteur = AUTEURS.get(json.getInt(Auteur.JSON_ID));
				auteur.setNom(json.getString(Auteur.JSON_NOM));
				auteur.setPrenom(json.getString(Auteur.JSON_PRENOM));
				auteur.setUrl(json.getString(Auteur.JSON_URL));
				// validation de l'Auteur et mise en cache
				if (Auteur.ValiderAuteur(auteur)) { AUTEURS.put(auteur.getId(), auteur);
				} else throw new ModelException(ModelException.INSTANCE_INVALIDE);
				// Requete// creation de la requete
				Connection connectionBDD = BDDservice.getInstance().getConnection();
				PreparedStatement requetePreparee = AuteurRequetes.Update(connectionBDD, auteur);
				int lignesModifiees = requetePreparee.executeUpdate();
				if (lignesModifiees != 1) { throw new DaoException(DaoException.DONNEE_INVALIDE); } // verifie l'impact de la modification
				else { chargerAuteurs = true; } // sert en cas de nouyvelle modification de la base
			} catch (JSONException | SQLException | JsonException | ModelException e) {
				String message = "AuteurDAO.DAO.update() : " + e.getClass().getSimpleName();
				throw new DaoException(message);
			} finally { cloturerAccesBdd(); }
			return auteur;
		}
		@Override
		public boolean delete(int id) throws DaoException {
			lock.lock(); // verrouillage de la methode pour empecher les acces simultanes
			Auteur auteur = AUTEURS.get(id);
			int lignesSupprimees;
			if (auteur == null) return false;
			else {
				try {
					Connection connectionBDD = BDDservice.getInstance().getConnection();
					PreparedStatement requetePreparee = AuteurRequetes.Delete(connectionBDD, id);
					lignesSupprimees = requetePreparee.executeUpdate();
				} catch (SQLException e) {
					String message = "AuteurDAO.DAO.delete() : " + e.getClass().getSimpleName();
					throw new DaoException(message);
				} finally { cloturerAccesBdd(); }
				if (lignesSupprimees == 1) {
					AUTEURS.remove(id);
					return true;
				}
				else return false;
			}
		}
		private void lireAuteur(ResultSet reponse, Auteur auteur) throws SQLException, ModelException {
			auteur.setId(reponse.getInt(AuteurRequetes.CHAMPS_ID));
			auteur.setNom(reponse.getString(AuteurRequetes.CHAMPS_NOM));
			auteur.setPrenom(reponse.getString(AuteurRequetes.CHAMPS_PRENOM));
			auteur.setUrl(reponse.getString(AuteurRequetes.CHAMPS_URL));
			if (Auteur.ValiderAuteur(auteur)) { AUTEURS.put(auteur.getId(), auteur); }
			else { throw new ModelException(ModelException.INSTANCE_INVALIDE); }
		}
		private void cloturerAccesBdd() {
			if (!maintenirConnection) BDDservice.getInstance().closeConnection(); // pour les requetes multiples, ou fermer la connection
			lock.unlock(); // Liberation du verrou pour permettre d'autres accès
		}

	}
	// Constructeur privé pour empêcher l'instanciation
	private AuteurDAO() {}
	private final static Map<Integer, Auteur> AUTEURS = new ConcurrentHashMap<>();
	private static boolean maintenirConnection = false;
	private static boolean forcerRequete = false;
	private static boolean chargerAuteurs = true;
	//
	public static Auteur creerAuteur(String fromJson) {
        try { return dao().create(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
    }
	public static Auteur recupererAuteur(int id) {
        try { return dao().find(id); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
    }
	public static List<Auteur> listerAuteurs() {
		try { return dao().findAll(); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static Auteur mettreAJourAuteur(String fromJson) {
		try { return dao().update(fromJson); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return null;
	}
	public static boolean supprimerAuteur(int id) {
		try { return dao().delete(id); }
		catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
		return false;
	}
	//

	//
}