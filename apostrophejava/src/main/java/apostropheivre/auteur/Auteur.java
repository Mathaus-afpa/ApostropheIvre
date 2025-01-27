package apostropheivre.auteur;
import org.json.JSONObject;
/**
 * [Auteur.Auteur] - class
 * @author Mathaus
 */
public class Auteur {
	public static String JSON_ID = "id";
	public static String JSON_NOM = "nom";
	public static String JSON_PRENOM = "prenom";
	public static String JSON_URL = "url";
	//
	public Auteur() {}
	private String nom;
	private String prenom;
	private String url;
	private Integer id;

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = "../Images/auteurs/" + url;
	}

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public static final boolean ValiderAuteur(Auteur auteur) {
		//todo : tester Auteur.Auteur
		return true;
	}
	public static final boolean ValiderJson(JSONObject auteurJson) {
		return auteurJson.has("nom") && auteurJson.has("prenom") && auteurJson.has("url");
	}
}