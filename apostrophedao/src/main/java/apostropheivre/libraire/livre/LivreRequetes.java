package apostropheivre.libraire.livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * [LivreRequetes] - class
 * @author Mathaus
 */
public class LivreRequetes {
	private LivreRequetes() {}
	//<editor-fold defaultstate="expanded" desc="Champs BDD">
	public final static String CHAMPS_ID = "id";
	public final static String CHAMPS_MAIL = "mail";
	public final static String CHAMPS_LOGIN = "login";
	public final static String CHAMPS_PASSWORD = "password";
	public final static String CHAMPS_ROLE = "role";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public static final String INSERT  = ""; //todo:
	public static final PreparedStatement Insert(Connection connection, Livre livre) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LivreRequetes.INSERT);
		requetePreparee.setString(1, livre.getTitre());			//login
		//todo: client ?
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ONE">
	public static final String SELECT_ONE  = ""; //todo:
	public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LivreRequetes.SELECT_ONE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ALL">
	public static final String SELECT_ALL  = ""; //todo:
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="UPDATE">
	public static final String UPDATE = ""; //todo:
	public static final PreparedStatement Update(Connection connection, Livre livre) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LivreRequetes.UPDATE);
		//todo :
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DELETE">
	public static final String DELETE = "DELETE FROM USERS WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LivreRequetes.DELETE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
}