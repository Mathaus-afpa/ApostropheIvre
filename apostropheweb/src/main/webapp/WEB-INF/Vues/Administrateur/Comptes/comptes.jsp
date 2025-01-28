<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <c:forEach var="compte" items="${comptes}">
        <tr>
            <td>${compte.id}</td>
            <td>${compte.toString()}</td>
        </tr>
    </c:forEach>
</table>