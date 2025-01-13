package apostropheivre.models;
public class Livre {
	public Livre(){}
	private String titre;
	private String auteur;
	private String isbn;
	private String resume;
	private int quantite;
	private Categorie categorie;

	// Constructeur
	public Livre(String titre, String auteur, String isbn, String resume, int quantite, Categorie categorie) {
		setTitre(titre);
		setAuteur(auteur);
		setIsbn(isbn);
		this.resume = resume;
		setQuantite(quantite);
		this.categorie = categorie;
	}

	// Getters et setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		if (titre == null || titre.trim().isEmpty()) {
			throw new IllegalArgumentException("Le titre ne peut pas être vide");
		}
		if (titre.length() > 100) {
			throw new IllegalArgumentException("Le titre ne peut pas dépasser 100 caractères");
		}
		this.titre = titre.trim();
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		if (auteur == null || auteur.trim().isEmpty()) {
			throw new IllegalArgumentException("L'auteur ne peut pas être vide");
		}
		if (!auteur.matches("^[a-zA-ZÀ-ÿ\\s-]{2,50}$")) {
			throw new IllegalArgumentException("Le nom de l'auteur contient des caractères invalides");
		}
		this.auteur = auteur.trim();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		if (isbn == null || isbn.trim().isEmpty()) {
			throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
		}
		// Supprimer tous les caractères non numériques pour la validation
		String isbnNumbers = isbn.replaceAll("[^0-9]", "");

		// Vérification pour ISBN-13
		if (isbnNumbers.length() == 13) {
			if (!isValidIsbn13(isbnNumbers)) {
				throw new IllegalArgumentException("ISBN-13 invalide");
			}
		}
		// Vérification pour ISBN-10
		else if (isbnNumbers.length() == 10) {
			if (!isValidIsbn10(isbnNumbers)) {
				throw new IllegalArgumentException("ISBN-10 invalide");
			}
		}
		else {
			throw new IllegalArgumentException("Format ISBN invalide");
		}
		this.isbn = isbn;
	}

	private boolean isValidIsbn13(String isbn) {
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			sum += (i % 2 == 0) ? digit : digit * 3;
		}
		int checksum = (10 - (sum % 10)) % 10;
		return checksum == Character.getNumericValue(isbn.charAt(12));
	}

	private boolean isValidIsbn10(String isbn) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			sum += digit * (10 - i);
		}
		// Gestion du dernier caractère qui peut être 'X' (représentant 10)
		char last = isbn.charAt(9);
		if (last == 'X') {
			sum += 10;
		} else {
			sum += Character.getNumericValue(last);
		}
		return sum % 11 == 0;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		if (resume != null && resume.length() > 1000) {
			throw new IllegalArgumentException("Le résumé ne peut pas dépasser 1000 caractères");
		}
		this.resume = resume;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		if (quantite < 0) {
			throw new IllegalArgumentException("La quantité ne peut pas être négative");
		}
		if (quantite > 9999) {
			throw new IllegalArgumentException("La quantité ne peut pas dépasser 9999");
		}
		this.quantite = quantite;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		if (categorie == null) {
			throw new IllegalArgumentException("La catégorie ne peut pas être nulle");
		}
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return titre + " par " + auteur + " (ISBN: " + isbn + ")";
	}
}
