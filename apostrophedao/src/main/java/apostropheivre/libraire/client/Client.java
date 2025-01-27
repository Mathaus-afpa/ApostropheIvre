package apostropheivre.libraire.client;
import apostropheivre.exceptions.RegExException;
import org.json.JSONObject;
import java.util.regex.Pattern;
/**
 * [Client] - class
 * @author Mathaus
 */
public class Client {
	public Client() {}
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String JSON_ID = "id";
	public static final String JSON_NOM = "nom";
	public static final String JSON_PRENOM = "prenom";
	public static final String JSON_EMAIL = "email";
	public static final String JSON_ADRESSE = "adresse";
	public static final String JSON_CODE_POSTAL = "codePostal";
	public static final String JSON_VILLE = "ville";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String REGEX_NOM = "^[A-ZÀ-ÖØ-Ý][a-zà-öø-ÿ\\-\\s]{1,49}$";
	public static final String REGEX_PRENOM = "^[A-ZÀ-ÖØ-Ý][a-zà-öø-ÿ\\-\\s]{1,49}$";
	public static final String REGEX_EMAIL = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
	public static final String REGEX_ADRESSE = "^[\\wÀ-ÖØ-Ýà-öø-ÿ,.'\\-\\s]{5,100}$";
	public static final String REGEX_CODE_POSTAL = "^\\d{5}$";
	public static final String REGEX_VILLE = "^[A-ZÀ-ÖØ-Ý][a-zà-öø-ÿ\\-\\s]{1,49}$";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs prive">
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String codePostal;
	private String ville;
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
	public String getEmail() {
		return email;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public String getVille() {
		return ville;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Setter">
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) throws RegExException {
		if (Pattern.matches(REGEX_NOM, nom)) {
			this.nom = nom;
		} else { throw new RegExException("Client.setNom"); }
	}
	public void setPrenom(String prenom) throws RegExException {
		if (Pattern.matches(REGEX_PRENOM, prenom)) {
			this.prenom = prenom;
		} else { throw new RegExException("Client.setPrenom"); }
	}
	public void setEmail(String email) throws RegExException {
		if (Pattern.matches(REGEX_EMAIL, email)) {
			this.email = email;
		} else { throw new RegExException("Client.setEmail"); }
	}
	public void setAdresse(String adresse) throws RegExException {
		if (Pattern.matches(REGEX_ADRESSE, adresse)) {
			this.adresse = adresse;
		} else { throw new RegExException("Client.setAdresse"); }
	}
	public void setCodePostal(String codePostal) throws RegExException {
		if (Pattern.matches(REGEX_CODE_POSTAL, codePostal)) {
			this.codePostal = codePostal;
		} else { throw new RegExException("Client.setCodePostal"); }
	}
	public void setVille(String ville) throws RegExException {
		if (Pattern.matches(REGEX_VILLE, ville)) {
			this.ville = ville;
		} else { throw new RegExException("Client.setVille"); }
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Validateurs">
	/**
	 * Verifie la validite d'un objet Client
	 * @param client
	 * @return vrai ou faux
	 */
	public static final boolean ValiderClient(Client client) {
		return Pattern.matches(REGEX_NOM, client.getNom())
				&& Pattern.matches(REGEX_PRENOM, client.getPrenom())
				&& Pattern.matches(REGEX_EMAIL, client.getEmail())
				&& Pattern.matches(REGEX_ADRESSE, client.getAdresse())
				&& Pattern.matches(REGEX_CODE_POSTAL, client.getCodePostal())
				&& Pattern.matches(REGEX_VILLE, client.getVille());
	}
	/**
	 * Verifie la validite d'un json Client
	 * @param clientJson
	 * @return vrai ou faux
	 */
	public static final boolean ValiderJson(JSONObject clientJson) {
		return clientJson.has(JSON_NOM) && clientJson.has(JSON_PRENOM)
				&& clientJson.has(JSON_EMAIL) && clientJson.has(JSON_ADRESSE)
				&& clientJson.has(JSON_CODE_POSTAL) && clientJson.has(JSON_VILLE);
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