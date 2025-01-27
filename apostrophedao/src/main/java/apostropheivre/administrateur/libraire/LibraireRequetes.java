package apostropheivre.administrateur.libraire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * [LibraireRequetes] - class
 * @author Mathaus
 */
public class LibraireRequetes {
	private LibraireRequetes() {}
	//<editor-fold defaultstate="expanded" desc="Champs BDD">
	public final static String CHAMPS_ID = "lib_id";
	public final static String CHAMPS_NOM = "lib_nom";
	public final static String CHAMPS_PRENOM = "lib_prenom";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public static final String INSERT = "INSERT INTO LIBRAIRE (" + CHAMPS_NOM + ", " + CHAMPS_PRENOM + ") VALUES (?, ?)";
	public static final PreparedStatement Insert(Connection connection, Libraire libraire) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LibraireRequetes.INSERT);
		requetePreparee.setString(1, libraire.getNom());			//lib_nom
		requetePreparee.setString(2, libraire.getPrenom());		//lib_prenom
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ONE">
	public static final String SELECT_ONE = "SELECT " + CHAMPS_ID + ", " + CHAMPS_NOM + ", " + CHAMPS_PRENOM + " FROM LIBRAIRE WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LibraireRequetes.SELECT_ONE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ALL">
	public static final String SELECT_ALL = "SELECT " + CHAMPS_ID + ", " + CHAMPS_NOM + ", " + CHAMPS_PRENOM + " FROM LIBRAIRE";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="UPDATE">
	public static final String UPDATE = "UPDATE LIBRAIRE SET " + CHAMPS_NOM + " = ?, " + CHAMPS_PRENOM + " = ? WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Update(Connection connection, Libraire libraire) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LibraireRequetes.UPDATE);
		requetePreparee.setString(1, libraire.getNom());			//lib_nom
		requetePreparee.setString(2, libraire.getPrenom());		//lib_prenom
		requetePreparee.setInt(3, libraire.getId());				//lib_id
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DELETE">
	public static final String DELETE = "DELETE FROM LIBRAIRE WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(LibraireRequetes.DELETE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
}