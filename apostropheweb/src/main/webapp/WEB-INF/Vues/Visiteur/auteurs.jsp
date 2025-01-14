<%@ page import="apostropheivre.models.Auteur" %>
<%@ page import="java.util.List" %><%--<%@ page import="apostropheivre.models.Auteur"%><%@ page import="java.util.List"%><%@ page contentType="application/json;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>

<%--&lt;%&ndash; Set the content disposition header &ndash;%&gt;--%>
<%--<%--%>
<%--   // Returns all employees (active and terminated) as json.--%>
<%--   response.setContentType("application/json");--%>
<%--   response.setCharacterEncoding("UTF-8");--%>
<%--   response.setHeader("Sec-Fetch-Dest", "empty");--%>
<%--%>--%>

<%--    <%--%>
<%--        // Récupérer la liste depuis le scope application--%>
<%--        List<Auteur> auteurs = (List<Auteur>) application.getAttribute("auteurs");--%>
<%--    %>[--%>
<%--    <c:forEach var="auteur" items="${auteurs}" varStatus="status">--%>
<%--    {--%>
<%--        "id": "${auteur.id}",--%>
<%--        "nom": "${auteur.nom}",--%>
<%--        "prenom": "${auteur.prenom}",--%>
<%--        "url": "${auteur.url}"--%>
<%--    }<c:if test="${!status.last}">,</c:if>--%>
<%--    </c:forEach>--%>
<%--]--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="auteur" items="${auteurs}" varStatus="status">
    <tr>
        <td>${auteur.id}</td>
        <td>${auteur.toString()}</td>
        <c:if test="${edit}">
            <td>
                <input type="button" value="+" onclick="edit()">
            </td>
            <td>
                <input type="button" value="-" onclick="remove()">
            </td>
            <script>
                function edit() {
                    alert("EDIT");
                }
                function remove() {
                    alert("DELETE");
                }
            </script>
        </c:if>
        <td><a href="./auteur/details?id=${auteur.id}"><button>O</button></a></td>
    </tr>
</c:forEach>