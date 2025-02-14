package apostropheivre.membres;

import apostropheivre.dao.ClientDAO;
import apostropheivre.dao.EmpruntDAO;
import apostropheivre.models.Client;
import apostropheivre.models.Emprunt;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

import static apostropheivre.membres.DetailsClientServlet.parseRequestBody;
import static java.lang.Integer.parseInt;

@WebServlet("/gestion/emprunts/emprunter")
public class AjoutEmpruntServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Gestion/ajout_emprunt.jsp");
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

        System.out.println("Corps de la requête: " + requestBody);
        Map<String, String> params = parseRequestBody(requestBody);

        Integer idCli = parseInt(params.get("client"));
        Integer idLiv = parseInt(params.get("livre"));
        Integer idLib = parseInt(params.get("libraire"));

        try {
            Emprunt emprunt = new Emprunt(
                    idCli, idLiv, idLib,
                    Date.valueOf(params.get("date")),
                    0
            );

            EmpruntDAO empD = new EmpruntDAO();

            if (empD.find(idCli, idLiv, idLib) != null) {
                throw new SQLException("Un emprunt identique existe déjà.");
            }

            request.setAttribute("emp", emprunt);

            doPut(request, response);

            response.sendRedirect("../../gestion/emprunts");

        } catch (Exception e) {
            request.setAttribute("parametres",params);

            request.setAttribute("error", e.getMessage());
            request.setAttribute("errorbool", true);

            request.setAttribute("page", "/WEB-INF/Vues/Gestion/ajout_emprunt.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/app.jsp");
            dispatcher.forward(request, response);
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntDAO empD = new EmpruntDAO();
        empD.create((Emprunt) request.getAttribute("emp"));
    }
}
