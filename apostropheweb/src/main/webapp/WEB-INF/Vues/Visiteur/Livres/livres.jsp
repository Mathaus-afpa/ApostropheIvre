<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <c:forEach var="livres" items="${livress}">
        <tr>
            <td>${livres.id}</td>
            <td>${livres.toString()}</td>
        </tr>
    </c:forEach>
</table>