package apostropheivre.models;
public class Auteur {

	private String aut_nom;
	private Byte aut_photo;

	public Auteur(Integer aut_id, String aut_nom, Byte aut_photo) {
		setAut_nom(aut_nom);
		setAut_photo(aut_photo);
	}

	public String getAut_nom() {
		return aut_nom;
	}

	public void setAut_nom(String aut_nom) {
		this.aut_nom = aut_nom;
	}

	public Byte getAut_photo() {
		return aut_photo;
	}

	public void setAut_photo(Byte aut_photo) {
		this.aut_photo = aut_photo;
	}

}