package apostropheivre.libraire.livre;
import apostropheivre.Cache;
import apostropheivre.exceptions.NullDataException;
import apostropheivre.exceptions.RegExException;
import apostropheivre.libraire.auteur.Auteur;
import apostropheivre.libraire.categorie.Categorie;
import org.json.JSONObject;
import java.util.regex.Pattern;
/**
 * [Livre] - class
 * @author Mathaus
 */
public class Livre {
	public Livre() {}
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String JSON_ID = "id";
	public static final String JSON_TITRE = "titre";
	public static final String JSON_RESUME = "resume";
	public static final String JSON_IMAGE = "image";
	public static final String JSON_ISBN = "isbn";
	public static final String JSON_QUANTITE = "quantite";
	public static final String JSON_CATEGORIE = "categorie";
	public static final String JSON_AUTEUR = "auteur";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs JSON">
	public static final String REGEX_TITRE = "^[\\wÀ-ÖØ-Ýà-öø-ÿ,.'\"!?\\-\\s]{1,100}$";
	public static final String REGEX_RESUME = "^[\\wÀ-ÖØ-Ýà-öø-ÿ,.'\"!?\\-\\s]{10,1000}$";
	public static final String REGEX_IMAGE = "^[\\w\\d_\\-]+\\.(jpg|jpeg|png|bmp)$";
	public static final String REGEX_ISBN = "^(97(8|9))?\\d{9}(\\d|X)$";
	public static final String REGEX_QUANTITE = "^\\d+$";
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Champs prive">
	private Integer id;
	private String titre;
	private String resume;
	private String image;
	private String isbn;
	private Integer quantite;
	private Integer categorie;
	private Integer auteur;
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Getter">
	public Integer getId() {
		return id;
	}
	public String getTitre() {
		return titre;
	}
	public String getResume() {
		return resume;
	}
	public String getImage() {
		return image;
	}
	public String getIsbn() {
		return isbn;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public Categorie getCategorie() throws NullDataException {
		return Cache.getCategorie(this.categorie);
	}
	public Auteur getAuteur() throws NullDataException {
		return Cache.getAuteur(this.auteur);
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Setter">
	public void setId(int id) {
		this.id = id;
	}
	public void setTitre(String titre) throws RegExException {
		if (Pattern.matches(REGEX_TITRE, titre)) {
			this.titre = titre;
		} else { throw new RegExException("Livre.setTitre"); }
	}
	public void setResume(String resume) throws RegExException {
		if (Pattern.matches(REGEX_RESUME, resume)) {
			this.resume = resume;
		} else { throw new RegExException("Livre.setResume"); }
	}
	public void setImage(String image) throws RegExException {
		if (Pattern.matches(REGEX_IMAGE, image)) {
			this.image = image;
		} else { throw new RegExException("Livre.setImage"); }
	}
	public void setIsbn(String isbn) throws RegExException {
		if (Pattern.matches(REGEX_ISBN, isbn)) {
			this.isbn = isbn;
		} else { throw new RegExException("Livre.setIsbn"); }
	}
	public void setQuantite(int quantite) throws RegExException {
		if (quantite >= 0) {
			this.quantite = quantite;
		} else { throw new RegExException("Livre.setQuantite"); }
	}
	public void setCategorie(int categorie) throws RegExException {
		if (categorie > 0) {
			this.categorie = categorie;
		} else { throw new RegExException("Livre.setCategorie"); }
	}
	public void setAuteur(int auteur) throws RegExException {
		if (auteur > 0) {
			this.auteur = auteur;
		} else { throw new RegExException("Livre.setAuteur"); }
	}
//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Validateurs">
	/**
	 * Verifie la validite d'un objet Livre
	 * @param livre
	 * @return vrai ou faux
	 */
	public static final boolean ValiderLivre(Livre livre) throws NullDataException {
		return Pattern.matches(REGEX_TITRE, livre.getTitre())
				&& Pattern.matches(REGEX_RESUME, livre.getResume())
				&& Pattern.matches(REGEX_IMAGE, livre.getImage())
				&& Pattern.matches(REGEX_ISBN, livre.getImage())
				&& 0 <= livre.getQuantite()
				&& livre.getCategorie() != null
				&& livre.getAuteur() != null;
	}
	/**
	 * Verifie la validite d'un json Livre
	 * @param livreJson
	 * @return vrai ou faux
	 */
	public static final boolean ValiderJson(JSONObject livreJson) {
		return livreJson.has(JSON_TITRE) && livreJson.has(JSON_RESUME)
				&& livreJson.has(JSON_IMAGE) && livreJson.has(JSON_ISBN)
				&& livreJson.has(JSON_QUANTITE) && livreJson.has(JSON_CATEGORIE)
				&& livreJson.has(JSON_AUTEUR);
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Serialisation">
	@Override
	public String toString() {
		return this.getTitre();
	}
	public String toJson() {
		JSONObject json = new JSONObject(this);
		return json.toString();
	}
	//</editor-fold>
}