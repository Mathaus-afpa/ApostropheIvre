package apostropheivre.visiteur.livre;
import apostropheivre.Cache;
import apostropheivre.PAGES;
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("livres", Cache.listerLivres());
			request.setAttribute("page", PAGES.VISITEUR + PAGES.LISTE_LIVRES);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PAGES.APP);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Log.error(e.getMessage(), e);
		}
	}
}