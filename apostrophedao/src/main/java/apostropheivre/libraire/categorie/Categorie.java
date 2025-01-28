package apostropheivre.libraire.categorie;
import apostropheivre.exceptions.RegExException;
import org.json.JSONObject;
import java.util.regex.Pattern;
/**
 * [Categorie] - class
 * @author Mathaus
 */
public class Categorie {
	public Categorie() {}
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String JSON_ID = "id";
	public static final String JSON_LIBELLE = "libelle";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	private static final String REGEX_LIBELLE = "^[A-ZÀ-ÖØ-Ý-a-zà-öø-ÿ\\-\\.\\s&']{1,49}$";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs prive">
	private Integer id;
	private String libelle;
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Getter">
	public Integer getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Setter">
	public void setId(int id) {
		this.id = id;
	}
	public void setLibelle(String libelle) throws RegExException {
		if (Pattern.matches(REGEX_LIBELLE, libelle)) {
			this.libelle = libelle;
		} else { throw new RegExException("Categorie.setLibelle"); }
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Validateurs">
	/**
	 * Verifie la validite d'un objet Categorie
	 * @param categorie
	 * @return vrai ou faux
	 */
	public static final boolean ValiderCategorie(Categorie categorie) {
		String libelle = categorie.getLibelle();
		return Pattern.matches(REGEX_LIBELLE, libelle);
	}
	/**
	 * Verifie la validite d'un json Categorie
	 * @param categorieJson
	 * @return vrai ou faux
	 */
	public static final boolean ValiderJson(JSONObject categorieJson) {
		return categorieJson.has(JSON_LIBELLE);
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Serialisation">
	@Override
	public String toString() {
		return this.getLibelle();
	}
	public String toJson() {
		JSONObject json = new JSONObject(this);
		return json.toString();
	}
	//</editor-fold>
}