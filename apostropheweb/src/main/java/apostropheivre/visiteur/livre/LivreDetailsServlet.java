package apostropheivre.visiteur.livre;
import apostropheivre.Cache;
import apostropheivre.PAGES;
import apostropheivre.exceptions.NullDataException;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/livre")
public class LivreDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String id = request.getParameter("id");
			request.setAttribute("livre", Cache.getLivre(Integer.parseInt(id)));
			request.setAttribute("page", PAGES.VISITEUR + PAGES.DETAILS_LIVRE);
			request.setAttribute("edit", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PAGES.APP);
			dispatcher.forward(request, response);
		} catch (ServletException | NullDataException e) {
			Log.error(e.getMessage(), e);
		}
	}
}