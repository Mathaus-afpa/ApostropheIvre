package apostropheivre.visiteurs;

import apostropheivre.dao.BDDservice;
import apostropheivre.models.Livre;
import apostropheivre.models.Categorie;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/livre/details")
public class LivreDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String id = request.getParameter("id");

            if (id != null && !id.trim().isEmpty()) {
                connection = BDDservice.getInstance().getConnection();
                String sql = "SELECT l.* FROM livre l WHERE l.liv_id = ?";

                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    Livre livre = new Livre();
                    livre.setTitre(rs.getString("liv_titre"));
                    livre.setIdAuteur(rs.getInt("aut_id"));
                    livre.setIsbn(rs.getString("liv_isbn"));
                    livre.setResume(rs.getString("liv_resume"));
                    livre.setQuantite(rs.getInt("liv_quantite"));
                    livre.setIdCategorie(rs.getInt("cat_id"));
                    livre.setImage(rs.getString("liv_image"));

                    request.setAttribute("livre", livre);
                }
            }

            request.setAttribute("id", id);
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/livre_details.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("../app.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ServletException e) {
            Log.error(e.getMessage(), e);
        } finally {
            // Fermeture des ressources dans l'ordre inverse
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Log.error("Erreur lors de la fermeture du ResultSet", e);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    Log.error("Erreur lors de la fermeture du PreparedStatement", e);
                }
            }
            if (connection != null) {
                BDDservice.getInstance().closeConnection();
            }
        }
    }
}