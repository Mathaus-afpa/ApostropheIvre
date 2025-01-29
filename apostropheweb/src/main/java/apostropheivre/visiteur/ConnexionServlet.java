package apostropheivre.visiteur;

import apostropheivre.Cache;
import apostropheivre.administrateur.compte.Compte;
import apostropheivre.administrateur.compte.CompteDAO;
import apostropheivre.exceptions.NullDataException;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("page", "/WEB-INF/Vues/Visiteur/connexion.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Log.error(e.getMessage(), e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		Log.info("Méthode doPost appelée dans ConnexionServlet");
		try {
			handleLogin(request, response);
		} catch (NullDataException e) {
			throw new RuntimeException(e);
		}
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullDataException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Valider l'utilisateur avec la "base de données"
		if (true) {
			String role = Cache.getCompte(1).getRole();

			// Créer une session et y stocker le rôle
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", role);

			// Redirection selon le rôle
			switch (role) {
				case "administrateur":
					response.sendRedirect("./administrateur");
					break;
				case "libraire":
					response.sendRedirect("./libraire");
					break;
				case "client":
					response.sendRedirect("./client");
					break;
				default:
					response.sendRedirect("./connexion");
					break;
			}
		} else {
			response.sendRedirect("./connexion");
		}
	}

	private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Log.info("Appel de la méthode handleRegister");

		// Récupération des paramètres de la requête
		String login = request.getParameter("login");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");

		Log.info("Reçu POST avec paramètres : login=" + login + ", mail=" + mail + ", password=" + password);

		// Validation des données
		if (login == null || mail == null || password == null || login.isEmpty() || mail.isEmpty() || password.isEmpty()) {
			Log.info("Validation des données échouée");
			request.setAttribute("errorMessage", "Tous les champs sont obligatoires.");
			doGet(request, response);
			return;
		}
		Log.info("Validation des données réussie");

		try {
			// Création du JSON à envoyer au DAO
			JSONObject jsonCompte = new JSONObject();
			jsonCompte.put("login", login);
			jsonCompte.put("mail", mail);
			jsonCompte.put("password", password);
			jsonCompte.put("role", 3); // Exemple de rôle par défaut

			Log.info("JSON créé pour le nouveau compte : " + jsonCompte.toString());
			// Appel au DAO pour créer le compte
			Compte nouveauCompte = CompteDAO.creer(jsonCompte.toString());

//			try {
//				// Appel de l'envoi d'e-mail simple
//				EmailService.envoyerEmailSimple(nouveauCompte.getMail());
//				Log.info("E-mail envoyé avec succès à " + nouveauCompte.getMail());
//			} catch (MessagingException e) {
//				Log.error("Erreur lors de l'envoi de l'e-mail : " + e.getMessage(), e);
//			}

			if (nouveauCompte != null && nouveauCompte.getId() > 0) {
				Log.info("Compte créé avec succès, ID généré : " + nouveauCompte.getId());
				response.sendRedirect("login"); // Redirection vers la page de connexion après création réussie
			} else {
				Log.info("Échec de la création du compte");
				request.setAttribute("errorMessage", "Impossible de créer le compte. Veuillez réessayer.");
				doGet(request, response);
			}
		} catch (Exception e) {
			Log.error("Erreur lors de l'enregistrement de l'utilisateur : " + e.getMessage(), e);
			request.setAttribute("errorMessage", "Une erreur interne est survenue. Veuillez réessayer.");
			doGet(request, response);
		}
	}

}