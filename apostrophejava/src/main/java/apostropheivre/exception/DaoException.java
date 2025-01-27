package apostropheivre.exception;

/**
 * [Exception.DaoException] - class
 * @author Mathaus
 */
public class DaoException extends Exception {
	public static final String DONNEE_INVALIDE = "Une donnee invalide existe en base.";
	public DaoException(String contexte) {
		super(contexte, new Throwable("Exception.DaoException"));
	}
}