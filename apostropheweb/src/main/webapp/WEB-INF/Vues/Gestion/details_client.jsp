<%@ page import="apostropheivre.models.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Client cli = (Client) request.getAttribute("cli"); %>

<div>
    <form id="frommmm" method="POST" action="${pageContext.request.contextPath}/gestion/clients/details?id=${id}">
        <input type="text" name="id" id="id" value = "${id}" hidden="hidden"/>
        Nom :
        <input type="text" name="nom" id="nom" value = "${cli.getNom()}" required/>
        <br/>
        <br/>

        Prénom :
        <input type="text" name="prenom" id="prenom" value = "${cli.getPrenom()}" required/>
        <br/>
        <br/>

        Adresse :
        <input type="text" name="adresse" id="adresse" value="${cli.getAdresse()}" required >
        <br/>
        <br/>

        Code postal :
        <input type="text" name="codePostal" id="cp" value = "${cli.getCodePostal()}" required >
        <br/>
        <br/>

        Ville :
        <input type="text" name="ville" id="ville" value = "${cli.getVille()}" required >
        <br/>
        <br/>

        Email :
        <input type="text" name="email" id="email" value = "${cli.getEmail()}" required >
        <br/>
        <br/>

        <button type="submit" name = "validerModif" value ="${id}" id="validerButton">Modifier</button>
        <button type="button" name = "annulerModif" value ="Annuler" id="annulerButton">Annuler</button>

    </form>
</div>

<c:if test="${errorbool}">
    <div> <br/> ${error}</div>
</c:if>


<script>
    var id = "${cli.getId()}";
    function annuler(){
        document.getElementById("nom").value="${cli.getNom()}";
        document.getElementById("prenom").value="${cli.getPrenom()}";
        document.getElementById("adresse").value="${cli.getAdresse()}";
        document.getElementById("cp").value="${cli.getCodePostal()}";
        document.getElementById("ville").value="${cli.getVille()}";
        document.getElementById("email").value="${cli.getEmail()}";
    }

    var annMod = document.getElementById("annulerButton");
    annMod.addEventListener("click",annuler)
</script>
