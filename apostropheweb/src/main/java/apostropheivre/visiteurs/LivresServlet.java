package apostropheivre.visiteurs;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/livres")
public class LivresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Visiteur/livres.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }
}