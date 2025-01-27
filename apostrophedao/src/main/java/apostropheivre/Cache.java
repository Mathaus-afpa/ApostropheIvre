package apostropheivre;
import apostropheivre.administrateur.compte.Compte;
import apostropheivre.administrateur.libraire.Libraire;
import apostropheivre.exceptions.NullDataException;
import apostropheivre.libraire.auteur.Auteur;
import apostropheivre.libraire.categorie.Categorie;
import apostropheivre.libraire.client.Client;
import apostropheivre.libraire.livre.Livre;
import java.util.Map;
import java.util.TreeMap;
/**
 * [Cache] - class
 * @author Mathaus
 */
public class Cache {
	private Cache() {}
	private static final Map<Integer,Auteur> AUTEURS = new TreeMap();
	private static final Map<Integer,Categorie> CATEGORIES = new TreeMap();
	private static final Map<Integer,Client> CLIENTS = new TreeMap();
	private static final Map<Integer, Compte> COMPTES = new TreeMap();
	private static final Map<Integer,Libraire> LIBRAIRES = new TreeMap();
	private static final Map<Integer,Livre> LIVRES = new TreeMap();
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
}