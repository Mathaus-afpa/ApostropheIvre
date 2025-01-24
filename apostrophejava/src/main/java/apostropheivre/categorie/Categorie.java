package apostropheivre.categorie;
import org.json.JSONObject;
/**
 * [Categorie] - class
 * @author Mathaus
 */
public class Categorie {
	public static String JSON_ID = "id";
	public static String JSON_LIBELLE = "libelle";
	//
	public Categorie() {}
	private String libelle;
	private Integer id;
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static final boolean ValiderCategorie(Categorie categorie) {
		//todo:
		return true;
	}
	public static final boolean ValiderJson(JSONObject categorieJson) {
		return categorieJson.has("libelle");
	}
	@Override
	public String toString() {
		return this.getLibelle();
	}
	public String toJson() {
		JSONObject json = new JSONObject(this);
		return json.toString();
	}
}