<%@ page import="apostropheivre.models.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <form id="formAjout" method="POST" action="${pageContext.request.contextPath}/gestion/clients/inscription">
        Nom :
        <input type="text" name="nom" id="nom" value="${parametres.nom}" required/>
        <br/>
        <br/>

        Prénom :
        <input type="text" name="prenom" id="prenom" value="${parametres.prenom}" required/>
        <br/>
        <br/>

        Adresse :
        <input type="text" name="adresse" id="adresse" value="${parametres.adresse}" required >
        <br/>
        <br/>

        Code postal :
        <input type="text" name="codePostal" id="cp" value="${parametres.codePostal}" required >
        <br/>
        <br/>

        Ville :
        <input type="text" name="ville" id="ville" value="${parametres.ville}" required >
        <br/>
        <br/>

        Email :
        <input type="text" name="email" id="email" value="${parametres.email}" required >
        <br/>
        <br/>

        <button type="submit" name = "validerAjout" value="valider" id="validerButton">Inscrire client</button>

    </form>
</div>

<c:if test="${errorbool}">
    <div> <br/> ${error}</div>
</c:if>