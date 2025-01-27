package apostropheivre.dao;

import apostropheivre.ApostropheIvre;
import apostropheivre.models.Compte;
import apostropheivre.utils.Log;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOcompte extends DAOgenerale {

    public DAOcompte() {}

    @Override
    public int create(Object obj) {

        JSONObject json = new JSONObject(obj.toString());
        System.out.println(json.toString());
        Connection conn = BDDservice.getInstance().getConnection();
        PreparedStatement stmt = null;

        int generatedId = -1;

        try {
            // Requête d'insertion
            String query = "INSERT INTO USERS (login, mail, password, role) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Paramètres de la requête
            stmt.setString(1, json.getString("login"));
            stmt.setString(2, json.getString("mail"));
            stmt.setString(3, json.getString("password"));
            stmt.setInt(4, json.getInt("role"));


            // Exécution de l'insertion
            int rowsInserted = stmt.executeUpdate();

            // Si une ligne a été insérée, récupérer l'ID généré
            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // ID généré
                }
            }
            Log.info("Tentative de création d'un compte avec les données : login=" + json.getString("login") +
                    ", mail=" + json.getString("mail") + ", role=" + json.getInt("role"));

        } catch (SQLException e) {
            Log.error(e.getMessage(), e.getCause());

        }

        ApostropheIvre.USERS = fasterUpdate(conn);
        BDDservice.getInstance().closeConnection();

        // Retourner l'ID généré ou -1 si l'insertion a échoué
        return generatedId;
    }

    @Override
    public String update(Object obj, Integer pId) {
        JSONObject json = new JSONObject(obj.toString());
        Connection conn = BDDservice.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE users SET login = ?, mail = ?, password = ?, role = " +
                    "? WHERE id = ?";
            stmt = conn.prepareStatement(query);

            // Paramètres de la requête
            stmt.setString(1, json.getString("login"));
            stmt.setString(2, json.getString("mail"));
            stmt.setString(3, json.getString("password"));
            stmt.setInt(4, json.getInt("role"));
            stmt.setInt(5, pId);


            // Exécution de la mise à jour
            stmt.executeUpdate();

        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
        return "";
    }

    @Override
    public String delete(Integer pId) {
        Connection conn = BDDservice.getInstance().getConnection();
        String query = "DELETE FROM usersid = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, pId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
        return "";
    }

    @Override
    public Object find(Integer pId) {
        Connection conn = BDDservice.getInstance().getConnection();
        String query = "SELECT id, login,mail, password, role FROM USERS WHERE " +
                "compte_id = ?";
        PreparedStatement stmt = null;
        Compte compte = null;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, pId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                compte = new Compte();
                compte.setId(rs.getInt("id"));
                compte.setLogin(rs.getString("login"));
                compte.setMail(rs.getString("mail"));
                compte.setPassword(rs.getString("password"));
                compte.setRole(rs.getInt("role"));
            }

        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }

        return compte;
    }

    @Override
    public List findAll() {
        Connection conn = BDDservice.getInstance().getConnection();
        ApostropheIvre.USERS = fasterUpdate(conn);
        BDDservice.getInstance().closeConnection();
        return ApostropheIvre.USERS;

    }

    private List fasterUpdate(Connection conn) {
        String query = "SELECT id, login, mail, role FROM USERS";
        Statement stmt = null;
        List<Compte> users = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Compte compte = new Compte();
                compte = new Compte();
                compte.setId(rs.getInt("id"));
                compte.setLogin(rs.getString("login"));
                compte.setMail(rs.getString("mail"));
                compte.setPassword(rs.getString("password"));
                users.add(compte);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
