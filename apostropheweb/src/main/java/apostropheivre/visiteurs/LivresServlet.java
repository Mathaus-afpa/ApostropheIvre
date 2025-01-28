package apostropheivre.visiteurs;
import apostropheivre.dao.*;
import apostropheivre.models.Livre;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet(name = "LivresServlet", urlPatterns = {
        "/livres",
        "/livres/*",
        "/modifier-livre",
        "/ajouter-livre",
        "/supprimer-livre"
})
public class LivresServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LivresServlet.class.getName());
    private LivreDAO livreDAO;
    private AuteurDAO auteurDAO;
    private CategorieDAO categorieDAO;
    private final BDDservice bddService = BDDservice.getInstance();

    @Override
    public void init() throws ServletException {
        try {
            LOGGER.info("Initialisation du LivresServlet");
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de l'initialisation: " + e.getMessage());
            throw new ServletException("Erreur d'initialisation de la connexion à la base de données", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = BDDservice.getInstance().getConnection();
        livreDAO = new LivreDAO();
        auteurDAO = new AuteurDAO();
        categorieDAO = new CategorieDAO();
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        String pathInfo = uri.substring(contextPath.length());

        LOGGER.info("Requête GET reçue - Path: " + pathInfo);

        try {
            if (pathInfo.equals("/livres")) {
                LOGGER.info("Affichage de la liste des livres");
                afficherListe(request, response);
            } else if (pathInfo.contains("/ajouter")) {
                LOGGER.info("Affichage du formulaire d'ajout");
                afficherFormulaireAjout(request, response);
            } else if (pathInfo.contains("/modifier-livre")) {
                LOGGER.info("Affichage du formulaire de modification");
                afficherFormulaireModification(request, response);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur SQL dans doGet: " + e.getMessage());
            request.setAttribute("erreur", "Une erreur est survenue : " + e.getMessage());
            request.setAttribute("page", "/WEB-INF/Vues/erreur.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        String pathInfo = uri.substring(contextPath.length());

        LOGGER.info("Requête POST reçue - Path: " + pathInfo);

        try {
            // Log des paramètres reçus
            LOGGER.info("Paramètres reçus - ISBN: " + request.getParameter("isbn") +
                    ", Titre: " + request.getParameter("titre"));

            if (pathInfo.equals("/ajouter")) {
                LOGGER.info("Tentative d'ajout d'un nouveau livre");
                ajouterLivre(request, response);
            } else if (pathInfo.contains("/modifier")) {
                LOGGER.info("Tentative de modification d'un livre");
                modifierLivre(request, response);
            } else if (pathInfo.contains("/supprimer")) {
                LOGGER.info("Tentative de suppression d'un livre");
                supprimerLivre(request, response);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur SQL dans doPost: " + e.getMessage());
            request.setAttribute("erreur", "Une erreur est survenue : " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/livres");
        } catch (NumberFormatException e) {
            LOGGER.severe("Erreur de parsing des paramètres: " + e.getMessage());
            request.setAttribute("erreur", "Erreur dans le format des données");
            response.sendRedirect(request.getContextPath() + "/livres");
        }
    }

    private void afficherListe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String recherche = request.getParameter("recherche");
        LOGGER.info("Recherche demandée: " + recherche);

        List<Livre> livres;
        if (recherche != null && !recherche.trim().isEmpty()) {
            LOGGER.info("Exécution de la recherche par titre");
            livres = livreDAO.rechercherParTitre(recherche);
        } else {
            LOGGER.info("Récupération de tous les livres");
            livres = livreDAO.listerTous();
        }

        LOGGER.info("Nombre de livres trouvés: " + livres.size());
        request.setAttribute("livres", livres);
        request.setAttribute("recherche", recherche);
        request.setAttribute("page", "/WEB-INF/Vues/Visiteur/livres.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        LOGGER.info("Préparation du formulaire d'ajout");
        request.setAttribute("categories", categorieDAO.listerTous());
        request.setAttribute("auteurs", auteurDAO.findAll());
        request.setAttribute("page", "/WEB-INF/Vues/Visiteur/ajouter-livre.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String isbn = request.getParameter("isbn");
        LOGGER.info("Recherche du livre avec ISBN: " + isbn);

        Livre livre = livreDAO.trouverParIsbn(isbn);
        if (livre != null) {
            LOGGER.info("Livre trouvé: " + livre.getTitre());
            request.setAttribute("livre", livre);
            request.setAttribute("auteurs", auteurDAO.findAll());
            request.setAttribute("categories", categorieDAO.listerTous());
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/modifier-livre.jsp");
        } else {
            LOGGER.warning("Livre non trouvé avec ISBN: " + isbn);
            request.setAttribute("erreur", "Livre non trouvé");
            request.setAttribute("page", "/WEB-INF/Vues/erreur.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterLivre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Livre livre = extraireLivreDeFormulaire(request);
        LOGGER.info("Tentative d'ajout du livre: " + livre.getTitre());

        livreDAO.ajouter(livre);
        LOGGER.info("Livre ajouté avec succès");

        response.sendRedirect(request.getContextPath() + "/livres");
    }

    private void modifierLivre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Livre livre = extraireLivreDeFormulaire(request);
        LOGGER.info("Tentative de modification du livre: " + livre.getTitre());

        livreDAO.modifier(livre);
        LOGGER.info("Livre modifié avec succès");

        response.sendRedirect(request.getContextPath() + "/livres");
    }

    private void supprimerLivre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String isbn = request.getParameter("isbn");
        LOGGER.info("Tentative de suppression du livre avec ISBN: " + isbn);

        if (isbn == null || isbn.trim().isEmpty()) {
            LOGGER.warning("ISBN manquant ou vide");
            request.setAttribute("erreur", "ISBN manquant ou vide");
            response.sendRedirect(request.getContextPath() + "/livres");
            return;
        }

        try {
            livreDAO.supprimer(isbn);
            LOGGER.info("Livre supprimé avec succès");
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la suppression du livre: " + e.getMessage());
            throw e;
        }

        response.sendRedirect(request.getContextPath() + "/livres");
    }

    private Livre extraireLivreDeFormulaire(HttpServletRequest request) throws SQLException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            String isbn = request.getParameter("isbn");
            String titre = request.getParameter("titre");
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            String resume = request.getParameter("resume");
            String image = request.getParameter("image");
            Integer idAuteur = Integer.valueOf(request.getParameter("auteurId"));
            Integer idCategorie = Integer.valueOf(request.getParameter("categorieId"));

            LOGGER.info("Extraction des données du formulaire - ISBN: " + isbn + ", Titre: " + titre);
            return new Livre(id, titre, resume, image, isbn, quantite, idCategorie, idAuteur);
        } catch (NumberFormatException e) {
            LOGGER.severe("Erreur lors de l'extraction des données du formulaire: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("Destruction du LivresServlet - Fermeture des connexions");
        BDDservice.getInstance().closeConnection();
    }
}