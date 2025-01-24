package apostropheivre.exception;
/**
 * [Exception.ModelException] - class
 * @author Mathaus
 */
public class ModelException extends Exception {
	public static final String INSTANCE_INVALIDE = "L'objet instancie n'est pas valide.";
	public ModelException(String contexte) {
		super(contexte, new Throwable("Exception.ModelException"));
	}
}