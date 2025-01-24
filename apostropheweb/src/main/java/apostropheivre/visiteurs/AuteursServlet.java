package apostropheivre.visiteurs;
import apostropheivre.auteur.AuteurDAO;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static apostropheivre.ApostropheIvrePages.APP;
import static apostropheivre.ApostropheIvrePages.LISTE_AUTEUR;

@WebServlet("/auteurs")
public class AuteursServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("auteurs", AuteurDAO.listerAuteurs());
            request.setAttribute("page", LISTE_AUTEUR);
            request.setAttribute("edit", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher(APP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}