package apostropheivre.models;

import java.util.InputMismatchException;

public class Client {

	private String ndf;
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

	public void setNdf(String ndf) throws InputMismatchException {
		if (!ndf.matches(regexNom)) {
			throw new InputMismatchException("Merci de saisir un nom valide.");
		}
		this.ndf = ndf;
	}
	public String getNdf() {
		return this.ndf;
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

	public Client(String ndf, String prenom, String adresse, String codePostal, String ville, String email)
		throws InputMismatchException, NullPointerException, IllegalArgumentException {
		setNdf(ndf);
		setPrenom(prenom);
		setAdresse(adresse);
		setCodePostal(codePostal);
		setVille(ville);
		setEmail(email);
	}
}