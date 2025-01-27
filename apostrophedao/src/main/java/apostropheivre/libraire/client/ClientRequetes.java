package apostropheivre.libraire.client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * [ClientRequetes] - class
 * @author Mathaus
 */
public class ClientRequetes {
	private ClientRequetes() {}
	//<editor-fold defaultstate="expanded" desc="Champs BDD">
	public final static String CHAMPS_ID = "cli_id";
	public final static String CHAMPS_NOM = "cli_nom";
	public final static String CHAMPS_PRENOM = "cli_prenom";
	public final static String CHAMPS_EMAIL = "cli_email";
	public final static String CHAMPS_ADRESSE = "cli_adresse";
	public final static String CHAMPS_CODE_POSTAL = "cli_code_postal";
	public final static String CHAMPS_VILLE = "cli_ville";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public static final String INSERT  = ""; //todo:
	public static final PreparedStatement Insert(Connection connection, Client client) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(ClientRequetes.INSERT);
		requetePreparee.setString(1, client.getNom());			//login
		//todo: client ?
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ONE">
	public static final String SELECT_ONE  = ""; //todo:
	public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(ClientRequetes.SELECT_ONE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ALL">
	public static final String SELECT_ALL  = ""; //todo:
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="UPDATE">
	public static final String UPDATE = ""; //todo:
	public static final PreparedStatement Update(Connection connection, Client client) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(ClientRequetes.UPDATE);
		//todo :
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DELETE">
	public static final String DELETE = "DELETE FROM USERS WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(ClientRequetes.DELETE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
}