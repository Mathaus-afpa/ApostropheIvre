package apostropheivre.models;

import java.util.Objects;

public class Livre {
	private Integer id;
	private String titre;
	private String resume;
	private String image;
	private String isbn;
	private int quantite;
	//private Categorie categorie;
	//private Auteur auteur;
	private Integer idAuteur;
	private Integer idCategorie;

	// Constructeur par défaut
	public Livre() {
	}

	// Constructeur avec les champs principaux
	public Livre(String titre, String isbn, int quantite) {
		this.titre = titre;
		this.isbn = isbn;
		this.quantite = quantite;
	}

	// Constructeur complet
	public Livre(Integer id, String titre, String resume, String image,
				 String isbn, int quantite, Categorie categorie, Auteur auteur) {
		this.id = id;
		this.titre = titre;
		this.resume = resume;
		this.image = image;
		this.isbn = isbn;
		this.quantite = quantite;
		//this.categorie = categorie;
		//this.auteur = auteur;
	}

	// Constructeur complet
	public Livre(Integer id, String titre, String resume, String image,
				 String isbn, int quantite, Integer idCategorie, Integer idAuteur) {
		this.id = id;
		this.titre = titre;
		this.resume = resume;
		this.image = image;
		this.isbn = isbn;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
		this.idAuteur = idAuteur;
	}

	// Getters


	public Integer getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(Integer idAuteur) {
		this.idAuteur = idAuteur;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public Integer getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public String getResume() {
		return resume;
	}

	public String getImage() {
		return image;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getQuantite() {
		return quantite;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	// equals et hashCode basés sur l'ISBN qui est unique
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Livre livre = (Livre) o;
		return Objects.equals(isbn, livre.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public String toString() {
		return "Livre{" +
				"id=" + id +
				", titre='" + titre + '\'' +
				", isbn='" + isbn + '\'' +
				", quantite=" + quantite + '}';
	}
}
