<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
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
            <td><c:out value="${livre.auteur}"/></td>
            <td><c:out value="${livre.isbn}"/></td>
            <td><c:out value="${livre.categorie.nom}"/></td>
            <td style="text-align: center;"><c:out value="${livre.quantite}"/></td>
            <td class="actions">
                <a href="modifier-livre?isbn=${livre.isbn}" class="btn btn-edit">Modifier</a>
                <a href="supprimer-livre?isbn=${livre.isbn}" class="btn btn-delete"
                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce livre ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>