package apostropheivre.dao;

import apostropheivre.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends DAOgenerale<Client> {

    @Override
    public int create(Client obj) {
        StringBuilder insertSQL = new StringBuilder("insert into Client (cli_nom, cli_prenom, cli_adresse, cli_code_postal, " +
                "cli_ville, cli_email) values (?,?,?,?,?,?)");
        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertSQL.toString(),PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getPrenom());
            pstmt.setString(3, obj.getAdresse());
            pstmt.setString(4, obj.getCodePostal());
            pstmt.setString(5, obj.getVille());
            pstmt.setString(6, obj.getEmail());


            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            int newId=-1;

            if(rs.next()) {
                newId = rs.getInt(1);
            }

            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    @Override
    public String update(Client obj, Integer pId) {
        StringBuilder updateSQL = new StringBuilder("update Client set cli_nom=?, cli_prenom=?, cli_Adresse=?, cli_code_postal=?," +
                "cli_ville=?, cli_email=? where cli_id = ?");
        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateSQL.toString());

            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getPrenom());
            pstmt.setString(3, obj.getAdresse());
            pstmt.setString(4, obj.getCodePostal());
            pstmt.setString(5, obj.getVille());
            pstmt.setString(6, obj.getEmail());
            pstmt.setInt(7, pId);

            pstmt.executeUpdate();

            return("Client mis à jour avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    @Override
    public String delete(Integer pId) {
        StringBuilder deleteSQL = new StringBuilder("delete from Client where cli_id=?");

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(deleteSQL.toString());

            pstmt.setInt(1, pId);

            pstmt.executeUpdate();

            return ("Abonne supprimé avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    @Override
    public Client find(Integer pId) {

        StringBuilder selectById = new StringBuilder("select * from client where cli_id=?");

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(selectById.toString());

            pstmt.setInt(1,pId);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                return new Client(
                resultSet.getString("Cli_nom"),
                resultSet.getString("Cli_prenom"),
                resultSet.getString("Cli_Adresse"),
                resultSet.getString("Cli_Code_Postal"),
                resultSet.getString("Cli_Ville"),
                resultSet.getString("Cli_Email")
                );
            }

            throw new SQLException("Ce client n'existe pas");
            // TODO : page erreur inexistence du client

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    @Override
    public List<Client> findAll() {
        String selectSQL = "select * from client";
        List<Client> listCli = new ArrayList<>();

        try {
            Connection con = BDDservice.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()) {
                Client client = new Client(
                resultSet.getString("Cli_nom"),
                resultSet.getString("Cli_prenom"),
                resultSet.getString("Cli_Adresse"),
                resultSet.getString("Cli_Code_Postal"),
                resultSet.getString("Cli_Ville"),
                resultSet.getString("Cli_Email")
                );
                client.setId(resultSet.getInt("cli_id"));

                listCli.add(client);
            }

            return listCli;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            BDDservice.getInstance().closeConnection();
        }

    }
}
