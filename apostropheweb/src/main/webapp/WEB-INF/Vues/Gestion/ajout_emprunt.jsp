<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="apostropheivre.dao.LivreDAO" %>
<%@ page import="apostropheivre.dao.ClientDAO" %>
<%@ page import="apostropheivre.dao.LibraireDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LivreDAO livD = new LivreDAO();
    ClientDAO cliD = new ClientDAO();
    LibraireDAO libD = new LibraireDAO();
    try {
        request.setAttribute("Liste_Livres", livD.listerTous());
        request.setAttribute("Liste_Clients", cliD.findAll());
        request.setAttribute("Liste_Libraires", libD.listerTous());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>
<div>
    <form id="detailsEmprunt" method="POST" action="${pageContext.request.contextPath}/gestion/emprunts/emprunter">
        Client :
        <select name="client" id="choixClient">
            <c:forEach items="${Liste_Clients}" var="i">
                <option value="${i.getId()}" <c:if test="${i.getId() == params.client}">selected</c:if>>${i.getId()} - ${i.getNom()} ${i.getPrenom()}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        Livre :
        <select name="livre" id="choixLivre">
            <c:forEach items="${Liste_Livres}" var="i">
                <option value="${i.getId()}" <c:if test="${i.getId() == params.livre}">selected</c:if>>${i.getId()} - ${i.getTitre()}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        Libraire :
        <select name="libraire" id="choixLibraire">
            <c:forEach items="${Liste_Libraires}" var="i">
                <option value="${i.getId()}" <c:if test="${i.getId() == params.libraire}">selected</c:if>>${i.getId()} - ${i.getLib_nom()} ${i.getLib_prenom()}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        Date :
        <c:set var="currentDate" value="<%= new java.util.Date() %>" />
        <fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" var="dateActuelle"/>
        <input type="date" name="date" id="choixDate" value="${dateActuelle}" readonly/>
        <br/>
        <br/>

        <button type="submit" name = "validerModif" value ="1" id="validerButton">Valider</button>

    </form>
</div>

<c:if test="${errorbool}">
    <div> <br/> ${error}</div>
</c:if>