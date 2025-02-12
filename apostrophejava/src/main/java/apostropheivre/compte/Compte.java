package apostropheivre.compte;

import org.json.JSONObject;

public class Compte {
    public static String JSON_ID = "id";
    public static String JSON_LOGIN = "login";
    public static String JSON_MAIL = "mail";
    public static String JSON_PASSWORD = "password";
    public static String JSON_ROLE = "role";
    //
    public Compte() {}
    private Integer id;
    private String login;
    private String mail;
    private String password;
    private Integer role;

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getRole() { return role; }
    public void setRole(int role) { this.role = role; }

    public static final boolean ValiderCompte(Compte compte) {
        //todo : tester Compte.Compte
        return true;
    }

    public static final boolean ValiderJson(JSONObject compteJson) {
        return compteJson.has("login") && compteJson.has("mail") && compteJson.has("password");
    }

}
