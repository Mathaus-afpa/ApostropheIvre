package apostropheivre.compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * [compte.CompteRequetes] - class
 * @author Mathaus
 */
public class CompteRequetes {

    private CompteRequetes() {}

    // Champs de la table USERS
    public static final String CHAMPS_ID = "id";
    public static final String CHAMPS_LOGIN = "login";
    public static final String CHAMPS_MAIL = "mail";
    public static final String CHAMPS_PASSWORD = "password";
    public static final String CHAMPS_ROLE = "role";

    // Requête d'insertion avec rôle par défaut (3) si non spécifié
    public static final String INSERT =
            "INSERT INTO USERS (" + CHAMPS_LOGIN + ", " + CHAMPS_MAIL + ", " + CHAMPS_PASSWORD + ", " + CHAMPS_ROLE + ") " +
                    "VALUES (?, ?, ?, COALESCE(?, 3))";

    public static PreparedStatement Insert(Connection connection, Compte compte) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.INSERT);
        requetePreparee.setString(1, compte.getLogin());       // login
        requetePreparee.setString(2, compte.getMail());        // mail
        requetePreparee.setString(3, compte.getPassword());    // password
        requetePreparee.setObject(4, compte.getRole() == null ? null : compte.getRole());   // rôle (peut être null)
        return requetePreparee;
    }

    // Requête pour sélectionner un utilisateur par son ID
    public static final String SELECT_ONE =
            "SELECT " + CHAMPS_ID + ", " + CHAMPS_LOGIN + ", " + CHAMPS_MAIL + ", " + CHAMPS_PASSWORD + ", " + CHAMPS_ROLE +
                    " FROM USERS WHERE " + CHAMPS_ID + " = ?";

    public static PreparedStatement SelectById(Connection connection, int id) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.SELECT_ONE);
        requetePreparee.setInt(1, id);
        return requetePreparee;
    }

    // Requête pour sélectionner tous les utilisateurs
    public static final String SELECT_ALL =
            "SELECT " + CHAMPS_ID + ", " + CHAMPS_LOGIN + ", " + CHAMPS_MAIL + ", " + CHAMPS_PASSWORD + ", " + CHAMPS_ROLE +
                    " FROM USERS";

    // Requête pour mettre à jour un utilisateur
    public static final String UPDATE =
            "UPDATE USERS SET " + CHAMPS_LOGIN + " = ?, " + CHAMPS_MAIL + " = ?, " + CHAMPS_PASSWORD + " = ?, " + CHAMPS_ROLE + " = ? " +
                    "WHERE " + CHAMPS_ID + " = ?";

    public static PreparedStatement Update(Connection connection, Compte compte) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.UPDATE);
        requetePreparee.setString(1, compte.getLogin());       // login
        requetePreparee.setString(2, compte.getMail());        // mail
        requetePreparee.setString(3, compte.getPassword());    // password
        requetePreparee.setInt(4, compte.getRole());        // rôle
        requetePreparee.setInt(5, compte.getId());             // id
        return requetePreparee;
    }

    // Requête pour supprimer un utilisateur par son ID
    public static final String DELETE = "DELETE FROM USERS WHERE " + CHAMPS_ID + " = ?";

    public static PreparedStatement Delete(Connection connection, int id) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.DELETE);
        requetePreparee.setInt(1, id);
        return requetePreparee;
    }
}
