<%--
  Created by IntelliJ IDEA.
  User: annasuleyman
  Date: 21/01/2025
  Time: 08:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Supprimer un Livre - L'Apostrophe Ivre</title>
</head>
<body>
<h1>Confirmer la suppression</h1>

<p>Êtes-vous sûr de vouloir supprimer le livre suivant ?</p>

<div>
    <strong>Titre :</strong> <c:out value="${livre.titre}"/><br>
    <strong>ISBN :</strong> <c:out value="${livre.isbn}"/><br>
    <strong>Auteur :</strong>
    <c:if test="${not empty livre.auteur}">
        <c:out value="${livre.auteur.prenom} ${livre.auteur.nom}"/>
    </c:if>
</div>

<form action="supprimer-livre" method="POST">
    <input type="hidden" name="isbn" value="${livre.isbn}">
    <button type="submit">Confirmer la suppression</button>
    <a href="livres">Annuler</a>
</form>
</body>
</html>