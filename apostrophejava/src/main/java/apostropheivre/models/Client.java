package apostropheivre.models;

import java.util.InputMismatchException;

public class Client {

	private Integer id;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String email;

	private final String regexNom = "^[a-zA-Zà-üÀ-Üß]+([\\s-][a-zA-Zà-üÀ-Üß]+)*$";
	private final String regexAdresse = "^[0-9]*([\\s-]|,\\s)?[a-zA-Zà-üÀ-Ü]+(([\\s'-]|,\\s)[0-9a-zA-Zà-üÀ-Ü]+)*$";
	private final String regexCodePostal = "^[0-9]{2}\\s?[0-9]{3}$";
	private final String regexVille = "^[a-zA-Zà-üÀ-Üß]+([\\s-][a-zA-Zà-üÀ-Üß]+)*$";
	private final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	public void setId(Integer id) throws IllegalArgumentException {
		if (id==null || id < -1) {
			throw new IllegalArgumentException();
		}
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}

	public void setNom(String nom) throws InputMismatchException {
		if (!nom.matches(regexNom)) {
			throw new InputMismatchException("Merci de saisir un nom valide.");
		}
		this.nom = nom;
	}
	public String getNom() {
		return this.nom;
	}

	public void setPrenom(String prenom) throws InputMismatchException {
		if (!prenom.matches(regexNom)){
			throw new InputMismatchException("Merci de saisir un prénom valide.");
		}
		this.prenom = prenom;
	}
	public String getPrenom() {
		return this.prenom;
	}

	public void setAdresse(String adresse) throws InputMismatchException {
		if (!adresse.matches(regexAdresse)) {
			throw new InputMismatchException("Merci de saisir une adresse valide");
		}
		this.adresse = adresse;
	}
	public String getAdresse(){
		return this.adresse;
	}

	public void setCodePostal(String codePostal) throws InputMismatchException {
		if (!codePostal.matches(regexCodePostal)) {
			throw new InputMismatchException("Merci de saisir un code postal valide");
		}
		this.codePostal = codePostal.replaceAll(" ","");
	}
	public String getCodePostal(){
		return this.codePostal;
	}

	public void setVille(String ville) throws InputMismatchException {
		if (!ville.matches(regexVille)) {
			throw new InputMismatchException("Merci de saisir un ville valide");
		}
		this.ville = ville;
	}
	public String getVille(){
		return this.ville;
	}

	public void setEmail(String email) throws InputMismatchException {
		if (!email.matches(regexEmail)) {
			throw new InputMismatchException("Merci de saisir un email valide");
		}
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}

	public String toString() {
		return("Nom : "+this.prenom+" "+this.nom+"<br/>"+"Adresse : "+this.adresse+", "+this.codePostal+" "+this.ville+
		"<br/>"+"Contact : "+this.email);
	}

	public Client(String nom, String prenom, String adresse, String codePostal, String ville, String email)
		throws InputMismatchException, NullPointerException, IllegalArgumentException {
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
		setCodePostal(codePostal);
		setVille(ville);
		setEmail(email);
	}
}