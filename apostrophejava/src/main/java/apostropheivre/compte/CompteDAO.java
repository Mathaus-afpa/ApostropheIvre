package apostropheivre.compte;
import  apostropheivre.dao.IDAO;
import  apostropheivre.dao.BDDservice;
import  apostropheivre.exception.DaoException;
import  apostropheivre.exception.ModelException;
import  apostropheivre.exception.JsonException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import apostropheivre.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * [Compte.Compte.CompteDAO] - class
 * @author Mathaus
 */
public class CompteDAO {
    // Instance unique
    private static DAO dao() { return DAOinstance.INSTANCE; }
    private static class DAOinstance { private static final DAO INSTANCE = new DAO(); };
    // Classe interne qui implémente dao.IDAO
    private static final class DAO implements IDAO<Compte> {
        private static final Lock lock = new ReentrantLock();
        @Override
        public Compte create(String fromJson) throws DaoException {
            lock.lock(); // verrouillage de la méthode pour empecher les acces simultanées
            Compte nouveauCompte;
            try {
                JSONObject json = new JSONObject(fromJson); //conversion en json
                if (!Compte.ValiderJson(json)) throw new JsonException(JsonException.JSON_INCORRECT); // validation
                // du json
                // creation d'un nouveau Compte
                nouveauCompte = new Compte();
                nouveauCompte.setLogin(json.getString(Compte.JSON_LOGIN));
                nouveauCompte.setMail(json.getString(Compte.JSON_MAIL));
                nouveauCompte.setPassword(json.getString(Compte.JSON_PASSWORD));

                // Récupération du rôle en tant qu'entier, avec une valeur par défaut si nécessaire
                int role = json.has(Compte.JSON_ROLE) ? json.getInt(Compte.JSON_ROLE) : 3;  // Valeur par défaut 3 si le rôle n'est pas fourni
                nouveauCompte.setRole(role);

                //creation de la requête
                Connection connectionBDD = BDDservice.getInstance().getConnection();
                PreparedStatement requetePreparee = CompteRequetes.Insert(connectionBDD, nouveauCompte);
                int idNouveauCompte = requetePreparee.executeUpdate();

                // attribution de l'ID généré
                nouveauCompte.setId(idNouveauCompte);

                // validation du Compte et mise en cache
                if (Compte.ValiderCompte(nouveauCompte)) {
                    chargerComptes = true; //sert à indiquer une modification de la base
                    COMPTES.put(nouveauCompte.getId(), nouveauCompte); // permet d'éviter une requete findAll
                    return nouveauCompte;
                } else throw new ModelException(ModelException.INSTANCE_INVALIDE);

            } catch (JSONException | SQLException | JsonException | ModelException e) {
                String message = "CompteDAO.DAO.create() : " + e.getClass().getSimpleName();
                Log.error(e.getMessage(), e.getCause());
                throw new DaoException(message);

            } finally { cloturerAccessBdd(); }

        }
        @Override
        public Compte find(int id) throws DaoException {
            Compte compte = null;
            if (forcerRequete) { // depuis la BDD
                lock.lock(); // verrouillage de la méthode pour empecher les acces simultanes

                try {
                    // creation de la requete
                    Connection connectionBDD = BDDservice.getInstance().getConnection();
                    PreparedStatement requetePreparee = CompteRequetes.SelectById(connectionBDD, id);
                    ResultSet reponse = requetePreparee.executeQuery();
                    if (reponse.next()) {
                        compte = new Compte();
                        lireCompte(reponse, compte);
                    }
                } catch (SQLException | ModelException e) {
                    String message = "CompteDAO.DAO.find(): " + e.getClass().getSimpleName();
                    throw new DaoException(message);
                } finally { cloturerAccessBdd(); }
            } else { compte = COMPTES.get(id); } // depuis le cache COMPTES
            return compte;
        }
        @Override
        public List<Compte> findAll() throws DaoException {
            if (forcerRequete) { // depuis la base de données
                lock.lock(); // verrouillage de la méthode pour empecher les accés simultanés
                COMPTES.clear(); // effacer le cache
                try {
                    // creation de la requete
                    Connection connectionBDD = BDDservice.getInstance().getConnection();
                    Statement requete = connectionBDD.createStatement();
                    ResultSet reponse = requete.executeQuery(CompteRequetes.SELECT_ALL);
                    while (reponse.next()) {
                        Compte compte = new Compte();
                        lireCompte(reponse, compte); // validation de Compte et mise en cache
                    }
                } catch (SQLException | ModelException e) {
                    String message = "CompteDAO.DAO.findall(): " + e.getClass().getSimpleName();
                    throw new DaoException(message);
                } finally { cloturerAccessBdd(); }
            }
            return new ArrayList<>(COMPTES.values());
        }
        @Override
        public Compte update(String fromJson) throws DaoException {
            lock.lock(); // verrouillage de la méthode pour empecher les acces simultanés
            Compte compte;
            try {
                JSONObject json = new JSONObject(fromJson); // conversion en json
                if (!Compte.ValiderJson(json) && json.has(Compte.JSON_ID)) throw new JsonException(JsonException.JSON_INCORRECT); // validation du json
                // modification d'un compte
                compte = COMPTES.get(json.getInt(Compte.JSON_ID));
                compte.setLogin(json.getString(Compte.JSON_LOGIN));
                compte.setMail(json.getString(Compte.JSON_MAIL));
                compte.setPassword(json.getString(Compte.JSON_PASSWORD));
                compte.setRole(Integer.parseInt(json.getString(Compte.JSON_ROLE)));
                // validation de Compte et mise en cache
                if (Compte.ValiderCompte(compte)) { COMPTES.put(compte.getId(), compte);
                } else throw new ModelException(ModelException.INSTANCE_INVALIDE);
                // requete Création de la requete
                Connection connectionBDD = BDDservice.getInstance().getConnection();
                PreparedStatement requetePreparee = CompteRequetes.Update(connectionBDD, compte);
                int lignesModifiees = requetePreparee.executeUpdate();
                if (lignesModifiees != 1) { throw new DaoException(DaoException.DONNEE_INVALIDE); } // verifie l'impact de la modification
                else { chargerComptes = true; } // sert en cas de nouvelle modification de la base
            } catch (JSONException | SQLException | JsonException | ModelException e) {
                String message = "CompteDAO.DAO.update() : " + e.getClass().getSimpleName();
                throw new DaoException(message);

            } finally { cloturerAccessBdd(); }

            return compte;

        }

