package apostropheivre.libraire.auteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * [AuteurRequetes] - class
 * @author Mathaus
 */
public class AuteurRequetes {
	private AuteurRequetes() {}
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public final static String CHAMPS_ID = "aut_id";
	public final static String CHAMPS_NOM = "aut_nom";
	public final static String CHAMPS_PRENOM = "aut_prenom";
	public final static String CHAMPS_URL = "aut_photo";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">
	public static final String INSERT = "INSERT INTO AUTEUR (" + CHAMPS_NOM + ", " + CHAMPS_PRENOM + ", " + CHAMPS_URL + ") VALUES (?, ?, ?)";
	public static final PreparedStatement Insert(Connection connection, Auteur auteur) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(AuteurRequetes.INSERT);
		requetePreparee.setString(1, auteur.getNom());			//aut_nom
		requetePreparee.setString(2, auteur.getPrenom());		//aut_prenom
		requetePreparee.setString(3, auteur.getUrl());			//aut_photo
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ONE">
	public static final String SELECT_ONE = "SELECT " + CHAMPS_ID + ", " + CHAMPS_NOM + ", " + CHAMPS_PRENOM + ", " + CHAMPS_URL + " FROM AUTEUR WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement SelectById(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(AuteurRequetes.SELECT_ONE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="SELECT ALL">
	public static final String SELECT_ALL = "SELECT " + CHAMPS_ID + ", " + CHAMPS_NOM + ", " + CHAMPS_PRENOM + ", " + CHAMPS_URL + " FROM AUTEUR";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="UPDATE">
	public static final String UPDATE = "UPDATE AUTEUR SET " + CHAMPS_NOM + " = ?, " + CHAMPS_PRENOM + " = ?, " + CHAMPS_URL + " = ? WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Update(Connection connection, Auteur auteur) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(AuteurRequetes.UPDATE);
		requetePreparee.setString(1, auteur.getNom());			//aut_nom
		requetePreparee.setString(2, auteur.getPrenom());		//aut_prenom
		requetePreparee.setString(3, auteur.getUrl());			//aut_photo
		requetePreparee.setInt(4, auteur.getId());				//aut_id
		return requetePreparee;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="DELETE">
	public static final String DELETE = "DELETE FROM AUTEUR WHERE " + CHAMPS_ID + " = ?";
	public static final PreparedStatement Delete(Connection connection, int id) throws SQLException {
		PreparedStatement requetePreparee = connection.prepareStatement(AuteurRequetes.DELETE);
		requetePreparee.setInt(1, id);
		return requetePreparee;
	}
	//</editor-fold>
}