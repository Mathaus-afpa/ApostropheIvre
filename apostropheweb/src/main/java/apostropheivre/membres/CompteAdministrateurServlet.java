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

@WebServlet("/administrateur")
public class CompteAdministrateurServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        List<Auteur> auteurs = new AuteurDAO().findAll();
        getServletContext().setAttribute("auteurs", auteurs);
        List<Auteur> livres = new AuteurDAO().findAll();
        getServletContext().setAttribute("livres", livres);
        livres.addAll(auteurs);
        List<Auteur> categories = new AuteurDAO().findAll();
        categories.addAll(livres);
        getServletContext().setAttribute("categories", categories);
        List<Auteur> dinosaures = new AuteurDAO().findAll();
        Auteur ao = new Auteur();
        ao.setNom("Apostrophe");
        ao.setPrenom("Apostrophe");
        ao.setUrl("aaz");
        ao.setId(150);
        dinosaures.add(ao);
        getServletContext().setAttribute("dinosaures", dinosaures);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Administrateur/compte_administrateur.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
