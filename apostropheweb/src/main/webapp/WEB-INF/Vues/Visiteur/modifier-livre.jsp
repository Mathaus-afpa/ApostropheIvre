<%--
  Created by IntelliJ IDEA.
  User: annasuleyman
  Date: 21/01/2025
  Time: 08:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier un Livre - L'Apostrophe Ivre</title>
</head>
<body>
<h1>Modifier le livre</h1>

<form action="modifier-livre" method="POST">
    <input type="hidden" name="id" value="${livre.id}">
    <input type="hidden" name="isbn" value="${livre.isbn}">

    <div>
        <label for="titre">Titre* :</label>
        <input type="text" id="titre" name="titre" required maxlength="100"
               value="<c:out value="${livre.titre}"/>">
    </div>

    <div>
        <label for="resume">Résumé :</label>
        <textarea id="resume" name="resume" rows="4">
                <c:out value="${livre.resume}"/>
            </textarea>
    </div>

    <div>
        <label for="image">URL de l'image :</label>
        <input type="text" id="image" name="image" maxlength="255"
               value="<c:out value="${livre.image}"/>">
    </div>

    <div>
        <label for="quantite">Quantité* :</label>
        <input type="number" id="quantite" name="quantite" required min="0"
               value="<c:out value="${livre.quantite}"/>">
    </div>

    <div>
        <label for="categorie">Catégorie :</label>
        <select id="categorie" name="categorieId">
            <option value="">Sélectionner une catégorie</option>
            <c:forEach items="${categories}" var="categorie">
                <option value="${categorie.id}"
                        <c:if test="${livre.idCategorie == categorie.id}">selected</c:if>>
                    <c:out value="${categorie.libelle}"/>
                </option>
            </c:forEach>
        </select>
    </div>

    <div>
        <label for="auteur">Auteur :</label>
        <select id="auteur" name="auteurId">
            <option value="">Sélectionner un auteur</option>
            <c:forEach items="${auteurs}" var="auteur">
                <option value="${auteur.id}"
                        <c:if test="${livre.idAuteur == auteur.id}">selected</c:if>>
                    <c:out value="${auteur.prenom} ${auteur.nom}"/>
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit">Enregistrer les modifications</button>
    <a href="livres">Annuler</a>
</form>
</body>
</html>