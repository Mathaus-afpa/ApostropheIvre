package apostropheivre;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/")
public class ApostropheIvreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la route
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.equals("/")) {
            response.sendRedirect("./accueil");
        } else {
        // Le chemin vers le fichier dans le système de fichiers
        String filePath = getServletContext().getRealPath(path);
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
        } else {
            // Si le fichier n'est pas trouvé, renvoyer une erreur 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Fichier non trouvé");
        }
        }
    }
}