package apostropheivre;
import apostropheivre.administrateur.compte.Compte;
import apostropheivre.administrateur.compte.CompteDAO;
import apostropheivre.administrateur.libraire.Libraire;
import apostropheivre.administrateur.libraire.LibraireDAO;
import apostropheivre.exceptions.ModelException;
import apostropheivre.exceptions.NullDataException;
import apostropheivre.libraire.auteur.Auteur;
import apostropheivre.libraire.auteur.AuteurDAO;
import apostropheivre.libraire.categorie.Categorie;
import apostropheivre.libraire.categorie.CategorieDAO;
import apostropheivre.libraire.client.Client;
import apostropheivre.libraire.client.ClientDAO;
import apostropheivre.libraire.livre.Livre;
import apostropheivre.libraire.livre.LivreDAO;
import apostropheivre.services.DataDB;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * [Cache] - class
 * @author Mathaus
 */
public class Cache {
	private Cache() {}
	//<editor-fold defaultstate="expanded" desc="CACHE MAPS">
	private static final Map<Integer,Auteur> AUTEURS = new TreeMap();
	private static final Map<Integer,Categorie> CATEGORIES = new TreeMap();
	private static final Map<Integer,Client> CLIENTS = new TreeMap();
	private static final Map<Integer, Compte> COMPTES = new TreeMap();
	private static final Map<Integer,Libraire> LIBRAIRES = new TreeMap();
	private static final Map<Integer,Livre> LIVRES = new TreeMap();
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="FONCTIONS CACHE">
	/**
	 * Ajoute un objet au cache.
	 * @param data
	 * @throws NullDataException
	 * @throws ModelException
	 */
	public static void ajouter(Object data) throws NullDataException, ModelException {
		switch (data) {
			case Auteur auteur -> AUTEURS.put(auteur.getId(), auteur);
			case Categorie categorie -> CATEGORIES.put(categorie.getId(), categorie);
			case Client client -> CLIENTS.put(client.getId(), client);
			case Compte compte -> COMPTES.put(compte.getId(), compte);
			case Libraire libraire -> LIBRAIRES.put(libraire.getId(), libraire);
			case Livre livre -> LIVRES.put(livre.getId(), livre);
			case null -> throw new NullDataException("Cache.ajouter", data.getClass().getName());
			default -> throw new ModelException("Cache.ajouter", data.getClass().getName());
		}
	}
	/**
	 * Retire un element du cache
	 * @param data
	 * @throws NullDataException
	 * @throws ModelException
	 */
	public static void retirer(Object data) throws NullDataException, ModelException {
		switch (data) {
			case Auteur auteur -> AUTEURS.remove(auteur.getId());
			case Categorie categorie -> CATEGORIES.remove(categorie.getId());
			case Client client -> CLIENTS.remove(client.getId());
			case Compte compte -> COMPTES.remove(compte.getId());
			case Libraire libraire -> LIBRAIRES.remove(libraire.getId());
			case Livre livre -> LIVRES.remove(livre.getId());
			case null -> throw new NullDataException("Cache.retirer", data.getClass().getName());
			default -> throw new ModelException("Cache.retirer", data.getClass().getName());
		}
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="LIRE DONNEE">
	public static Auteur getAuteur(int id) throws NullDataException {
		Auteur auteur = AUTEURS.get(id);
		if (auteur == null) throw new NullDataException("Cache.getAuteur");
		return auteur;
	}
	public static Categorie getCategorie(int id) throws NullDataException {
		Categorie categorie = CATEGORIES.get(id);
		if (categorie == null) throw new NullDataException("Cache.getCategorie");
		return categorie;
	}
	public static Client getClient(int id) throws NullDataException {
		Client client = CLIENTS.get(id);
		if (client == null) throw new NullDataException("Cache.getClient");
		return client;
	}
	public static Compte getCompte(int id) throws NullDataException {
		Compte compte = COMPTES.get(id);
		if (compte == null) throw new NullDataException("Cache.getCompte");
		return compte;
	}
	public static Libraire getLibraire(int id) throws NullDataException {
		Libraire libraire = LIBRAIRES.get(id);
		if (libraire == null) throw new NullDataException("Cache.getLibraire");
		return libraire;
	}
	public static Livre getLivre(int id) throws NullDataException {
		Livre livre = LIVRES.get(id);
		if (livre == null) throw new NullDataException("Cache.getLivre");
		return livre;
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="EFFACEMENT DES CACHES">
	public static void clearAuteurs() {
		AUTEURS.clear();
	}
	public static void clearCategories() {
		CATEGORIES.clear();
	}
	public static void clearClients() {
		CLIENTS.clear();
	}
	public static void clearComptes() {
		COMPTES.clear();
	}
	public static void clearLibraires() {
		LIBRAIRES.clear();
	}
	public static void clearLivres() {
		LIVRES.clear();
	}
	//</editor-fold>
	//<editor-fold defaultstate="expanded" desc="Validateurs">
	public static List<Auteur> listerAuteurs() {
		if (AUTEURS.isEmpty()) {
			DataDB.setForcerRequete(true);
			AuteurDAO.rechercherTout();
			DataDB.setForcerRequete(false);
		}
		return new ArrayList<>(AUTEURS.values());
	}
	public static List<Categorie> listerCategories() {
		if (CATEGORIES.isEmpty()) {
			DataDB.setForcerRequete(true);
			CategorieDAO.rechercherTout();
			DataDB.setForcerRequete(false);
		}
		return new ArrayList<>(CATEGORIES.values());
	}
	public static List<Client> listerClients() {
		if (CLIENTS.isEmpty()) {
			DataDB.setForcerRequete(true);
			ClientDAO.rechercherTout();
			DataDB.setForcerRequete(false);
		}
		return new ArrayList<>(CLIENTS.values());
	}
	public static List<Compte> listerComptes() {
		if (COMPTES.isEmpty()) {
			DataDB.setForcerRequete(true);
			CompteDAO.rechercherTout();
			DataDB.setForcerRequete(false);
		}
		return new ArrayList<>(COMPTES.values());
	}
	public static List<Libraire> listerLibraires() {
		if (LIBRAIRES.isEmpty()) {
			DataDB.setForcerRequete(true);
			LibraireDAO.rechercherTout();
			DataDB.setForcerRequete(false);
		}
		return new ArrayList<>(LIBRAIRES.values());
	}
	public static List<Livre> listerLivres() {
		if (LIVRES.isEmpty()) {
			DataDB.setForcerRequete(true);
			AuteurDAO.rechercherTout();
			CategorieDAO.rechercherTout();
			LivreDAO.rechercherTout();
			DataDB.setForcerRequete(false);
		}
		return new ArrayList<>(LIVRES.values());
	}
	//</editor-fold>
}