package apostropheivre;
import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import static apostropheivre.PAGES.*;
@WebServlet("/")
public class ApostropheIvreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getRequestURI().substring(request.getContextPath().length());
        if (ROUTES.isRoutable(route)) {
            response.sendRedirect("./accueil");
//            if (!ROUTES.isController(route)) {
//                RequestDispatcher dispatcher = request.getRequestDispatcher(APP);
//                dispatcher.forward(request, response);
//            }
        } else {
            // Le chemin vers le fichier dans le système de fichiers
            String filePath = getServletContext().getRealPath(route);
            File file = new File(filePath);
            if (file.exists() && !file.isDirectory()) {
                // Déterminer le type MIME du fichier en fonction de son extension
                String mimeType = getServletContext().getMimeType(file.getName());
                // Si le type MIME n'est pas trouvé, on utilise "application/octet-stream" par défaut
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                // Définir le type MIME dans la réponse
                response.setContentType(mimeType);
                // Définir la taille du fichier
                response.setContentLength((int) file.length());
                // Ouvrir le fichier et l'envoyer dans le flux de sortie de la réponse HTTP
                try (FileInputStream fileInputStream = new FileInputStream(file);
                     OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                }
            }
        }
    }
    private void doRouting(String route, HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (ROUTES.getRoute(route)) {
            case CONTROLLEUR:
                response.sendRedirect("./accueil");
            case ACCUEIL:
                request.setAttribute("page", ACCUEIL);
                break;
            case INSCRIPTION:
                request.setAttribute("page", INSCRIPTION);
                break;
            case CONNEXION:
                request.setAttribute("page", CONNEXION);
                break;
            case DECONNEXION:
                request.setAttribute("page", DECONNEXION);
                break;
            case ADMINISTRATEUR:
                request.setAttribute("page", COMPTE_ADMIN);
                break;
            case AUTEUR:
                request.setAttribute("page", DETAILS_AUTEUR);
                break;
            case AUTEURS:
                request.setAttribute("page", LISTE_AUTEURS);
                break;
            case CLIENT:
                request.setAttribute("page", COMPTE_CLIENT);
                break;
            case LIBRAIRE:
                request.setAttribute("page", COMPTE_LIBRAIRE);
                break;
            case LIVRE:
                request.setAttribute("page", ACCUEIL);
                break;
            case LIVRES:
                request.setAttribute("page", ACCUEIL);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

//        // Récupérer la route
//        String path = request.getRequestURI().substring(request.getContextPath().length());
//        if (path.equals("/")) {
//            response.sendRedirect("./accueil");
//        } else {
//        // Le chemin vers le fichier dans le système de fichiers
//        String filePath = getServletContext().getRealPath(path);
//        File file = new File(filePath);
//        if (file.exists() && !file.isDirectory()) {
//            // Déterminer le type MIME du fichier en fonction de son extension
//            String mimeType = getServletContext().getMimeType(file.getName());
//            // Si le type MIME n'est pas trouvé, on utilise "application/octet-stream" par défaut
//            if (mimeType == null) {
//                mimeType = "application/octet-stream";
//            }
//            // Définir le type MIME dans la réponse
//            response.setContentType(mimeType);
//            // Définir la taille du fichier
//            response.setContentLength((int) file.length());
//            // Ouvrir le fichier et l'envoyer dans le flux de sortie de la réponse HTTP
//            try (FileInputStream fileInputStream = new FileInputStream(file);
//                 OutputStream outputStream = response.getOutputStream()) {
//                byte[] buffer = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//                outputStream.flush();
//            }
//        } else {
//            // Si le fichier n'est pas trouvé, renvoyer une erreur 404
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Fichier non trouvé");
//        }
//        }