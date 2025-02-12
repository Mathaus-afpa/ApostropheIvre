package apostropheivre.models;

import apostropheivre.dao.BDDservice;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.InputMismatchException;

public class Emprunt {

	private Integer id_client;
	private Integer id_livre;
	private Integer id_libraire;
	private Date date_emprunt;
	private Integer statut;

	public void setId_client(Integer id_client) throws InputMismatchException, SQLException {
		/*
		int resultat=0;

		Connection con = BDDservice.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select count(1) from client where cli_id=?");
		pstmt.setInt(1, id_client);
		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat = rs.getInt(1);
			}
		} catch (SQLException ex) {}
		BDDservice.getInstance().closeConnection();
        if (resultat == 0) {
            throw new InputMismatchException("Cet id de client n'existe pas dans la base de données.");
		} else {
            this.id_client = id_client;
		}

 */
		this.id_client = id_client;
	}
	public Integer getId_client() {
		return this.id_client;
	}

	public void setId_livre(Integer id_livre) throws InputMismatchException, SQLException {
		/*
		int resultat=0;

		Connection con = BDDservice.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement("select count(1) from livre where liv_id=?");
		pstmt.setInt(1, id_livre);
		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat = rs.getInt(1);
			}
		} catch (SQLException ex) {}
		BDDservice.getInstance().closeConnection();
		if (resultat == 0) {
			throw new InputMismatchException("Cet id de livre n'existe pas dans la base de données.");
		} else {
			this.id_livre = id_livre;
		}

 */
		this.id_livre = id_livre;
	}
	public Integer getId_livre() {
		return this.id_livre;
	}

	public void setId_libraire(Integer id_libraire) throws InputMismatchException, SQLException {
		/*
		int resultat=0;

		Connection con = BDDservice.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement("select count(1) from libraire where lib_id=?");
		pstmt.setInt(1, id_libraire);
		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				resultat = rs.getInt(1);
			}
		} catch (SQLException ex) {}
		BDDservice.getInstance().closeConnection();
		if (resultat == 0) {
			throw new InputMismatchException("Cet id de libraire n'existe pas dans la base de données.");
		} else {
			this.id_libraire = id_libraire;
		}

		 */
		this.id_libraire = id_libraire;
	}
	public Integer getId_libraire() {
		return this.id_libraire;
	}

	public void setDate_emprunt(Date date_emprunt) {
        if (date_emprunt == null || date_emprunt.before(Date.valueOf("2025-01-09"))) {
            throw new IllegalArgumentException("Date invalide");
        }
        try {
            this.date_emprunt = date_emprunt;
        } catch (IllegalArgumentException ex) {
        }
    }
	public Date getDate_emprunt() {
		return this.date_emprunt;
	}

	public void setStatut(Integer statut) throws InputMismatchException {
		if (statut < 0 || statut > 4) {
			throw new InputMismatchException("Statut invalide : doit être entre 0 et 4 inclus.");
		}
		this.statut = statut;
	}
	public Integer getStatut() {
		return this.statut;
	}


	public Emprunt(Integer id_client, Integer id_livre, Integer id_libraire, Date date_emprunt, Integer statut) throws InputMismatchException, SQLException {
		setId_client(id_client);
		setId_livre(id_livre);
		setId_libraire(id_libraire);
		setDate_emprunt(date_emprunt);
		setStatut(statut);
	}


	public Emprunt() {}
}