package apostropheivre.visiteurs;
import apostropheivre.UserDatabase;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/connexion.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Valider l'utilisateur avec la "base de données"
        if (UserDatabase.validateUser(username, password)) {
            String role = UserDatabase.getRole(username);
            // Créer une session et y stocker le rôle
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            // Redirection selon le rôle
            switch (role) {
                case "administrateur":
                    response.sendRedirect("./administrateur");
                    break;
                case "libraire":
                    response.sendRedirect("./libraire");
                    break;
                case "client":
                    response.sendRedirect("./client");
                    break;
                default:
                    break;
            }
        } else {
            response.sendRedirect("./connexion");
        }
    }

}