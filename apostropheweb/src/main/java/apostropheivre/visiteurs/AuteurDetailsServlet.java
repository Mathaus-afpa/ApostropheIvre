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
@WebServlet("/auteur/details")
public class AuteurDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            Auteur auteur = (Auteur) new AuteurDAO().find(Integer.parseInt(id));
            request.setAttribute("auteur", auteur);
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/auteur_details.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("../app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("AAAAAAAA");
        String id = request.getParameter("id");
        new AuteurDAO().delete(Integer.parseInt(id));
    }
}