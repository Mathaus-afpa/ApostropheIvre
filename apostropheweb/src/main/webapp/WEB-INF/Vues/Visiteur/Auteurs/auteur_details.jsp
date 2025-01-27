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
<script>
    function setDefaultImage(img) {
        img.src = 'data:image/svg+xml,%3Csvg%20width%3D%22100px%22%20height%3D%22100px%22%20viewBox%3D%220%200%2014%2014%22%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%3E%0A%20%20%3Cpath%20d%3D%22M7%2014A7%207%200%201%201%207%200a7%207%200%200%201%200%2014z%22%20/%3E%0A%20%20%3Cpath%20d%3D%22M7%2013A6%206%200%201%200%207%201a6%206%200%200%200%200%2012z%22%20fill%3D%22%23FFF%22%20style%3D%22fill%3A%20var%28--svg-status-bg%2C%20%23fff%29%3B%22%20fill-rule%3D%22nonzero%22/%3E%0A%20%20%3Cpath%20d%3D%22M8.16%207.184c.519-.37.904-.857%201.07-1.477.384-1.427-.619-2.897-2.246-2.897-.732%200-1.327.26-1.766.692a2.163%202.163%200%200%200-.509.743.75.75%200%200%200%201.4.54.78.78%200%200%201%20.16-.213c.168-.165.39-.262.715-.262.597%200%20.936.496.798%201.007-.067.249-.235.462-.492.644-.231.165-.47.264-.601.3a.75.75%200%200%200-.556.724v1.421a.75.75%200%200%200%201.5%200v-.909c.168-.082.346-.185.526-.313z%22%20fill-rule%3D%22nonzero%22/%3E%0A%20%20%3Cellipse%20cx%3D%226.889%22%20cy%3D%2210.634%22%20rx%3D%221%22%20ry%3D%221%22/%3E%0A%3C/svg%3E';
    }
</script>