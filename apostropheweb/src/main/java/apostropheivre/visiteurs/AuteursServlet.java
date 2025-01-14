package apostropheivre.visiteurs;
import apostropheivre.dao.AuteurDAO;
import apostropheivre.models.Auteur;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/auteurs")
public class AuteursServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        List<Auteur> auteurs = new AuteurDAO().findAll();
        // Stocker la liste dans le scope application
        getServletContext().setAttribute("auteurs", auteurs);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
//            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vues/Visiteur/auteurs.jsp");
            dispatcher.include(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}