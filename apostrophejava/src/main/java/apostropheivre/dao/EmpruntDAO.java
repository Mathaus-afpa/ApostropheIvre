package apostropheivre.dao;

import apostropheivre.models.Emprunt;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class EmpruntDAO extends DAOgenerale<Emprunt> {

    @Override
    public int create(Emprunt obj) {
        StringBuilder insertSQL = new StringBuilder("insert into Emprunter (cli_id, liv_id, lib_id, date_emprunt, statut values (?,?,?,?,?)");
        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertSQL.toString());
            pstmt.setInt(1, obj.getId_client());
            pstmt.setInt(2, obj.getId_livre());
            pstmt.setInt(3, obj.getId_libraire());
            pstmt.setDate(4, obj.getDate_emprunt());
            pstmt.setInt(5,obj.getStatut());

            pstmt.executeUpdate();

            BDDservice.getInstance().closeConnection();
            return (1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String update(Emprunt obj, Integer pId) {
        return "";
    }

    @Override
    public String delete(Integer pId) {
        return "";
    }

    @Override
    public Emprunt find(Integer pId) {
        return null;
    }

    @Override
    public List<Emprunt> findAll() {

        String selectSQL = "select * from emprunter";
        List<Emprunt> listEmp = Arrays.asList();

        try {
            Connection con = BDDservice.getInstance().getConnection();

            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()) {
                listEmp.add(find(resultSet.getInt("cli_id"), resultSet.getInt("liv_id"), resultSet.getInt("lib_id")));
            }

            BDDservice.getInstance().closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listEmp;
    }

    public String update(Emprunt obj, Integer nCli, Integer nLiv, Integer nLib, Date nDate, Integer nStatut) {
        StringBuilder updateSQL = new StringBuilder("update Emprunter set cli_id=?, liv_id=?, lib_id=?, date_emprunt=?, statut=?" +
                "where cli_id = ? AND liv_id = ? AND lib_id = ?");
        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateSQL.toString());
            pstmt.setInt(1, nCli);
            pstmt.setInt(2, nLiv);
            pstmt.setInt(3, nLib);
            pstmt.setDate(4, nDate);
            pstmt.setInt(5, nStatut);
            pstmt.setInt(6, obj.getId_client());
            pstmt.setInt(7, obj.getId_livre());
            pstmt.setInt(8, obj.getId_libraire());

            pstmt.executeUpdate();

            BDDservice.getInstance().closeConnection();
            return ("Emprunt mis à jour avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String delete(Emprunt obj) {
        StringBuilder deleteSQL = new StringBuilder("delete from Emprunter where cli_id=? AND liv_id=? AND lib_id=?");

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getId_client());
            pstmt.setInt(2, obj.getId_livre());
            pstmt.setInt(3, obj.getId_libraire());

            BDDservice.getInstance().closeConnection();
            return("Suppression de l'emprunt effectuée");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Emprunt find(Integer cli, Integer liv, Integer lib) throws SQLException {
        Emprunt emprunt = new Emprunt(null, null, null, null, null);
        StringBuilder selectById = new StringBuilder("select * from Emprunter where cli_id=? AND liv_id=? AND lib_id=?");

        try{
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt=con.prepareStatement(selectById.toString());
            pstmt.setInt(1, cli);
            pstmt.setInt(2, liv);
            pstmt.setInt(3, lib);

            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                emprunt.setId_client(rs.getInt("cli_id"));
                emprunt.setId_livre(rs.getInt("liv_id"));
                emprunt.setId_libraire(rs.getInt("lib_id"));
            }

            BDDservice.getInstance().closeConnection();

            return emprunt;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
