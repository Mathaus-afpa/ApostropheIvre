package apostropheivre.exception;
/**
 * [Exception.JsonException] - class
 * @author Mathaus
 */
public class JsonException extends Exception {
	public static final String JSON_INCORRECT = "Le json n'est pas valide.";
	public JsonException(String contexte) {
		super(contexte, new Throwable("Exception.JsonException"));
	}
}