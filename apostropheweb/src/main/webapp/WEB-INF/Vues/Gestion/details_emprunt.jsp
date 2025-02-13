<%@ page import="apostropheivre.models.Client" %>
<%@ page import="apostropheivre.dao.ClientDAO" %>
<%@ page import="apostropheivre.dao.LivreDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="apostropheivre.dao.LibraireDAO" %>
<%@ page import="apostropheivre.models.Emprunt" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Emprunt emp = (Emprunt) request.getAttribute("emp"); %>

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
    <form id="detailsEmprunt" method="POST" action="${pageContext.request.contextPath}/gestion/emprunts/details?idCli=${idCli}&idLiv=${idLiv}&idLib=${idLib}">
        <input type="text" name="idCli" id="idCli" value = "${idCli}" hidden="hidden"/>
        <input type="text" name="idLiv" id="idLiv" value = "${idLiv}" hidden="hidden"/>
        <input type="text" name="idLib" id="idLib" value = "${idLib}" hidden="hidden"/>
        Client :
        <select name="client" id="choixClient">
            <c:forEach items="${Liste_Clients}" var="i">
                <option value="${i.getId()}" <c:if test="${i.getId() == idCli}">selected</c:if>>${i.getId()} - ${i.getNom()} ${i.getPrenom()}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        Livre :
        <select name="livre" id="choixLivre">
            <c:forEach items="${Liste_Livres}" var="i">
                <option value="${i.getId()}" <c:if test="${i.getId() == idLiv}">selected</c:if>>${i.getId()} - ${i.getTitre()}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        Libraire :
        <select name="libraire" id="choixLibraire">
            <c:forEach items="${Liste_Libraires}" var="i">
                <option value="${i.getId()}" <c:if test="${i.getId() == idLib}">selected</c:if>>${i.getId()} - ${i.getLib_nom()} ${i.getLib_prenom()}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        Date :
        <c:set var="currentDate" value="<%= new java.util.Date() %>" />
        <fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" var="dateActuelle"/>
        <fmt:formatDate value="${emp.date_emprunt}" pattern="yyyy-MM-dd" var="dateEmprunt"/>
        <input type="date" name="date" id="choixDate" value="${dateEmprunt}" min="2025-01-09" max="${dateActuelle}" />
        <br/>
        <br/>

        Statut :
        <select name="statut" id="choixStatut">
            <c:forEach var="i" begin="0" end="4">
                <option value="${i}" <c:if test="${emp.getStatut() == i}">selected</c:if>>${i}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>

        <button type="submit" name = "validerModif" value ="1" id="validerButton">Modifier</button>
        <button type="button" name = "annulerModif" value ="Annuler" id="annulerButton">Annuler</button>

    </form>
</div>

<c:if test="${errorbool}">
    <div> <br/> ${error}</div>
</c:if>


<script>

    function sauvegardeEtatInit() {
        const selects = document.querySelectorAll('select');
        const dateIni = document.getElementById("choixDate");
        selects.forEach(select => {
            select.dataset.initialValue = select.value;
        });
        dateIni.dataset.initialValue = dateIni.value;
    }

    sauvegardeEtatInit();

    function annulerModifs() {
        const selects = document.querySelectorAll('select');
        const dateIni = document.getElementById("choixDate");
        selects.forEach(select => {
            select.value = select.dataset.initialValue;
        });
        dateIni.value = dateIni.dataset.initialValue;
    }

    var annMod = document.getElementById("annulerButton");
    annMod.addEventListener("click",annulerModifs)
</script>
