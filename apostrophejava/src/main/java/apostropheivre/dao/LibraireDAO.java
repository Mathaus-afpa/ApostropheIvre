package apostropheivre.dao;

import apostropheivre.models.Libraire;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class LibraireDAO extends DAOgenerale<Libraire> {

    @Override
    public int create(Libraire obj) {

        StringBuilder insertSQL = new StringBuilder("insert into Libraire (lib_nom, lib_prenom) values (?,?)");

        try {

            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertSQL.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getLib_nom());
            pstmt.setString(2, obj.getLib_prenom());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            int newId=-1;

            if(rs.next()) {
                newId = rs.getInt(1);
            }

            BDDservice.getInstance().closeConnection();

            return newId;

        } catch (SQLException e) {

            BDDservice.getInstance().closeConnection();

            throw new RuntimeException(e);

        }

    }

    @Override
    public String update(Libraire obj, Integer pId) {

        StringBuilder updateSQL = new StringBuilder("update Libraire set lib_nom=?, lib_prenom=? where lib_id = ?");

        try {

            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getLib_nom());
            pstmt.setString(2, obj.getLib_prenom());
            pstmt.setInt(7, pId);

            pstmt.executeUpdate();

            BDDservice.getInstance().closeConnection();

            return("Libraire mis à jour avec succès.");

        } catch (SQLException e) {

            BDDservice.getInstance().closeConnection();

            throw new RuntimeException(e);

        }

    }

    @Override
    public String delete(Integer pId) {

        StringBuilder deleteSQL = new StringBuilder("delete from Libraire where Lib_id=?");

        try {
            Connection con = BDDservice.getInstance().getConnection();

            PreparedStatement pstmt = con.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, pId);

            pstmt.executeUpdate();

            BDDservice.getInstance().closeConnection();

            return ("Libraire supprimé avec succès");

        } catch (SQLException e) {

            BDDservice.getInstance().closeConnection();

            throw new RuntimeException(e);

        }

    }

    @Override
    public Libraire find(Integer pId) {

        Libraire libraire = new Libraire(null, null, null);

        StringBuilder selectById = new StringBuilder("select * from libraire where lib_id=?");

        try {

            Connection con = BDDservice.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(selectById.toString());
            pstmt.setInt(1,pId);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {

                libraire.setLib_nom(resultSet.getString("Lib_nom"));
                libraire.setLib_prenom(resultSet.getString("Lib_prenom"));

            }

            BDDservice.getInstance().closeConnection();

            return libraire;

        } catch (SQLException e) {

            BDDservice.getInstance().closeConnection();

            throw new RuntimeException(e);

        }

    }

    @Override
    public List findAll() {

        String selectSQL = "select id_Libraire from libraire";

        List<Libraire> listLib = Arrays.asList();

        try {
            Connection con = BDDservice.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()) {
                listLib.add(find(resultSet.getInt("id_Libraire")));
            }

            BDDservice.getInstance().closeConnection();

            return listLib;

        } catch (SQLException e) {

            BDDservice.getInstance().closeConnection();

            throw new RuntimeException(e);

        }

    }

}
