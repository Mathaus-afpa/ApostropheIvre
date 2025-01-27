<%--
  Created by IntelliJ IDEA.
  User: annasuleyman
  Date: 21/01/2025
  Time: 08:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Livre - L'Apostrophe Ivre</title>
</head>
<body>
<h1>Ajouter un nouveau livre</h1>

<form action="ajouter-livre" method="POST">
    <div>
        <label for="titre">Titre* :</label>
        <input type="text" id="titre" name="titre" required maxlength="100">
    </div>

    <div>
        <label for="isbn">ISBN* :</label>
        <input type="text" id="isbn" name="isbn" required maxlength="13"
               pattern="[0-9]{13}" title="L'ISBN doit contenir 13 chiffres">
    </div>

    <div>
        <label for="resume">Résumé :</label>
        <textarea id="resume" name="resume" rows="4"></textarea>
    </div>

    <div>
        <label for="image">URL de l'image :</label>
        <input type="text" id="image" name="image" maxlength="255">
    </div>

    <div>
        <label for="quantite">Quantité* :</label>
        <input type="number" id="quantite" name="quantite" required min="0" value="0">
    </div>

    <div>
        <label for="categorie">Catégorie :</label>
        <select id="categorie" name="categorieId">
            <option value="">Sélectionner une catégorie</option>
            <c:forEach items="${categories}" var="categorie">
                <option value="${categorie.id}">
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
                <option value="${auteur.id}">
                    <c:out value="${auteur.prenom} ${auteur.nom}"/>
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit">Ajouter le livre</button>
    <a href="livres">Annuler</a>
</form>
</body>
</html>