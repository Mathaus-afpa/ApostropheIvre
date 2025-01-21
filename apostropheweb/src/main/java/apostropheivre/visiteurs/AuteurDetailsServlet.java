package apostropheivre.visiteurs;
import apostropheivre.dao.AuteurDAO;
import apostropheivre.models.Auteur;
import apostropheivre.utils.Json;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static apostropheivre.ApostropheIvrePages.APP;
import static apostropheivre.ApostropheIvrePages.AUTEUR_DETAIL;
@WebServlet("/auteur/details")
public class AuteurDetailsServlet extends HttpServlet {
    //<editor-fold defaultstate="expanded" desc="POST / CREATE">
    private static void CreerAuteur(String json) throws IOException {
        try {
            new AuteurDAO().create(json);
        } catch (Exception e) {
            Log.error(e.getMessage(), e.getCause());
            throw new IOException("POST /auteur/details CreerAuteur " + json);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreerAuteur(Json.RequeteToJson(request));
    }
    //</editor-fold>
    //<editor-fold defaultstate="expanded" desc="GET / READ">
    private static Auteur RecupererAuteur(String id) throws IOException {
        try {
            return (Auteur) new AuteurDAO().find(Integer.parseInt(id));
        } catch (Exception e) {
            throw new IOException("GET /auteur/details RecupererAuteur " + id);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            request.setAttribute("auteur", RecupererAuteur(id));
            request.setAttribute("page", AUTEUR_DETAIL);
            request.setAttribute("edit", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher(APP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="expanded" desc="PUT / UPDATE">
    private static void ModifierAuteur(String json) throws IOException {
        try {
            new AuteurDAO().update(json, 0);
        } catch (Exception e) {
            throw new IOException("PUT /auteur/details ModifierAuteur " + json);
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModifierAuteur(Json.RequeteToJson(request));
    }
    //</editor-fold>
    //<editor-fold defaultstate="expanded" desc="DELETE / DELETE">
    private static void SupprimerAuteur(String id) throws IOException {
        try {
            new AuteurDAO().delete(Integer.parseInt(id));
        } catch (Exception e) {
            throw new IOException("DELETE /auteur/details SupprimerAuteur " + id);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        SupprimerAuteur(id);
    }
    //</editor-fold>
}