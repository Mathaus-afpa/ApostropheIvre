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
import java.sql.Array;

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

            try {
                System.out.println(request.getParameter("idSup"));
                doDelete(request, response);
                response.setHeader("Refresh","0;URL="+request.getRequestURI());

//                EmpruntDAO emd = new EmpruntDAO();
//
//                request.setAttribute("Liste_Emprunts", emd.findAll());
//                request.setAttribute("page", "/WEB-INF/Vues/Gestion/liste_emprunts.jsp");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("../app.jsp");
//                dispatcher.forward(request, response);
            } catch (ServletException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EmpruntDAO emd = new EmpruntDAO();
            String[] values=request.getAttribute("id").toString().split(",");
            emd.delete(Integer.parseInt(values[0]), Integer.parseInt(values[1]),Integer.parseInt(values[2]));
        } catch (Exception e){
            Log.error(e.getMessage(), e);
        }
    }
}