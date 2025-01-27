package apostropheivre.administrateur.libraire;
import apostropheivre.exceptions.RegExException;
import org.json.JSONObject;
import java.util.regex.Pattern;
/**
 * [Libraire] - class
 * @author Mathaus
 */
public class Libraire {
	public Libraire() {}
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String JSON_ID = "id";
	public static final String JSON_NOM = "nom";
	public static final String JSON_PRENOM = "prenom";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	private static final String REGEX_NOM = "^[A-ZÀ-ÖØ-Ý][a-zà-öø-ÿ\\-\\s]{1,49}$";
	private static final String REGEX_PRENOM = "^[A-ZÀ-ÖØ-Ý][a-zà-öø-ÿ\\-\\s]{1,49}$";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs prive">
	private Integer id;
	private String nom;
	private String prenom;
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Getter">
	public Integer getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Setter">
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) throws RegExException {
		if (Pattern.matches(REGEX_NOM, nom)) {
			this.nom = nom;
		} else { throw new RegExException("Libraire.setNom"); }
	}
	public void setPrenom(String prenom) throws RegExException {
		if (Pattern.matches(REGEX_PRENOM, prenom)) {
			this.prenom = prenom;
		} else { throw new RegExException("Libraire.setPrenom"); }
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Validateurs">
	/**
	 * Verifie la validite d'un objet Libraire
	 * @param libraire
	 * @return vrai ou faux
	 */
	public static final boolean ValiderLibraire(Libraire libraire) {
		String nom = libraire.getNom();
		String prenom = libraire.getPrenom();
		return Pattern.matches(REGEX_NOM, nom) && Pattern.matches(REGEX_PRENOM, prenom);
	}
	/**
	 * Verifie la validite d'un json Libraire
	 * @param libraireJson
	 * @return vrai ou faux
	 */
	public static final boolean ValiderJson(JSONObject libraireJson) {
		return libraireJson.has(JSON_NOM) && libraireJson.has(JSON_PRENOM);
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Serialisation">
	@Override
	public String toString() {
		return this.getNom() + " " + this.getPrenom();
	}
	public String toJson() {
		JSONObject json = new JSONObject(this);
		return json.toString();
	}
	//</editor-fold>
}