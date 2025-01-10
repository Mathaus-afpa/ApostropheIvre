package apostropheivre;

import apostropheivre.dao.BDDservice;
import apostropheivre.dao.LivreDAO;
import apostropheivre.models.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "livresServlet", value = "/livres")
public class ApostropheIvreServlet extends HttpServlet {
    private LivreDAO livreDAO;
    private final BDDservice bddService = BDDservice.getInstance();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = bddService.getConnection();
            livreDAO = new LivreDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Erreur d'initialisation de la connexion à la base de données", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            List<Livre> livres = livreDAO.listerTous();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des Livres - L'Apostrophe Ivre</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("h1 { color: #2c3e50; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #3498db; color: white; }");
            out.println("tr:hover { background-color: #f5f5f5; }");
            out.println(".quantite { text-align: center; }");
            out.println(".actions { text-align: center; }");
            out.println(".btn { padding: 5px 10px; margin: 2px; border-radius: 3px; text-decoration: none; color: white; }");
            out.println(".btn-edit { background-color: #2ecc71; }");
            out.println(".btn-delete { background-color: #e74c3c; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Liste des Livres</h1>");
            out.println("<a href='ajouter-livre' class='btn btn-edit'>Ajouter un nouveau livre</a>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Titre</th>");
            out.println("<th>Auteur</th>");
            out.println("<th>ISBN</th>");
            out.println("<th>Catégorie</th>");
            out.println("<th>Quantité</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            for (Livre livre : livres) {
                out.println("<tr>");
                out.println("<td>" + livre.getTitre() + "</td>");
                out.println("<td>" + livre.getAuteur() + "</td>");
                out.println("<td>" + livre.getIsbn() + "</td>");
                out.println("<td>" + livre.getCategorie().getLibelle() + "</td>");
                out.println("<td class='quantite'>" + livre.getQuantite() + "</td>");
                out.println("<td class='actions'>");
                out.println("<a href='modifier-livre?isbn=" + livre.getIsbn() + "' class='btn btn-edit'>Modifier</a>");
                out.println("<a href='supprimer-livre?isbn=" + livre.getIsbn() + "' class='btn btn-delete' onclick='return confirm(\"Êtes-vous sûr de vouloir supprimer ce livre ?\")'>Supprimer</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des livres", e);
        }
    }

    @Override
    public void destroy() {
        bddService.closeConnection();
    }
}