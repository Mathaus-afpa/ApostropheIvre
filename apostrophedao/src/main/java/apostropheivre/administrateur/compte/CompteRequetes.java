package apostropheivre.administrateur.compte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * [CompteRequetes] - class
 * @author Mathaus
 */
public class CompteRequetes {
	private CompteRequetes() {}
	//<editor-fold defaultstate="expanded" desc="Champs BDD">
	public final static String CHAMPS_ID = "id";
	public final static String CHAMPS_MAIL = "mail";
	public final static String CHAMPS_LOGIN = "login";
	public final static String CHAMPS_PASSWORD = "password";
	public final static String CHAMPS_ROLE = "role";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public static final String INSERT  = ""; //todo:
	public static final PreparedStatement Insert(Connection connection, Compte compte) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.INSERT);
		requetePreparee.setString(1, compte.getLogin());			//login
		//todo: client ?
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ONE">
	public static final String SELECT_ONE  = ""; //todo:
	public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.SELECT_ONE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ALL">
	public static final String SELECT_ALL = "SELECT " + CHAMPS_ID + ", " + CHAMPS_LOGIN + " FROM USERS";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="UPDATE">
	public static final String UPDATE = ""; //todo:
	public static final PreparedStatement Update(Connection connection, Compte compte) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.UPDATE);
		//todo :
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DELETE">
	public static final String DELETE = "DELETE FROM USERS WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(CompteRequetes.DELETE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
}