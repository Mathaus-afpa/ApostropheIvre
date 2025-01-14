<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="compte" items="${comptes}" varStatus="status">
    <tr>
        <td>${compte.id}</td>
        <td>${compte.toString()}</td>
        <td>${compte.role}</td>
        <td><a href="./auteur/details?id=${auteur.id}"><button>O</button></a></td>
    </tr>
</c:forEach>