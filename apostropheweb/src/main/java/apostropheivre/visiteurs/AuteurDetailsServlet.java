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
            String role = (String) request.getSession().getAttribute("role");
            if (role.equals("admin") || role.equals("libraire")) {
                request.setAttribute("page", "/WEB-INF/Vues/Libraire/auteur_form.jsp");
            } else {
                request.setAttribute("page", "/WEB-INF/Vues/Libraire/auteur_form.jsp");
            }
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}