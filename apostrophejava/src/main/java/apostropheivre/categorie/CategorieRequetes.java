package apostropheivre.categorie;
import apostropheivre.auteur.Auteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * [auteur.AuteurRequetes] - class
 * @author Mathaus
 */
public class CategorieRequetes
{
    private CategorieRequetes() {}
    public final static String CHAMPS_ID = "cat_id";
    public final static String CHAMPS_LIBELLE = "cat_libelle";
    public static final String INSERT = "INSERT INTO CATEGORIE (" + CHAMPS_LIBELLE + ") VALUES (?)";
    public static final PreparedStatement Insert(Connection connection, Categorie categorie) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.INSERT);
        requetePreparee.setString(1, categorie.getLibelle());			//cat_libelle
        return requetePreparee;
    }
    public static final String SELECT_ONE = "SELECT " + CHAMPS_ID + ", " + CHAMPS_LIBELLE + " FROM CATEGORIE WHERE " + CHAMPS_ID + " = ?";
    public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.SELECT_ONE);
        requetePreparee.setInt(1, id);
        return requetePreparee;
    }
    public static final String SELECT_ALL = "SELECT " + CHAMPS_ID + ", " + CHAMPS_LIBELLE + " FROM CATEGORIE";
    public static final String UPDATE = "UPDATE CATEGORIE SET " + CHAMPS_LIBELLE + " = ? WHERE " + CHAMPS_ID + " = ?";
    public static final PreparedStatement Update(Connection connection, Categorie categorie) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.UPDATE);
        requetePreparee.setString(1, categorie.getLibelle());			//cat_libelle
        return requetePreparee;
    }
    public static final String DELETE = "DELETE FROM CATEGORIE WHERE " + CHAMPS_ID + " = ?";
    public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
        PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.DELETE);
        requetePreparee.setInt(1, id);
        return requetePreparee;
    }
}