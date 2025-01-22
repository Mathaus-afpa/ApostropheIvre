package apostropheivre.dao;
import apostropheivre.ApostropheIvre;
import apostropheivre.models.Auteur;
import apostropheivre.utils.Json;
import apostropheivre.utils.Log;
import org.json.JSONObject;

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
		JSONObject json = new JSONObject(obj.toString());
		System.out.println(json.toString());
		Connection conn = BDDservice.getInstance().getConnection();
		PreparedStatement stmt = null;
		int generatedId = -1;

		try {
			// Requête d'insertion
			String query = "INSERT INTO AUTEUR (aut_nom, aut_prenom, aut_photo) VALUES (?, ?, ?)";
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			// Paramètres de la requête
			stmt.setString(1, json.getString("nomAuteur"));
			stmt.setString(2, json.getString("prenomAuteur"));
			stmt.setString(3, json.getString("url"));

			// Exécution de l'insertion
			int rowsInserted = stmt.executeUpdate();

			// Si une ligne a été insérée, récupérer l'ID généré
			if (rowsInserted > 0) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getInt(1); // ID généré
				}
			}

		} catch (SQLException e) {
			Log.error(e.getMessage(), e.getCause());
		}
		ApostropheIvre.AUTEURS = fasterUpdate(conn);
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
			String query = "UPDATE AUTEUR SET aut_nom = ?, aut_prenom = ?, aut_photo = ? WHERE aut_id = ?";
			stmt = conn.prepareStatement(query);

			// Paramètres de la requête
			stmt.setString(1, json.getString("prenomAuteur"));
			stmt.setString(2, json.getString("nomAuteur"));
			stmt.setString(3, json.getString("url"));
			stmt.setInt(4, json.getInt("idAuteur"));
			// Exécution de la mise à jour
			int rowsUpdated = stmt.executeUpdate();
		} catch (SQLException e) {
			Log.error(e.getMessage(), e.getCause());
		}
		ApostropheIvre.AUTEURS = fasterUpdate(conn);
		BDDservice.getInstance().closeConnection();
		return "";
	}

	@Override
	public String delete(Integer pId) {
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
		ApostropheIvre.AUTEURS = fasterUpdate(conn);
		BDDservice.getInstance().closeConnection();
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
		ApostropheIvre.AUTEURS = fasterUpdate(conn);
		BDDservice.getInstance().closeConnection();
		return ApostropheIvre.AUTEURS;
	}

	private List fasterUpdate(Connection conn) {
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