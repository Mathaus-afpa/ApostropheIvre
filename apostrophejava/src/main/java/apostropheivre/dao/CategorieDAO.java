package apostropheivre.dao;

import apostropheivre.models.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {

    // Utilisez BDDservice pour gérer les connexions
    private BDDservice bddService = BDDservice.getInstance();

    public void ajouter(Categorie categorie) throws SQLException {
        String sql = "INSERT INTO CATEGORIE (cat_libelle) VALUES (?)";

        try (Connection connection = bddService.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categorie.getLibelle());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categorie.setId(generatedKeys.getInt(1));
                }
            }
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
    }

    public void modifier(Categorie categorie) throws SQLException {
        String sql = "UPDATE CATEGORIE SET cat_libelle = ? WHERE cat_id = ?";

        try (Connection connection = bddService.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, categorie.getLibelle());
            stmt.setInt(2, categorie.getId());
            stmt.executeUpdate();
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM CATEGORIE WHERE cat_id = ?";

        try (Connection connection = bddService.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
    }

    public Integer trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM CATEGORIE WHERE cat_id = ?";

        try (Connection connection = bddService.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Categorie tempo = creerCategorieDepuisResultSet(rs);
                    return tempo.getId();
                }
            }
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
        return null;
    }

    public List<Categorie> listerTous() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIE";

        try (Connection connection = bddService.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(creerCategorieDepuisResultSet(rs));
            }
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
        return categories;
    }

    public List<Categorie> rechercherParLibelle(String libelle) throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIE WHERE LOWER(cat_libelle) LIKE LOWER(?)";

        try (Connection connection = bddService.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + libelle + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(creerCategorieDepuisResultSet(rs));
                }
            }
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
        return categories;
    }

    private Categorie creerCategorieDepuisResultSet(ResultSet rs) throws SQLException {
        Categorie categorie = new Categorie();
        categorie.setId(rs.getInt("cat_id"));
        categorie.setLibelle(rs.getString("cat_libelle"));
        return categorie;
    }

    public boolean libelleExiste(String libelle) throws SQLException {
        String sql = "SELECT COUNT(*) FROM CATEGORIE WHERE cat_libelle = ?";

        try (Connection connection = bddService.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, libelle);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } finally {
            bddService.closeConnection(); // Fermez la connexion après utilisation
        }
        return false;
    }
}