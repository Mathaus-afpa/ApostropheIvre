<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <c:forEach var="categorie" items="${categories}" varStatus="status">
        <tr>
            <td>${categorie.id}</td>
            <td>${categorie.toString()}</td>
            <c:if test="${edit}">
                <td><input data-json='<c:out value="${categorie.toJson()}" escapeXml="false" />' type="button" value="+" onclick="edit(this)"></td>
                <td><input type="button" value="-" onclick="remove(${categorie.id})"></td>
            </c:if>
        </tr>
    </c:forEach>
</table>