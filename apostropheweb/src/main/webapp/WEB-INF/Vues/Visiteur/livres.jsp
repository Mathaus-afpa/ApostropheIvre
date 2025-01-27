<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Livres - L'Apostrophe Ivre</title>
</head>
<body>
<header>
    <h1>Gestion des Livres</h1>
    <!-- Bouton Ajouter qui redirige vers le formulaire d'ajout -->
    <nav>
        <a href="${pageContext.request.contextPath}/ajouter-livre">Ajouter un nouveau livre</a>
    </nav>
</header>

<main>
    <table border="1">
        <thead>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>ISBN</th>
            <th>Catégorie</th>
            <th>Quantité</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${livres}" var="livre">
            <tr>
                <td><c:out value="${livre.titre}"/></td>
                <td><c:out value="${livre.idAuteur}"/></td>
                <td><c:out value="${livre.isbn}"/></td>
                <td><c:out value="${livre.idCategorie}"/></td>
                <td><c:out value="${livre.quantite}"/></td>
                <td>
                    <!-- Boutons d'actions -->
                    <div class="actions">
                        <!-- Bouton Modifier qui redirige vers le formulaire de modification -->
                        <a href="${pageContext.request.contextPath}/modifier-livre?isbn=${livre.isbn}">
                            Modifier
                        </a>
                        <a href="${pageContext.request.contextPath}/livre/details?id=${livre.id}">/livres/details?id=${livre.id}</a><br><br>
                        <!-- Bouton Supprimer avec confirmation -->
                        <form action="${pageContext.request.contextPath}/supprimer-livre"
                              method="POST"
                              style="display: inline;"
                              onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce livre ?');">
                            <input type="hidden" name="isbn" value="${livre.isbn}">
                            <button type="submit">Supprimer</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Message si aucun livre -->
    <c:if test="${empty livres}">
        <p>Aucun livre disponible.</p>
    </c:if>
</main>
</body>
</html>