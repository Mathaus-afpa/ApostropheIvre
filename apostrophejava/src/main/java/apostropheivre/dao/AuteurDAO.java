package apostropheivre.dao;

import apostropheivre.models.Auteur;
import apostropheivre.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * [AuteurDAO] - class
 * @author Mathaus
 */
public class AuteurDAO extends DAOgenerale {
    public AuteurDAO() {}

    @Override
    public int create(Object obj) {
        return 0;
    }

    @Override
    public String update(Object obj, Integer pId) {
        return "";
    }

    @Override
    public String delete(Integer pId) {
        System.out.println("j'arrrrive");
        Connection conn = BDDservice.getInstance().getConnection();
        String query = "DELETE FROM AUTEUR WHERE aut_id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, pId.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e.getCause());
        }
        return "";
    }

    @Override
    public Object find(Integer pId) {
        Connection conn = BDDservice.getInstance().getConnection();
        String query = "SELECT aut_id, aut_nom, aut_prenom, aut_photo FROM AUTEUR WHERE aut_id = ?";
        PreparedStatement stmt = null;
        Auteur auteur = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, pId.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                auteur = new Auteur();
                auteur.setId(pId);
                auteur.setNom(rs.getString("aut_nom"));
                auteur.setPrenom(rs.getString("aut_prenom"));
                auteur.setUrl(rs.getString("aut_photo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            auteur = null;
            Log.error(e.getMessage(), e.getCause());
        }
        BDDservice.getInstance().closeConnection();
        return auteur;
    }

    @Override
    public List findAll() {
        Connection conn = BDDservice.getInstance().getConnection();
        String query = "SELECT aut_id, aut_nom, aut_prenom, aut_photo FROM AUTEUR";
        Statement stmt = null;
        List<Auteur> auteurs = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Auteur auteur = new Auteur();
                auteur = new Auteur();
                auteur.setId(rs.getInt("aut_id"));
                auteur.setNom(rs.getString("aut_nom"));
                auteur.setPrenom(rs.getString("aut_prenom"));
                auteur.setUrl(rs.getString("aut_photo"));
                auteurs.add(auteur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteurs;
    }
}