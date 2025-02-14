package apostropheivre.dao;

import apostropheivre.models.Emprunt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmpruntDAO extends DAOgenerale<Emprunt> {

    @Override
    public int create(Emprunt obj) {
        StringBuilder insertSQL = new StringBuilder("insert into Emprunter (cli_id, liv_id, lib_id, date_emprunt, statut) values (?,?,?,?,?)");
        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertSQL.toString());

            pstmt.setInt(1, obj.getId_client());
            pstmt.setInt(2, obj.getId_livre());
            pstmt.setInt(3, obj.getId_libraire());
            pstmt.setDate(4, obj.getDate_emprunt());
            pstmt.setInt(5,obj.getStatut());

            pstmt.executeUpdate();

            return (1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
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
        List<Emprunt> listEmp = new ArrayList<>();
        Connection con = BDDservice.getInstance().getConnection();

        try  {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()) {
                Emprunt emprunt = new Emprunt (
                        resultSet.getInt("cli_id"),
                        resultSet.getInt("liv_id"),
                        resultSet.getInt("lib_id"),
                        resultSet.getDate("date_emprunt"),
                        resultSet.getInt("statut")
                );
                listEmp.add(emprunt);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            BDDservice.getInstance().closeConnection();
        }
        return listEmp;
    }

    public String update(Emprunt obj, Integer cli, Integer liv, Integer lib) {
        StringBuilder updateSQL = new StringBuilder("update Emprunter set cli_id=?, liv_id=?, lib_id=?, date_emprunt=?, statut=? " +
                "where cli_id = ? AND liv_id = ? AND lib_id = ?");

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateSQL.toString());

            pstmt.setInt(1, obj.getId_client());
            pstmt.setInt(2, obj.getId_livre());
            pstmt.setInt(3, obj.getId_libraire());
            pstmt.setDate(4, obj.getDate_emprunt());
            pstmt.setInt(5, obj.getStatut());
            pstmt.setInt(6, cli);
            pstmt.setInt(7, liv);
            pstmt.setInt(8, lib);

            pstmt.executeUpdate();

            return ("Emprunt mis à jour avec succès.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    public String delete(Integer id_cli, Integer id_liv, Integer id_lib) {
        StringBuilder deleteSQL = new StringBuilder("delete from Emprunter where cli_id=? AND liv_id=? AND lib_id=?");

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(deleteSQL.toString());

            pstmt.setInt(1, id_cli);
            pstmt.setInt(2, id_liv);
            pstmt.setInt(3, id_lib);

            pstmt.executeUpdate();

            return("Suppression de l'emprunt effectuée");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }

    public Emprunt find(Integer cli, Integer liv, Integer lib) throws SQLException {
        String selectById = "select * from Emprunter where cli_id=? AND liv_id=? AND lib_id=?";

        try {
            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt=con.prepareStatement(selectById);
            pstmt.setInt(1, cli);
            pstmt.setInt(2, liv);
            pstmt.setInt(3, lib);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    return new Emprunt(rs.getInt("cli_id"), rs.getInt("liv_id"), rs.getInt("lib_id"),
                            rs.getDate("date_emprunt"), rs.getInt("statut"));
                }
                return null;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            BDDservice.getInstance().closeConnection();
        }
    }
}
