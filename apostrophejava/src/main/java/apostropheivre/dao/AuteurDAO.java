package apostropheivre.dao;
import apostropheivre.models.Auteur;
import apostropheivre.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 * [AuteurDAO] - class
 * @author Mathaus
 */
public class AuteurDAO extends DAOgenerale<Auteur> {
	public AuteurDAO() {}

	@Override
	public int create(Auteur obj) {
		Connection con = BDDservice.getInstance().getConnection();
		BDDservice.getInstance().closeConnection();
		return 0;
	}

	@Override
	public String update(Auteur obj, Integer pId) {
		Connection con = BDDservice.getInstance().getConnection();
		BDDservice.getInstance().closeConnection();
		return "";
	}

	@Override
	public String delete(Integer pId) {
		Connection con = BDDservice.getInstance().getConnection();
		BDDservice.getInstance().closeConnection();
		return "";
	}

	@Override
	public Auteur find(Integer pId) {
		Connection con = BDDservice.getInstance().getConnection();
		BDDservice.getInstance().closeConnection();
		return null;
	}

	@Override
	public List<Auteur> findAll() {
		Connection con = BDDservice.getInstance().getConnection();
		String query = "SELECT aut_id, aut_nom, aut_prenom, aut_photo FROM AUTEUR";
        Statement stmt = null;
		List<Auteur> auteurs = new ArrayList<>();
        try {
            stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Auteur auteur = new Auteur();
				auteur.setId(rs.getInt("aut_id"));
				auteur.setNom(rs.getString("aut_nom"));
				auteur.setPrenom(rs.getString("aut_prenom"));
				auteur.setUrl(rs.getString("aut_photo"));
				auteurs.add(auteur);
			}
        } catch (SQLException e) { Log.error(e.getMessage(), e.getCause()); }
		BDDservice.getInstance().closeConnection();
		return auteurs;
	}
}