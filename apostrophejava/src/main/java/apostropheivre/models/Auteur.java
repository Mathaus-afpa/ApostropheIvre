package apostropheivre.models;
public class Auteur {
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
		this.url = url;
	}

	private String nom;
	private String prenom;
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	public Auteur() {}
	public Auteur(String nom, String prenom, String url) {
			this.nom = nom;
			this.prenom = prenom;
			this.url = url;
	}

}