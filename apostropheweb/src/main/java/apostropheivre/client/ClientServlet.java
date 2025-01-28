package apostropheivre.client;
import apostropheivre.Cache;
import apostropheivre.PAGES;
import apostropheivre.exceptions.NullDataException;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/client")
public class ClientServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		Cache.listerClients();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("client", Cache.getClient(1)); //todo: mes infos
			request.setAttribute("emprunts", null); //todo: mes emprunts Select where
			request.setAttribute("page", PAGES.CLIENT + PAGES.COMPTE_CLIENT);
			RequestDispatcher dispatcher = request.getRequestDispatcher(PAGES.APP);
			dispatcher.forward(request, response);
		} catch (ServletException | NullDataException e) {
			Log.error(e.getMessage(), e);
		}
	}
}