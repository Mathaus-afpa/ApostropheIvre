<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <c:forEach var="livre" items="${livres}">
        <tr>
            <td>${livre.id}</td>
            <td>${livre.toString()}</td>
            <td><a href="${pageContext.request.contextPath}/livre?id=${livre.id}">voir</a></td>
        </tr>
    </c:forEach>
</table>