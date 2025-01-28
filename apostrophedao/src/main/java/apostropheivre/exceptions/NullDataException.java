package apostropheivre.exceptions;
import apostropheivre.utilitaires.Log;
/**
 * [Exception.NullDataException] - class
 * @author Mathaus
 */
public class NullDataException extends Exception {
	public NullDataException(String location) {
		super(location + ": Valeur null non autorisee.", new Throwable("Exception.NotNullDataException"));
		Log.error(this.getMessage(), this.getCause());
	}
	public NullDataException(String location, String contexte) {
		super(location + ": Valeur null non autorisee pour " + contexte, new Throwable("Exception.NotNullDataException"));
		Log.error(this.getMessage(), this.getCause());
	}
}