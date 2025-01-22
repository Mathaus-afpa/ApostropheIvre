<%@ page import="apostropheivre.dao.ClientDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% ClientDAO cli = new ClientDAO();
request.setAttribute("Liste_Clients",cli.findAll()); %>

<form id="formListeCli" method="POST" action="${pageContext.request.contextPath}/gestion/clients">
<div style="overflow: auto; max-height: 200px">
    <table>
        <thead style="position: sticky; top: 0; z-index: 1; background-color:#f4f7f4 ">
            <tr style="border-style: solid; border-width:1px">
                <th style="border-style: solid; border-width:1px">ID</th>
                <th style="border-style: solid; border-width:1px">Nom</th>
                <th style="border-style: solid; border-width:1px">Adresse</th>
                <th style="border-style: solid; border-width:1px">Mail</th>
                <th style="border-style: solid; border-width:1px">Modifier</th>
                <th style="border-style: solid; border-width:1px">Supprimer</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${Liste_Clients}" var="i">
                <tr style="border-style: solid; border-width:1px">
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getId()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getPrenom()} ${i.getNom()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getAdresse()}, ${i.getCodePostal()} ${i.getVille()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getEmail()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <a href="${pageContext.request.contextPath}/gestion/clients/details?id=${i.getId()}">
                        <button type="button">Modifier</button>
                        </a>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <button type="submit" name="idSup" value="${i.getId()}" id="supprimerButton">Supprimer</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</form>

<div>
<br/> <br/>
<a href="${pageContext.request.contextPath}/gestion/clients/inscription">
    <button type="button" name="ajoutCli" id="ajoutCliBut">Inscrire un client</button>
</a>
</div>

<script>

    function ajout(event){

    }

    var ajBut = document.getElementById("ajoutCliBut");
    ajBut.addEventListener("click",ajout)
</script>