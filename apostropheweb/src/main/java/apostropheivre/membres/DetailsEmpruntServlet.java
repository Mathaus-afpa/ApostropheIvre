package apostropheivre.membres;

import apostropheivre.dao.ClientDAO;
import apostropheivre.dao.EmpruntDAO;
import apostropheivre.models.Client;
import apostropheivre.models.Emprunt;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@WebServlet("/gestion/emprunts/details")
public class DetailsEmpruntServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idCli = parseInt(request.getParameter("idCli"));
        Integer idLiv = parseInt(request.getParameter("idLiv"));
        Integer idLib = parseInt(request.getParameter("idLib"));

        EmpruntDAO empD = new EmpruntDAO();

        try {
            if (empD.find(idCli, idLiv, idLib) == null) {
                throw new SQLException("Cet emprunt n'existe pas");
            } else {

                request.setAttribute("idCli", idCli);
                request.setAttribute("idLiv", idLiv);
                request.setAttribute("idLib", idLib);
                request.setAttribute("page", "/WEB-INF/Vues/Gestion/details_emprunt.jsp");

                try {
                    request.setAttribute("emp", empD.find(idCli, idLiv, idLib));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        System.out.println("Corps de la requête: " + requestBody);
        Map<String, String> params = parseRequestBody(requestBody);


        Integer cliOri = parseInt(params.get("idCli"));
        Integer livOri = parseInt(params.get("idLiv"));
        Integer libOri = parseInt(params.get("idLib"));

        Integer idCli = parseInt(params.get("client"));
        Integer idLiv = parseInt(params.get("livre"));
        Integer idLib = parseInt(params.get("libraire"));

        try {
            Emprunt emprunt = new Emprunt(
                    idCli, idLiv, idLib,
                    Date.valueOf(params.get("date")),
                    parseInt(params.get("statut"))
            );

            EmpruntDAO empD = new EmpruntDAO();

            if ((!idCli.equals(cliOri) || !idLiv.equals(livOri) || !idLib.equals(libOri))
                && empD.find(idCli, idLiv, idLib) != null) {
                throw new SQLException("Un emprunt identique existe déjà.");
            }

            request.setAttribute("emp", emprunt);
            request.setAttribute("idCli", idCli);
            request.setAttribute("idLiv", idLiv);
            request.setAttribute("idLib", idLib);

            doPut(request,response);

            response.sendRedirect("../../gestion/emprunts");

        } catch (Exception e) {

            request.setAttribute("idCli", cliOri);
            request.setAttribute("idLiv", livOri);
            request.setAttribute("idLib", libOri);
            request.setAttribute("error", e.getMessage());
            request.setAttribute("errorbool", true);

            request.setAttribute("page", "/WEB-INF/Vues/Gestion/details_emprunt.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
            dispatcher.forward(request, response);
        }


    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntDAO empD = new EmpruntDAO();
        Integer idCli = Integer.parseInt(request.getParameter("idCli"));
        Integer idLiv = Integer.parseInt(request.getParameter("idLiv"));
        Integer idLib = Integer.parseInt(request.getParameter("idLib"));
        Emprunt emp = (Emprunt) request.getAttribute("emp");

        empD.update(emp, idCli, idLiv, idLib);
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
