package apostropheivre.utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;

public class CsrfUtil {

    private static final String CSRF_TOKEN_SESSION_KEY = "csrfToken";

    // Générer un nouveau token CSRF et le stocker dans la session
    public static String generateCsrfToken(HttpSession session) {
        String csrfToken = UUID.randomUUID().toString();
        session.setAttribute(CSRF_TOKEN_SESSION_KEY, csrfToken);
        return csrfToken;
    }

    // Récupérer le token CSRF depuis la session
    public static String getCsrfTokenFromSession(HttpSession session) {
        if (session == null) {
            return null;
        }
        return (String) session.getAttribute(CSRF_TOKEN_SESSION_KEY);
    }

    // Vérifier que le token CSRF de la requête correspond à celui de la session
    public static boolean isCsrfTokenValid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String sessionCsrfToken = getCsrfTokenFromSession(session);
        String requestCsrfToken = request.getParameter("csrfToken");

        return sessionCsrfToken != null && sessionCsrfToken.equals(requestCsrfToken);
    }
}
