<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div style="overflow: auto; max-height: 200px">
    <table>
        <thead style="position: sticky; top: 0; z-index: 1; background-color:#f4f7f4 ">
            <tr style="border-style: solid; border-width:1px">
                <th style="border-style: solid; border-width:1px">Nom</th>
                <th style="border-style: solid; border-width:1px">Adresse</th>
                <th style="border-style: solid; border-width:1px">Mail</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${Liste_Clients}" var="i">
                <tr style="border-style: solid; border-width:1px">
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getPrenom()} ${i.getNom()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getAdresse()}, ${i.getCodePostal()} ${i.getVille()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getEmail()}" escapeXml="false"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
