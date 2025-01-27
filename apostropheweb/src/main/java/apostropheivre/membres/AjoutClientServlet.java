package apostropheivre.membres;

import apostropheivre.dao.ClientDAO;
import apostropheivre.models.Client;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static apostropheivre.membres.DetailsClientServlet.parseRequestBody;

@WebServlet("/gestion/clients/inscription")
public class AjoutClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Gestion/ajout_client.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("../../app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        String requestBody = stringBuilder.toString();

        // Afficher ou traiter le corps de la requête
        System.out.println("Corps de la requête: " + requestBody);
        Map<String, String> params = parseRequestBody(requestBody);

        // Créer un objet Client à partir des paramètres analysés
        try {
            Client client = new Client(
                    params.get("nom"),
                    params.get("prenom"),
                    params.get("adresse"),
                    params.get("codePostal"),
                    params.get("ville"),
                    params.get("email")
            );
            request.setAttribute("cli", client);

            doPut(request,response);

            response.sendRedirect("../../gestion/clients");

        } catch (Exception e) {
            request.setAttribute("parametres",params);

            request.setAttribute("error", e.getMessage());
            request.setAttribute("errorbool", true);

            request.setAttribute("page", "/WEB-INF/Vues/Gestion/ajout_client.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientDAO cld = new ClientDAO();
        cld.create((Client) request.getAttribute("cli"));
    }
}
