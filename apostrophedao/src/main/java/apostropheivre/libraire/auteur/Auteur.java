package apostropheivre.libraire.auteur;
import apostropheivre.exceptions.RegExException;
import org.json.JSONObject;
import java.util.regex.Pattern;
/**
 * [Auteur] - class
 * @author Mathaus
 */
public class Auteur {
	public Auteur() {}
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String JSON_ID = "id";
	public static final String JSON_NOM = "nom";
	public static final String JSON_PRENOM = "prenom";
	public static final String JSON_URL = "url";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	private static final String REGEX_NOM = "^[A-ZÀ-ÖØ-Ý][a-zà-öø-ÿ\\-\\s]{1,49}$";
	private static final String REGEX_PRENOM = "^[A-ZÀ-ÖØ-Ý-a-zà-öø-ÿ\\-\\.\\s]{1,49}$";
	private static final String REGEX_URL = ".*";//"^[\\w\\d_\\-]+\\.(jpg|jpeg|png|bmp)$";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs prive">
	private Integer id;
	private String nom;
	private String prenom;
	private String url;
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
	public String getUrl() {
		return url;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Setter">
	public void setId(int id) {
		this.id = (id > 0) ? id : null;
	}
	public void setNom(String nom) throws RegExException {
		if (Pattern.matches(REGEX_NOM, nom)) {
			this.nom = nom;
		} else { throw new RegExException("Auteur.setNom"); }
	}
	public void setPrenom(String prenom) throws RegExException {
		if (Pattern.matches(REGEX_PRENOM, prenom)) {
			this.prenom = prenom;
		} else { throw new RegExException("Auteur.setPrenom"); }
	}
	public void setUrl(String url) throws RegExException {
		if (Pattern.matches(REGEX_URL, url)) {
			this.url = url;
		} else { throw new RegExException("Auteur.setUrl"); }
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Validateurs">
	/**
	 * Verifie la validite d'un objet Auteur
	 * @param auteur
	 * @return vrai ou faux
	 */
	public static final boolean ValiderAuteur(Auteur auteur) {
		String nom = auteur.getNom();
		String prenom = auteur.getPrenom();
		String url = auteur.getUrl();
		return Pattern.matches(REGEX_NOM, nom)
				&& Pattern.matches(REGEX_PRENOM, prenom)
				&& Pattern.matches(REGEX_URL, url);
	}
	/**
	 * Verifie la validite d'un json Auteur
	 * @param auteurJson
	 * @return vrai ou faux
	 */
	public static final boolean ValiderJson(JSONObject auteurJson) {
		return auteurJson.has(JSON_NOM) && auteurJson.has(JSON_PRENOM) && auteurJson.has(JSON_URL);
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