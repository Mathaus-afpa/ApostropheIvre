package apostropheivre.membres;
import apostropheivre.Cache;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static apostropheivre.ApostropheIvre.AUTEURS;
import static apostropheivre.ApostropheIvre.CATEGORIES;
import static apostropheivre.ApostropheIvrePages.APP;
import static apostropheivre.ApostropheIvrePages.LIBRAIRE_DASHBOARD;

@WebServlet("/libraire")
public class CompteLibraireServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("auteurs", Cache.listerAuteurs());
            request.setAttribute("categories", Cache.listerCategories());
            request.setAttribute("livres", Cache.listerLivres());
            request.setAttribute("clients", Cache.listerClients());
            request.setAttribute("page", LIBRAIRE_DASHBOARD);
            request.setAttribute("edit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher(APP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}