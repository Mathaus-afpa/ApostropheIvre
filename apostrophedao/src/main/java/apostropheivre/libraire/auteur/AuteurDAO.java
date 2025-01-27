package apostropheivre.libraire.auteur;
import apostropheivre.interfaces.IApostropheDAO;
import apostropheivre.interfaces.IApostropheInnerDAO;
/**
 * [AuteurDAO] - class
 * @author Mathaus
 */
public class AuteurDAO implements IApostropheDAO {

	//<editor-fold defaultstate="expanded" desc="DAO Singletons">
	private static DAO dao() { return DAOinstance.INSTANCE; }
	private static class DAOinstance {
		private static final DAO INSTANCE = new DAO();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">

	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">

	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="INSERT">

	//</editor-fold>


	// Classe interne qui implémente l'interface dao.IDAO
	private static final class DAO implements IApostropheInnerDAO<Auteur> {
	}
}