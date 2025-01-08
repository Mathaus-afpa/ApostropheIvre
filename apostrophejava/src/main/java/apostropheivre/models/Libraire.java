package apostropheivre.models;

public class Libraire {

	private String lib_nom;
	private String lib_prenom;

	public Libraire(Integer lib_id, String lib_nom, String lib_prenom) {
		this.lib_nom = lib_nom;
		this.lib_prenom = lib_prenom;
	}

	public String getLib_nom() {
		return lib_nom;
	}

	public void setLib_nom(String lib_nom) {
		this.lib_nom = lib_nom;
	}

	public String getLib_prenom() {
		return lib_prenom;
	}

	public void setLib_prenom(String lib_prenom) {
		this.lib_prenom = lib_prenom;
	}
	
}