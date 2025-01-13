package apostropheivre.visiteurs;
import apostropheivre.dao.BDDservice;
import apostropheivre.dao.LivreDAO;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LivresServlet", value = "/livres")
public class LivresServlet extends HttpServlet {
    private LivreDAO livreDAO;
    private final BDDservice bddService = BDDservice.getInstance();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = bddService.getConnection();
            livreDAO = new LivreDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Erreur d'initialisation de la connexion à la base de données", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String recherche = request.getParameter("recherche");
        List<Livre> livres = new ArrayList<>();
        String message = null;

        try {
            if (recherche != null && !recherche.trim().isEmpty()) {
                livres = livreDAO.rechercherParTitre(recherche.trim());
                if (livres.isEmpty()) {
                    message = "Aucun livre trouvé pour : " + recherche;
                }
            } else {
                livres = livreDAO.listerTous();
            }
        } catch (SQLException e) {
            message = "Erreur lors de la recherche : " + e.getMessage();
        }

        request.setAttribute("livres", livres);
        request.setAttribute("message", message);
        request.setAttribute("recherche", recherche);

        request.setAttribute("page", "/WEB-INF/Vues/Visiteur/livres.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        bddService.closeConnection();
    }
}