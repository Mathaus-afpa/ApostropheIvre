package apostropheivre.exceptions;
import apostropheivre.utilitaires.Log;
/**
 * [Exception.RegExException] - class
 * @author Mathaus
 */
public class RegExException extends Exception {
	public RegExException(String location) {
		super(location + ": L'entree ne correspond pas a la regex.", new Throwable("Exception.RegExException"));
		Log.error(this.getMessage(), this.getCause());
	}
}