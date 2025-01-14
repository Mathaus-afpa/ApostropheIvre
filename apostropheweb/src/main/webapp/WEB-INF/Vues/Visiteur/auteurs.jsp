<%@ page import="apostropheivre.models.Auteur"%><%@ page import="java.util.List"%><%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Set the content disposition header --%>
<%
   // Returns all employees (active and terminated) as json.
   response.setContentType("application/json");
   response.setCharacterEncoding("UTF-8");
   response.setHeader("Sec-Fetch-Dest", "empty");
%>

    <%
        // Récupérer la liste depuis le scope application
        List<Auteur> auteurs = (List<Auteur>) application.getAttribute("auteurs");
    %>[
    <c:forEach var="auteur" items="${auteurs}" varStatus="status">
    {
        "id": "${auteur.id}",
        "nom": "${auteur.nom}",
        "prenom": "${auteur.prenom}",
        "url": "${auteur.url}"
    }<c:if test="${!status.last}">,</c:if>
    </c:forEach>
]