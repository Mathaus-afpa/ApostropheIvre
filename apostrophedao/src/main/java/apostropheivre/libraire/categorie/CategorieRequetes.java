package apostropheivre.libraire.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * [CategorieRequetes] - class
 * @author Mathaus
 */
public class CategorieRequetes {
	private CategorieRequetes() {}
	//<editor-fold defaultstate="expanded" desc="Champs BDD">
	public final static String CHAMPS_ID = "cat_id";
	public final static String CHAMPS_LIBELLE = "cat_libelle";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public static final String INSERT  = ""; //todo:
	public static final PreparedStatement Insert(Connection connection, Categorie categorie) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.INSERT);
		requetePreparee.setString(1, categorie.getLibelle());			//login
		//todo: client ?
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ONE">
	public static final String SELECT_ONE  = ""; //todo:
	public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.SELECT_ONE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ALL">
	public static final String SELECT_ALL = "SELECT " + CHAMPS_ID + ", " + CHAMPS_LIBELLE + " FROM CATEGORIE";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="UPDATE">
	public static final String UPDATE = ""; //todo:
	public static final PreparedStatement Update(Connection connection, Categorie categorie) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.UPDATE);
		//todo :
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DELETE">
	public static final String DELETE = "DELETE FROM CATEGORIE WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CategorieRequetes.DELETE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
}