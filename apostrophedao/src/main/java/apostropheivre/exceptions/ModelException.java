package apostropheivre.exceptions;
import apostropheivre.utilitaires.Log;
/**
 * [Exception.ModelException] - class
 * @author Mathaus
 */
public class ModelException extends Exception {
	public ModelException(String location) {
		super(location + ": L'objet instancie n'est pas valide.", new Throwable("Exception.ModelException"));
		Log.error(this.getMessage(), this.getCause());
	}
}