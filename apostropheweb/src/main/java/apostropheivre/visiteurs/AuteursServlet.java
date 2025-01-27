package apostropheivre.visiteurs;
import apostropheivre.ApostropheIvre;
import apostropheivre.dao.AuteurDAO;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static apostropheivre.ApostropheIvre.AUTEURS;
import static apostropheivre.ApostropheIvrePages.APP;
import static apostropheivre.ApostropheIvrePages.LISTE_AUTEUR;

@WebServlet("/auteurs")
public class AuteursServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        AUTEURS = new AuteurDAO().findAll();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("auteurs", AUTEURS);
            request.setAttribute("page", LISTE_AUTEUR);
            request.setAttribute("edit", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher(APP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}