        @Override
        public boolean delete(int id) throws DaoException {
            lock.lock(); //// verrouillage de la methode pour empecher les acces simultanes
            Compte compte = COMPTES.get(id);
            int lignesSupprimees;
            if (compte == null) return false;
            else {
                try {

                    Connection connectionBDD = BDDservice.getInstance().getConnection();
                    PreparedStatement requetePreparee = CompteRequetes.Delete(connectionBDD, id);
                    lignesSupprimees = requetePreparee.executeUpdate();
                } catch (SQLException e) {
                    String message = "CompteDAO.DAO.delete(): " + e.getClass().getSimpleName();
                    throw new DaoException(message);


                } finally {
                    cloturerAccessBdd();
                }

                if (lignesSupprimees == 1) {
                    COMPTES.remove(id);
                    return true;
                }

                else return false;

            }

        }

        private void lireCompte(ResultSet reponse, Compte compte) throws SQLException, ModelException {
            compte.setId(reponse.getInt(CompteRequetes.CHAMPS_ID));
            compte.setLogin(reponse.getString(CompteRequetes.CHAMPS_LOGIN));
            compte.setMail(reponse.getString(CompteRequetes.CHAMPS_MAIL));
            compte.setPassword(reponse.getString(CompteRequetes.CHAMPS_PASSWORD));
            compte.setRole(Integer.parseInt(reponse.getString(CompteRequetes.CHAMPS_ROLE)));
            if (Compte.ValiderCompte(compte)) { COMPTES.put(compte.getId(), compte); }
            else { throw new ModelException(ModelException.INSTANCE_INVALIDE); }
        }
        private void cloturerAccessBdd() {
            if (!maintenirConnection) BDDservice.getInstance().closeConnection(); // pour les requetes multiples, ou fermer la connection
            lock.unlock(); // Liberation du verrou pour permettre d'autres accès
        }

    }

    // Constructeur privé pour empêcher l'instanciation
	private CompteDAO() {}
    private final static Map<Integer, Compte> COMPTES = new ConcurrentHashMap<>();
    private static boolean maintenirConnection = false;
    private static boolean forcerRequete = false;
    private static boolean chargerComptes = true;
    //
    public static Compte creerCompte(String fromJson) {
        try { return dao().create(fromJson); }
        catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
        return null;
    }
    public static Compte recupererCompte(int id) {
        try { return dao().find(id); }
        catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
        return null;
    }
    public static Compte mettreAJourCompte(String fromJson) {
        try { return dao().update(fromJson); }
        catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
        return null;
    }
    public static boolean supprimerCompte(int id) {
        try { return dao().delete(id); }
        catch (DaoException e) {  Log.error(e.getMessage(), e.getCause()); }
        return false;
    }
    //
    public static boolean isMaintenirConnection() {
        return maintenirConnection;
    }
    public static void setMaintenirConnection(boolean maintenirConnection) {
        CompteDAO.maintenirConnection = maintenirConnection;
    }
    public static boolean isForcerRequete() {
        return forcerRequete;
    }
    public static void setForcerRequete(boolean forcerRequete) {
        CompteDAO.forcerRequete = forcerRequete;
    }
    //

}
