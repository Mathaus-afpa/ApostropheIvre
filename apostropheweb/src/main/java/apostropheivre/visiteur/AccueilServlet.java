package apostropheivre.visiteur;
import apostropheivre.Cache;
import apostropheivre.PAGES;
import apostropheivre.utilitaires.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		Cache.initCache();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("page", PAGES.VISITEUR + PAGES.ACCUEIL);
			request.setAttribute("livres", Cache.getTop3LivresByQuantite());
			Cache.getTop3LivresByQuantite().forEach(livre -> {
				System.out.println(livre.getTitre() + " - Quantité: " + livre.getQuantite());
			});
			RequestDispatcher dispatcher = request.getRequestDispatcher(PAGES.APP);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Log.error(e.getMessage(), e);
		}
	}
}