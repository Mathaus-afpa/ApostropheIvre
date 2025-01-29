package apostropheivre.libraire;
import apostropheivre.Cache;
import apostropheivre.PAGES;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/libraire")
public class LibraireServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("auteurs", Cache.listerAuteurs());
			request.setAttribute("categories", Cache.listerCategories());
			request.setAttribute("livres", Cache.listerLivres());
			request.setAttribute("clients", Cache.listerClients());
			request.setAttribute("page", PAGES.LIBRAIRE + PAGES.COMPTE_LIBRAIRE);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PAGES.APP);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Log.error(e.getMessage(), e);
		}
	}
}