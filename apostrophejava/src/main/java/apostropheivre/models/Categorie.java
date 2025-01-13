package apostropheivre.models;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int id;
    private String libelle;
    private List<Livre> livres;

    // Constructeur
    public Categorie(int id, String libelle) {
        setId(id);
        setLibelle(libelle);
        this.livres = new ArrayList<>();
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("L'identifiant ne peut pas être négatif");
        }
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé ne peut pas être vide");
        }
        if (libelle.length() > 50) {
            throw new IllegalArgumentException("Le libellé ne peut pas dépasser 50 caractères");
        }
        if (!libelle.matches("^[a-zA-ZÀ-ÿ\\s-]{2,50}$")) {
            throw new IllegalArgumentException("Le libellé contient des caractères invalides");
        }
        this.libelle = libelle.trim();
    }

    public List<Livre> getLivres() {
        return new ArrayList<>(livres); // Retourne une copie de la liste pour éviter la modification directe
    }

    public void setLivres(List<Livre> livres) {
        if (livres == null) {
            throw new IllegalArgumentException("La liste des livres ne peut pas être nulle");
        }
        this.livres = new ArrayList<>(livres); // Crée une copie de la liste
    }

    // Méthodes pour gérer la liste des livres
    public void ajouterLivre(Livre livre) {
        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être nul");
        }
        if (!this.livres.contains(livre)) {
            this.livres.add(livre);
        }
    }

    public void retirerLivre(Livre livre) {
        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être nul");
        }
        this.livres.remove(livre);
    }

    public int getNombreLivres() {
        return this.livres.size();
    }

    @Override
    public String toString() {
        return libelle + " (" + livres.size() + " livres)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categorie categorie = (Categorie) obj;
        return id == categorie.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

