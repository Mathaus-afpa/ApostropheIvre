<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
<c:forEach var="auteur" items="${auteurs}" varStatus="status">
        <tr>
            <td>${auteur.id}</td>
            <td>${auteur.toString()}</td>
            <td><a href="${pageContext.request.contextPath}/auteur/details?id=${auteur.id}"><button>O</button></a></td>
            <c:if test="${edit}">
                <td><input data-json='<c:out value="${auteur.toJson()}" escapeXml="false" />' type="button" value="+" onclick="edit(this)"></td>
                <td><input type="button" value="-" onclick="remove(${auteur.id})"></td>
            </c:if>
        </tr>
</c:forEach>
</table>