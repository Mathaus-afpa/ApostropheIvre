<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <c:forEach var="categorie" items="${categories}">
        <tr>
            <td>${categorie.id}</td>
            <td>${categorie.toString()}</td>
        </tr>
    </c:forEach>
</table>