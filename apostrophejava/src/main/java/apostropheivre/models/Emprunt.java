package apostropheivre.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Emprunt {

	private Integer id_client;
	private Integer id_livre;
	private Integer id_libraire;
	private Date date_emprunt;
	private Integer statut;

	public void setId_client(Integer id_client) throws InputMismatchException {
		if (id_client < 0) {
			throw new InputMismatchException("ID du client invalide");
		}
		this.id_client = id_client;
	}
	public int getId_client() {
		return this.id_client;
	}

	public void setId_livre(Integer id_livre) throws InputMismatchException {
		if (id_livre < 0) {
			throw new InputMismatchException("ID du livre invalide");
		}
		this.id_livre = id_livre;
	}
	public int getId_livre() {
		return this.id_livre;
	}

	public void setId_libraire(Integer id_libraire) throws InputMismatchException {
		if (id_libraire < 0) {
			throw new InputMismatchException("ID du libraire invalide");
		}
		this.id_libraire = id_libraire;
	}
	public int getId_libraire() {
		return this.id_libraire;
	}

	public void setDate_emprunt(Date date_emprunt) {
		this.date_emprunt = date_emprunt;
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
	public int getStatut() {
		return this.statut;
	}


	public Emprunt(Integer id_client, Integer id_livre, Integer id_libraire, Date date_emprunt, Integer statut) throws InputMismatchException {
		setId_client(id_client);
		setId_livre(id_livre);
		setId_libraire(id_libraire);
		setDate_emprunt(date_emprunt);
		setStatut(statut);
	}


	public Emprunt() {}
}