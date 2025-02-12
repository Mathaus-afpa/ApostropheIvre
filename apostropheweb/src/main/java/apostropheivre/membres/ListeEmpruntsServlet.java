package apostropheivre.membres;

import apostropheivre.dao.EmpruntDAO;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/gestion/emprunts")
public class ListeEmpruntsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Gestion/liste_emprunts.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("../app.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            Log.error(e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", request.getParameter("idSup"));
        if (request.getAttribute("id") != null) {
            doDelete(request, response);

            try {
                EmpruntDAO emd = new EmpruntDAO();

                request.setAttribute("Liste_Emprunts", emd.findAll());
                request.setAttribute("page", "/WEB-INF/Vues/Gestion/liste_emprunts.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("../app.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EmpruntDAO emd = new EmpruntDAO();
            emd.delete(Integer.parseInt((String) request.getAttribute("id")));
        } catch (Exception e){
            Log.error(e.getMessage(), e);
        }
    }
}