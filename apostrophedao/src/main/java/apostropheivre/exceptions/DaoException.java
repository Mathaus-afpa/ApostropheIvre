package apostropheivre.exceptions;
import apostropheivre.utilitaires.Log;
/**
 * [Exception.DaoException] - class
 * @author Mathaus
 */
public class DaoException extends Exception {
	public static final String REQUETE_INVALIDE = ": La requete est invalide.";
	public static final String DONNEE_CORROMPUE = ": Une donnee corrompue existe en base.";
	public DaoException(String location, String contexte) {
		super(location + " : " + contexte, new Throwable("Exception.DaoException"));
		Log.error(this.getMessage(), this.getCause());
	}
}