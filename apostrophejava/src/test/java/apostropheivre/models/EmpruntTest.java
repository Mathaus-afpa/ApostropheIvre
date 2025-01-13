package apostropheivre.models;

import apostropheivre.dao.BDDservice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmpruntTest {

    Emprunt empruntTest = new Emprunt(1,1,1, Date.valueOf(LocalDate.of(2025,10,25)),1);

    EmpruntTest() throws SQLException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

// ----------------------- TESTS ID CLIENT -----------------------

    @Test
    void setId_CliNullAssertFalse() {

        try {
            empruntTest.setId_client(null);
        } catch (Exception ignored) {}
        assertNotNull(empruntTest.getId_client());
    }

    @Test
    void setId_CliMinAssertTrue() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select min(cli_id) from client");
            while (rs.next()) {
                resultat = rs.getInt(1);
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_client(resultat);
        } catch (Exception ignored) {}
        assertEquals(resultat, empruntTest.getId_client());
    }

    @Test
    void setId_CliMaxAssertTrue() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select max(cli_id) from client");
            while (rs.next()) {
                resultat = rs.getInt(1);
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_client(resultat);
        } catch (Exception ignored) {}
        assertEquals(resultat, empruntTest.getId_client());
    }

    @Test
    void setId_CliAbsentAssertFalse() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select max(cli_id) from client");
            while (rs.next()) {
                resultat = rs.getInt(1)+1;
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_client(resultat);
        } catch (Exception ignored) {}
        assertNotEquals(resultat, empruntTest.getId_client());
    }

// ----------------------- TESTS ID LIVRE ------------------------

    @Test
    void setId_LivNullAssertFalse() {

        try {
            empruntTest.setId_livre(null);
        } catch (Exception ignored) {}
        assertNotNull(empruntTest.getId_livre());
    }

    @Test
    void setId_LivMinAssertTrue() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select min(liv_id) from livre");
            while (rs.next()) {
                resultat = rs.getInt(1);
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_livre(resultat);
        } catch (Exception ignored) {}
        assertEquals(resultat, empruntTest.getId_livre());
    }

    @Test
    void setId_LivMaxAssertTrue() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select max(liv_id) from livre");
            while (rs.next()) {
                resultat = rs.getInt(1);
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_livre(resultat);
        } catch (Exception ignored) {}
        assertEquals(resultat, empruntTest.getId_livre());
    }

    @Test
    void setId_LivAbsentAssertFalse() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select max(liv_id) from livre");
            while (rs.next()) {
                resultat = rs.getInt(1)+1;
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_livre(resultat);
        } catch (Exception ignored) {}
        assertNotEquals(resultat, empruntTest.getId_livre());
    }

// ----------------------- TESTS ID LIBRAIRE ---------------------

    @Test
    void setId_LibNullAssertFalse() {

        try {
            empruntTest.setId_libraire(null);
        } catch (Exception ignored) {}
        assertNotNull(empruntTest.getId_libraire());
    }

    @Test
    void setId_LibMinAssertTrue() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select min(lib_id) from libraire");
            while (rs.next()) {
                resultat = rs.getInt(1);
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_libraire(resultat);
        } catch (Exception ignored) {}
        assertEquals(resultat, empruntTest.getId_libraire());
    }

    @Test
    void setId_LibMaxAssertTrue() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select max(lib_id) from libraire");
            while (rs.next()) {
                resultat = rs.getInt(1);
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_libraire(resultat);
        } catch (Exception ignored) {}
        assertEquals(resultat, empruntTest.getId_libraire());
    }

    @Test
    void setId_LibAbsentAssertFalse() throws SQLException {

        int resultat=0;

        Connection con = BDDservice.getInstance().getConnection();
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("select max(lib_id) from libraire");
            while (rs.next()) {
                resultat = rs.getInt(1)+1;
            }
        } catch (SQLException ignored) {}
        BDDservice.getInstance().closeConnection();
        try {
            empruntTest.setId_libraire(resultat);
        } catch (Exception ignored) {}
        assertNotEquals(resultat, empruntTest.getId_libraire());
    }

// ----------------------- TESTS DATE EMPRUNT --------------------

    @Test
    void setDateNullAssertFalse() {
        try {
            empruntTest.setDate_emprunt(null);
        } catch (Exception ignored) {}
        assertNotNull((empruntTest.getDate_emprunt()));
    }

    @Test
    void setDateVieilleAssertFalse() {
        try {
            empruntTest.setDate_emprunt(Date.valueOf("2024-12-30"));
        } catch (Exception ignored) {}
        assertNotEquals(empruntTest.getDate_emprunt(), Date.valueOf("2024-12-30"));
    }

    @Test
    void setDateCorrecteAssertTrue() {
        try {
            empruntTest.setDate_emprunt(Date.valueOf("2025-12-30"));
        } catch (Exception ignored) {}
        assertEquals(empruntTest.getDate_emprunt(), Date.valueOf("2025-12-30"));
    }

// ----------------------- TESTS STATUT --------------------------

    @Test
    void setId_StatutNullAssertFalse() {

        try {
            empruntTest.setStatut(null);
        } catch (Exception ignored) {}
        assertNotNull(empruntTest.getStatut());
    }

    @Test
    void setStatutM1AssertFalse() {

        try {
            empruntTest.setStatut(-1);
        } catch (Exception ignored) {}
        assertFalse((empruntTest.getStatut() == -1));
    }

    @Test
    void setStatut0AssertTrue() {

        try {
            empruntTest.setStatut(0);
        } catch (Exception ignored) {}
        assertTrue((empruntTest.getStatut() == 0));
    }
    @Test
    void setStatut1AssertTrue() {

        try {
            empruntTest.setStatut(1);
        } catch (Exception ignored) {}
        assertTrue((empruntTest.getStatut() == 1));
    }
    @Test
    void setStatut2AssertTrue() {

        try {
            empruntTest.setStatut(2);
        } catch (Exception ignored) {}
        assertTrue((empruntTest.getStatut() == 2));
    }
    @Test
    void setStatut3AssertTrue() {

        try {
            empruntTest.setStatut(3);
        } catch (Exception ignored) {}
        assertTrue((empruntTest.getStatut() == 3));
    }
    @Test
    void setStatut4AssertTrue() {

        try {
            empruntTest.setStatut(4);
        } catch (Exception ignored) {}
        assertTrue((empruntTest.getStatut() == 4));
    }
    @Test
    void setStatut5AssertFalse() {

        try {
            empruntTest.setStatut(5);
        } catch (Exception ignored) {}
        assertFalse((empruntTest.getStatut() == 5));
    }
}