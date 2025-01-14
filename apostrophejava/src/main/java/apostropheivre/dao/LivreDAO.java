package apostropheivre.dao;

import apostropheivre.models.Livre;
import apostropheivre.models.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {
    private Connection connection;

    public LivreDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouter(Livre livre) throws SQLException {
String sql = "INSERT INTO livre (liv_titre, liv_auteur, isbn, liv_resume, liv_quantite, cat_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getIsbn());
            stmt.setString(4, livre.getResume());
            stmt.setInt(5, livre.getQuantite());
            stmt.setInt(6, livre.getCategorie().getId());

            stmt.executeUpdate();
        }
    }

    public void modifier(Livre livre) throws SQLException {
String sql = "UPDATE livre SET liv_titre = ?, liv_auteur = ?, liv_resume = ?, liv_quantite = ?, cat_id = ? WHERE isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getResume());
            stmt.setInt(4, livre.getQuantite());
            stmt.setInt(5, livre.getCategorie().getId());
            stmt.setString(6, livre.getIsbn());

            stmt.executeUpdate();
        }
    }

    public void supprimer(String isbn) throws SQLException {
        String sql = "DELETE FROM livre WHERE isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        }
    }

    public Livre trouverParIsbn(String isbn) throws SQLException {
        String sql = "SELECT l.*, c.cat_id as cat_id, c.cat_libelle as cat_nom FROM livre l " +
                "JOIN categorie c ON l.cat_id = c.cat_id " +
                "WHERE l.isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return creerLivreDepuisResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Livre> listerTous() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT l.*, c.cat_id as cat_id, c.cat_libelle as cat_nom FROM livre l " +
                "JOIN categorie c ON l.cat_id = c.cat_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                livres.add(creerLivreDepuisResultSet(rs));
            }
        }
        return livres;
    }

    public List<Livre> rechercherParTitre(String titre) throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT l.*, c.cat_id as cat_id, c.cat_libelle as cat_nom FROM livre l " +
                "JOIN categorie c ON l.cat_id = c.cat_id " +
                "WHERE LOWER(l.liv_titre) LIKE LOWER(?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + titre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    livres.add(creerLivreDepuisResultSet(rs));
                }
            }
        }
        return livres;
    }

    private Livre creerLivreDepuisResultSet(ResultSet rs) throws SQLException {
        Categorie categorie = new Categorie(
                rs.getInt("cat_id"),
                rs.getString("cat_nom")
        );

        return new Livre(
                rs.getString("titre"),
                rs.getString("auteur"),
                rs.getString("isbn"),
                rs.getString("resume"),
                rs.getInt("quantite"),
                categorie
        );
    }

    public boolean isbnExiste(String isbn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM livres WHERE isbn = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
