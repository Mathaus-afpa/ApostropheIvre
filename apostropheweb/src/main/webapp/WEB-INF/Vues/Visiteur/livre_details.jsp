<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-4">
    <h2>Détails du livre</h2>

    <c:if test="${empty livre}">
        <div class="alert alert-warning">
            Livre non trouvé.
        </div>
    </c:if>

    <c:if test="${not empty livre}">
        <div class="card">
            <div class="card-body">
                <table class="table table-bordered">
                    <tr>
                        <th style="width: 150px;">Titre</th>
                        <td><c:out value="${livre.titre}"/></td>
                    </tr>
                    <tr>
                        <th>Image</th>
                        <td><img
                            src="${pageContext.request.contextPath}/Images/livres/<c:out value='${livre.image}'/>"
                            alt="<c:out value='${livre.titre}'/>">
                        </td>
                    </tr>
                    <tr>
                        <th>Auteur</th>
                        <td><c:out value="${livre.idAuteur}"/></td>
                    </tr>
                    <tr>
                        <th>ISBN</th>
                        <td><c:out value="${livre.isbn}"/></td>
                    </tr>
                    <tr>
                        <th>Résumé</th>
                        <td><c:out value="${livre.resume}"/></td>
                    </tr>
                    <tr>
                        <th>Quantité</th>
                        <td><c:out value="${livre.quantite}"/></td>
                    </tr>
                    <tr>
                        <th>Catégorie</th>
                        <td><c:out value="${livre.idCategorie}"/></td>
                    </tr>
                </table>

                <div class="mt-3">
                    <a href="liste" class="btn btn-secondary">Retour à la liste</a>
                    <a href="modifier?id=${param.id}" class="btn btn-primary">Modifier</a>
                    <a href="supprimer?id=${param.id}" class="btn btn-danger"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce livre ?')">
                        Supprimer
                    </a>
                </div>
            </div>
        </div>
    </c:if>
</div>