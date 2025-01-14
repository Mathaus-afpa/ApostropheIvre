package apostropheivre.membres;

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
import java.util.List;

@WebServlet("/libraire")
public class CompteLibraireServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Auteur> auteurs = new AuteurDAO().findAll();
            // Stocker la liste dans le scope application
            getServletContext().setAttribute("auteurs", auteurs);
            request.setAttribute("page", "/WEB-INF/Vues/Libraire/compte_libraire.jsp");
            request.setAttribute("edit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
