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

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApostropheIvre.AUTEURS = new AuteurDAO().findAll();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/accueil.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}