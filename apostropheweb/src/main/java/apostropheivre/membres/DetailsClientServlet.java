package apostropheivre.membres;

import apostropheivre.dao.ClientDAO;
import apostropheivre.models.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/gestion/clients/details")
public class DetailsClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer id = Integer.parseInt(request.getParameter("id"));

        ClientDAO cld = new ClientDAO();
        request.setAttribute("cli", cld.find(id));

        request.setAttribute("id", id);
        request.setAttribute("page", "/WEB-INF/Vues/Gestion/details_client.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
        dispatcher.forward(request, response);

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
        client.setId(Integer.parseInt(params.get("id")));
        request.setAttribute("cli", client);
        request.setAttribute("id", client.getId());

        doPut(request,response);

        response.sendRedirect("../../gestion/clients");

        } catch (Exception e) {
            Integer id = Integer.parseInt(request.getParameter("id"));

            ClientDAO cld = new ClientDAO();

            Client client = cld.find(id);

            client.setId(Integer.parseInt(params.get("id")));
            request.setAttribute("cli", client);
            request.setAttribute("id", client.getId());
            request.setAttribute("error", e.getMessage());
            request.setAttribute("errorbool", true);

            request.setAttribute("page", "/WEB-INF/Vues/Gestion/details_client.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientDAO cld = new ClientDAO();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Client cli = (Client) request.getAttribute("cli");

        cld.update(cli, id);
    }

    public static Map<String, String> parseRequestBody(String requestBody) {
        Map<String, String> params = new HashMap<>();

        try {
            String[] pairs = requestBody.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = URLDecoder.decode(keyValue[1], "UTF-8");
                params.put(key, value);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return params;
    }
}
