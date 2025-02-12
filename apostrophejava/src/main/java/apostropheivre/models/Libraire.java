package apostropheivre.models;

import java.util.InputMismatchException;

public class Libraire {

	private Integer id;
	private String lib_nom;
	private String lib_prenom;

	private final String regexNom = "^[a-zA-Zà-üÀ-Ü\\s-]+$";
	private final String regexPrenom = "^[a-zA-Zà-üÀ-Ü\\s-]+$";

	public Libraire(Integer lib_id, String lib_nom, String lib_prenom) throws InputMismatchException,
			NullPointerException, IllegalArgumentException {
		setLib_nom(lib_nom);
		setLib_prenom(lib_prenom);
	}

	public Libraire(){}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getLib_nom() {
		return lib_nom;
	}

	public void setLib_nom(String lib_nom) throws InputMismatchException {

		if (!lib_nom.matches(regexNom)) {
			throw new InputMismatchException("Merci de saisir un nom valide.");
		}

		this.lib_nom = lib_nom;

	}

	public String getLib_prenom() {
		return lib_prenom;
	}

	public void setLib_prenom(String lib_prenom) {

		if (!lib_prenom.matches(regexPrenom)) {
			throw new InputMismatchException("Merci de saisir un prénom valide.");
		}

		this.lib_prenom = lib_prenom;

	}

}