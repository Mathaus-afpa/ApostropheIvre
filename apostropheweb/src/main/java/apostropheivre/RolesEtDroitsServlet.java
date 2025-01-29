package apostropheivre;
import apostropheivre.utilitaires.Log;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*") // Applique le filtre à toutes les routes
public class RolesEtDroitsServlet implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false); // Ne crée pas de session si elle n'existe pas
		// Récupère la route demandée
		String requestedPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		ROUTES route = ROUTES.getRoute(requestedPath);
		System.out.println(requestedPath);
		// Vérifie si la route est définie dans les ROUTES
		if (ROUTES.isRoutable(route)) {
			String role = (session == null) ? null : (String) session.getAttribute("role");
			if (ROUTES.forVisiteur(route)) {
				Log.info("VSITEUR");
				chain.doFilter(request, response);
			} else if (role != null && (ROUTES.forClient(route) || ROUTES.forLibraire(route) ||ROUTES.forAdministrateur(route))) {
				Log.info("ROLE");
				chain.doFilter(request, response);
			} else {
				System.out.println(route + " " + role + " FORBIDDEN");
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		} else {
			if (ROUTES.isStaticFile(requestedPath)) {
				System.out.println("isStaticFile");
				chain.doFilter(request, response);
			} else {
				System.out.println("404");
				httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
	}
}
