<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="width: 60%;">
    <div style="float: left; width: 50%;">
        <img id="photoAuteur"
             src="${auteur.url}"
             alt="Aperçu de l'image"
             onerror="setDefaultImage(this);"
             width="60%"
        />
    </div>
    <div style="float: left; width: 50%;">
        <ul>
            <li>
                <c:choose>
                    <c:when test="${edit}">
                        <label for="idAuteur">ID :</label>
                        <input type="text" id="idAuteur" name="idAuteur" value="${auteur.id}" required>
                    </c:when>
                    <c:otherwise>ID : <p>${auteur.id}</p></c:otherwise>
                </c:choose>

            </li>
            <li>
                <c:choose>
                    <c:when test="${edit}">
                        <label for="nomAuteur">Nom :</label>
                        <input type="text" id="nomAuteur" name="nomAuteur" value="${auteur.nom}" required>
                    </c:when>
                    <c:otherwise>Nom : <p>${auteur.nom}</p></c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${edit}">
                        <label for="prenomAuteur">Prénom :</label>
                        <input type="text" id="prenomAuteur" name="prenomAuteur" value="${auteur.prenom}" required>
                    </c:when>
                    <c:otherwise>Prénom : <p>${auteur.prenom}</p></c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</div>