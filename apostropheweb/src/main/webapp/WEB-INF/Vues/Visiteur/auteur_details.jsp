<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <label for="idAuteur">ID :</label>
    <input type="text" id="idAuteur" name="idAuteur" value="${auteur.id}" required><br><br>
    <label for="nomAuteur">Nom :</label>
    <input type="text" id="nomAuteur" name="nomAuteur" value="${auteur.nom}" required><br><br>
    <label for="prenomAuteur">Prénom :</label>
    <input type="text" id="prenomAuteur" name="prenomAuteur" value="${auteur.prenom}" required><br><br>
    <img id="imagePreview"
         src="${auteur.url}"
         alt="Aperçu de l'image"
         onerror="setDefaultImage(this);"
         width="100px"
    />
</div>
<script>
    function setDefaultImage(img) {
        img.src = 'data:image/svg+xml;utf8,<svg width="100px" height="100px" viewBox="0 0 14 14" xmlns="http://www.w3.org/2000/svg">' +
           '<path d="M 10 10 H 90 V 90 H 10 Z" fill="red" stroke="black"/>' +
            '</svg>';
    }
</script>