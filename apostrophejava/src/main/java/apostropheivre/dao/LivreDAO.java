package apostropheivre.dao;

import apostropheivre.models.Livre;
import apostropheivre.models.Categorie;
import apostropheivre.models.Auteur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {


    public LivreDAO() {
    }

    public void ajouter(Livre livre) throws SQLException {
        String sql = "INSERT INTO LIVRE (liv_titre, liv_resume, liv_image, liv_isbn, " +
                "liv_quantite, cat_id, aut_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getResume());
            stmt.setString(3, livre.getImage());
            stmt.setString(4, livre.getIsbn());
            stmt.setInt(5, livre.getQuantite());
            stmt.setObject(6, livre.getIdCategorie());
            stmt.setObject(7, livre.getIdAuteur());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    livre.setId(generatedKeys.getInt(1));
                }
            } catch (Exception ex) {
                throw new SQLException(ex.getMessage());
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    public void modifier(Livre livre) throws SQLException {
        String sql = "UPDATE LIVRE SET liv_titre = ?, liv_resume = ?, liv_image = ?, liv_quantite = ?, " +
                "cat_id = ?, aut_id = ? WHERE liv_isbn = ?";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getResume());
            stmt.setString(3, livre.getImage());
            stmt.setInt(4, livre.getQuantite());
            stmt.setObject(5, livre.getIdCategorie());
            stmt.setObject(6, livre.getIdAuteur());
            stmt.setString(7, livre.getIsbn());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    public void supprimer(String isbn) throws SQLException {
        String sql = "DELETE FROM LIVRE WHERE liv_isbn = ?";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, isbn);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    public Livre trouverParIsbn(String isbn) throws SQLException {
        String sql = "SELECT * FROM LIVRE WHERE liv_isbn = ?;";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return creerLivreDepuisResultSet(rs);
                }
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
        return null;
    }

    public Livre trouverParId(Integer id) throws SQLException {
        String sql = "SELECT * FROM LIVRE WHERE liv_id = ?";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return creerLivreDepuisResultSet(rs);
                }
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
        return null;
    }

    public List<Livre> listerTous() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT l.* FROM LIVRE l;";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                livres.add(creerLivreDepuisResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
        return livres;
    }

    public List<Livre> rechercherParTitre(String titre) throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT l.* " +
                "FROM LIVRE l " +
                "WHERE LOWER(l.liv_titre) LIKE LOWER(?)";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + titre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    livres.add(creerLivreDepuisResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
        return livres;
    }

    private Livre creerLivreDepuisResultSet(ResultSet rs) throws SQLException {
        Livre livre = new Livre();
        livre.setId(rs.getInt("liv_id"));
        livre.setTitre(rs.getString("liv_titre"));
        livre.setResume(rs.getString("liv_resume"));
        livre.setImage(rs.getString("liv_image"));
        livre.setIsbn(rs.getString("liv_isbn"));
        livre.setQuantite(rs.getInt("liv_quantite"));
        livre.setIdCategorie(rs.getInt("cat_id"));
        livre.setIdAuteur(rs.getInt("aut_id"));

        return livre;
    }

    public boolean isbnExiste(String isbn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM LIVRE WHERE liv_isbn = ?";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return false;
    }
}
// TODO (pas ici) : quand on passe d'une page appelant la BDD à une autre, ne fonctionne pas