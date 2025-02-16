package apostropheivre.membres;

import apostropheivre.dao.ClientDAO;
import apostropheivre.utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.SessionTrackingMode.URL;

@WebServlet("/gestion/clients")
public class ListeClientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("page", "/WEB-INF/Vues/Gestion/liste_clients.jsp");
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
                doDelete(request, response);
                response.setHeader("Refresh","0;URL="+request.getRequestURI());

//                ClientDAO cld = new ClientDAO();
//
//                request.setAttribute("Liste_Clients", cld.findAll());
//                request.setAttribute("page", "/WEB-INF/Vues/Gestion/liste_clients.jsp");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("../app.jsp");
//                dispatcher.forward(request, response);
            } catch (Exception e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ClientDAO cld = new ClientDAO();
            cld.delete(Integer.parseInt((String) request.getAttribute("id")));
        } catch (Exception e){
            Log.error(e.getMessage(), e);
        }
    }
}