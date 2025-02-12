package apostropheivre;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class ApostropheIvreFilter implements Filter {

	private static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "POST", "PUT", "DELETE");
	// Pages accessibles par les utilisateurs standards

	public static final List<String> PAGES_VISITEUR = Arrays.asList("/", "/accueil", "/inscription",
			"/gestion", "/gestion/clients", "/gestion/clients/details", "/gestion/clients/inscription", "/modifier", "/supprimer",
			"/livres/*", "/modifier-livre", "/ajouter-livre", "/connexion", "/livres", "/livre/details", "/auteurs", "/auteur/details",
			"/erreur", "/gestion/emprunts");
	public static final List<String> PAGES_CLIENT = Arrays.asList("/client");
	public static final List<String> PAGES_LIBRAIRE = Arrays.asList("/libraire", "/client");
	public static final List<String> PAGES_ADMIN = Arrays.asList("/administrateur", "/libraire", "/client");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			// Récupérer la méthode HTTP
			String method = httpRequest.getMethod();
			System.out.println(method + " " + httpRequest.getRequestURI());
			// Vérifier si la méthode est autorisée
			if (ALLOWED_METHODS.contains(method.toUpperCase())) {
				// Continuer le traitement si la méthode est autorisée
				String role = (String) ((HttpServletRequest) request).getSession().getAttribute("role");
				String path = httpRequest.getServletPath();
				chain.doFilter(request, response);
			} else {
				// Répondre avec une erreur 405 Method Not Allowed si la méthode n'est pas autorisée
				httpResponse.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				httpResponse.getWriter().write("HTTP method " + method + " is not allowed.");
			}

		} else {
			// Si la requête ou la réponse n'est pas HTTP, continuer sans validation
			chain.doFilter(request, response);
		}
	}
}