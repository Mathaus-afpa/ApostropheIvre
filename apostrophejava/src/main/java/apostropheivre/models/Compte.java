
package apostropheivre.models;

public class Compte {
	private int id; // Identifiant unique
	private String login; // Nom d'utilisateur
	private String mail; // Adresse e-mail
	private String password; // Mot de passe (hashé)
	private int role; // Rôle (par exemple, 1 pour client, 2 pour admin, etc.)

	// Constructeurs
	public Compte() {}

	public Compte(String login, String mail, String password, int role) {
		this.login = login;
		this.mail = mail;
		this.password = password;
		this.role = role;
	}

	public Compte(int id, String login, String mail, String password, int role) {
		this.id = id;
		this.login = login;
		this.mail = mail;
		this.password = password;
		this.role = role;
	}

	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
