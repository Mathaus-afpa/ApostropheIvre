package apostropheivre.dao;

import apostropheivre.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientDAO extends DAOgenerale<Client> {

    @Override
    public int create(Client obj) {
        StringBuilder insertSQL = new StringBuilder("insert into Client (cli_nom, cli_prenom, cli_adresse, cli_codepostal, " +
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

            BDDservice.getInstance().closeConnection();
            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String update(Client obj, Integer pId) {
        StringBuilder updateSQL = new StringBuilder("update Client set cli_nom=?, cli_prenom=?, cli_Adresse=?, cli_codepostal=?," +
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

            BDDservice.getInstance().closeConnection();
            return("Client mis à jour avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

            BDDservice.getInstance().closeConnection();
            return ("Abonne supprimé avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client find(Integer pId) {
        Client client = new Client("a", "a", "1a", "00001", "A", "aa@aa.aa");

        StringBuilder selectById = new StringBuilder("select * from client where cli_id=?");

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(selectById.toString());
            pstmt.setInt(1,pId);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                client.setNom(resultSet.getString("Cli_nom"));
                client.setPrenom(resultSet.getString("Cli_prenom"));
                client.setAdresse(resultSet.getString("Cli_Adresse"));
                client.setCodePostal(resultSet.getString("Cli_Code_Postal"));
                client.setVille(resultSet.getString("Cli_Ville"));
                client.setEmail(resultSet.getString("Cli_Email"));
            }

            BDDservice.getInstance().closeConnection();
            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List findAll() {
        String selectSQL = "select * from client";
        List<Client> listCli = new ArrayList<>();

        try {
            Connection con = BDDservice.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()) {
                Client client = new Client("a", "a", "1a", "00001", "A", "aa@aa.aa");

                client.setNom(resultSet.getString("Cli_nom"));
                client.setPrenom(resultSet.getString("Cli_prenom"));
                client.setAdresse(resultSet.getString("Cli_Adresse"));
                client.setCodePostal(resultSet.getString("Cli_Code_Postal"));
                client.setVille(resultSet.getString("Cli_Ville"));
                client.setEmail(resultSet.getString("Cli_Email"));

                listCli.add(client);
            }

            BDDservice.getInstance().closeConnection();

            return listCli;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
