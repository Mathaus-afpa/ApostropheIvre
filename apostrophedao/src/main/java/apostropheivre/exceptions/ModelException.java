package apostropheivre.exceptions;
import apostropheivre.utilitaires.Log;
/**
 * [Exception.ModelException] - class
 * @author Mathaus
 */
public class ModelException extends Exception {
	public static final String AUTEUR = "Auteur";
	public static final String CATEGORIE = "Categorie";
	public static final String COMPTE = "Compte";
	public static final String CLIENT = "Client";
	public static final String LIBRAIRE = "Libraire";
	public static final String LIVRE = "Livre";
	public ModelException(String location, String className) {
		super(location + ": L'objet instancie " + className +" n'est pas valide.", new Throwable("Exception.ModelException"));
		Log.error(this.getMessage(), this.getCause());
	}
}