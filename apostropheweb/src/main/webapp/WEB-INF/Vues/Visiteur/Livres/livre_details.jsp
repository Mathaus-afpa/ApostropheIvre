<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="idLivre">ID :</label>
                <input type="text" id="idLivre" name="idLivre" value="${livre.id}" required>
            </c:when>
            <c:otherwise>ID : <p>${livre.id}</p></c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="titreLivre">Titre :</label>
                <input type="text" id="titreLivre" name="titreLivre" value="${livre.titre}" required>
            </c:when>
            <c:otherwise>Titre : <p>${livre.titre}</p></c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="resumeLivre">Resume :</label>
                <textarea id="resumeLivre" name="resumeLivre" required>${livre.resume}</textarea>
            </c:when>
            <c:otherwise>Resume : <p>${livre.resume}</p></c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="isbnLivre">Isbn :</label>
                <input type="text" id="isbnLivre" name="isbnLivre" value="${livre.isbn}" required>
            </c:when>
            <c:otherwise>Isbn : <p>${livre.isbn}</p></c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="quantiteLivre">Quantite :</label>
                <input type="text" id="quantiteLivre" name="quantiteLivre" value="${livre.quantite}" required>
            </c:when>
            <c:otherwise>Quantite : <p>${livre.quantite}</p></c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="categorieLivre">Categorie :</label>
                <input type="text" id="categorieLivre" name="categorieLivre" value="${livre.categorie}" required>
            </c:when>
            <c:otherwise>Titre : <p>${livre.categorie}</p></c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:choose>
            <c:when test="${edit}">
                <label for="auteurLivre">Auteur :</label>
                <input type="text" id="auteurLivre" name="auteurLivre" value="${livre.auteur}" required>
            </c:when>
            <c:otherwise>Auteur : <p>${livre.auteur}</p></c:otherwise>
        </c:choose>
    </li>
</ul>