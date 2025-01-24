package apostropheivre.visiteurs;

import apostropheivre.ApostropheIvre;
import apostropheivre.auteur.AuteurDAO;
import apostropheivre.categorie.CategorieDAO;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
    static {
        AuteurDAO.setForcerRequete(true);
        ApostropheIvre.AUTEURS = AuteurDAO.listerAuteurs();
        AuteurDAO.setForcerRequete(false);
        CategorieDAO.setForcerRequete(true);
        ApostropheIvre.CATEGORIES = CategorieDAO.listerCategories();
        CategorieDAO.setForcerRequete(false);
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