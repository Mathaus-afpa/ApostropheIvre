package apostropheivre.visiteurs;
import apostropheivre.dao.*;
import apostropheivre.models.Auteur;
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

import apostropheivre.dao.LivreDAO;

@WebServlet(name = "LivresServlet", urlPatterns = {
        "/livres/*",
        "/modifier-livre",
        "/ajouter-livre"
})
public class LivresServlet extends HttpServlet {
    private LivreDAO livreDAO;
    private AuteurDAO auteurDAO;
    private CategorieDAO categorieDAO;
    private final BDDservice bddService = BDDservice.getInstance();

    @Override
    public void init() throws ServletException {
        try {
//            Connection connection = bddService.getConnection();
//            livreDAO = new LivreDAO(connection);
//            auteurDAO = new AuteurDAO();
//            categorieDAO = new CategorieDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Erreur d'initialisation de la connexion à la base de données", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String pathInfo = request.getPathInfo();
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        String pathInfo = uri.substring(contextPath.length());

        Connection connection = bddService.getConnection();
        livreDAO = new LivreDAO(connection);
        auteurDAO = new AuteurDAO();
        categorieDAO = new CategorieDAO(connection);
        try {
            if (pathInfo.equals("/livres")) {
                // Liste des livres avec recherche optionnelle
                afficherListe(request, response);
            } else if (pathInfo.contains("/ajouter")) {
                // Formulaire d'ajout
                afficherFormulaireAjout(request, response);
            } else if (pathInfo.contains("/modifier")) {
                // Formulaire de modification
                afficherFormulaireModification(request, response);
            }
        } catch (SQLException e) {
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

        // getParameter
        Integer id = Integer.valueOf(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        String titre = request.getParameter("titre");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        String resume = request.getParameter("resume");
        String image = request.getParameter("image");
        Integer idAuteur = Integer.valueOf(request.getParameter("auteurId"));
        Integer idCategorie = Integer.valueOf(request.getParameter("categorieId"));

        // TODO

        Livre tempo = new Livre(id, titre, resume, image, isbn, quantite, idCategorie, idAuteur);

        try {
            livreDAO.modifier(tempo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (pathInfo.equals("/ajouter")) {
                ajouterLivre(request, response);
            } else if (pathInfo.contains("/modifier")) {
                modifierLivre(request, response);
            } else if (pathInfo.contains("/supprimer")) {
                supprimerLivre(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("erreur", "Une erreur est survenue : " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/livres");
        }
    }

    private void afficherListe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String recherche = request.getParameter("recherche");
        List<Livre> livres;

        if (recherche != null && !recherche.trim().isEmpty()) {
            livres = livreDAO.rechercherParTitre(recherche);
        } else {
            livres = livreDAO.listerTous();
        }

        request.setAttribute("livres", livres);
        request.setAttribute("recherche", recherche);
        request.setAttribute("page", "/WEB-INF/Vues/Visiteur/livres.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //request.setAttribute("auteurs", auteurDAO.listerTous());
        request.setAttribute("categories", categorieDAO.listerTous());
        request.setAttribute("page", "/WEB-INF/Vues/Visiteur/ajouter-livre.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String isbn = request.getParameter("isbn");
        Livre livre = livreDAO.trouverParIsbn(isbn);

        if (livre != null) {
            request.setAttribute("livre", livre);
            request.setAttribute("auteurs", auteurDAO.findAll());
            request.setAttribute("categories", categorieDAO.listerTous());
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/modifier-livre.jsp");
        } else {
            request.setAttribute("erreur", "Livre non trouvé");
            request.setAttribute("page", "/WEB-INF/Vues/erreur.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterLivre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Récupération des données du formulaire
        Livre livre = extraireLivreDeFormulaire(request);

        // Ajout du livre
        livreDAO.ajouter(livre);

        // Redirection vers la liste
        response.sendRedirect(request.getContextPath() + "/livres");
    }

    private void modifierLivre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Récupération des données du formulaire
        Livre livre = extraireLivreDeFormulaire(request);

        // Modification du livre
        livreDAO.modifier(livre);

        // Redirection vers la liste
        response.sendRedirect(request.getContextPath() + "/livres");
    }

    private void supprimerLivre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String isbn = request.getParameter("isbn");
        livreDAO.supprimer(isbn);
        response.sendRedirect(request.getContextPath() + "/livres");
    }

    private Livre extraireLivreDeFormulaire(HttpServletRequest request) throws SQLException {

        Integer id = Integer.valueOf(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        String titre = request.getParameter("titre");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        String resume = request.getParameter("resume");
        String image = request.getParameter("image");
        Integer idAuteur = Integer.valueOf(request.getParameter("auteurId"));
        Integer idCategorie = Integer.valueOf(request.getParameter("categorieId"));

        return new Livre(id, titre, resume, image, isbn, quantite, idCategorie, idAuteur);
    }

    @Override
    public void destroy() {
        BDDservice.getInstance().closeConnection();
    }
}