package apostropheivre.administrateur;
import apostropheivre.Cache;
import apostropheivre.PAGES;
import apostropheivre.utilitaires.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/administrateur")
public class AdministrateurServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("comptes", Cache.listerComptes());
			request.setAttribute("libraires", Cache.listerLibraires());
			request.setAttribute("page", PAGES.ADMINISTRATEUR + PAGES.COMPTE_ADMIN);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PAGES.APP);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Log.error(e.getMessage(), e);
		}
	}
}