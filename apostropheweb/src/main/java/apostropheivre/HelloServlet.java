package apostropheivre;

import java.io.*;

import apostropheivre.models.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    public void init() {
        Abonne abonne = new Abonne();
        Auteur auteur = new Auteur();
        Compte compte = new Compte();
        Emprunt emprunt = new Emprunt();
        Libraire libraire = new Libraire();
        Livre livre = new Livre();
        message = ApostropheIvre.HelloWorld();
        message += "<br>" + abonne.toString();
        message += "<br>" + auteur.toString();
        message += "<br>" + compte.toString();
        message += "<br>" + emprunt.toString();
        message += "<br>" + libraire.toString();
        message += "<br>" + livre.toString();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}