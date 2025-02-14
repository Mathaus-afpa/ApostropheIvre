<%@ page import="apostropheivre.dao.*" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% EmpruntDAO emd =new EmpruntDAO() ;
    request.setAttribute("Liste_Emprunts",emd.findAll());

    LivreDAO lid = new LivreDAO();
    request.setAttribute("livD", lid);

    ClientDAO cld = new ClientDAO();
    request.setAttribute("cliD",cld);

    LibraireDAO brd = new LibraireDAO();
    request.setAttribute("brD",brd);
%>

<form id="formListeEmp" method="POST" action="${pageContext.request.contextPath}/gestion/emprunts">
    <div style="overflow: auto; max-height: 200px">
        <table>
            <thead style="position: sticky; top: 0; z-index: 1; background-color:#f4f7f4 ">
            <tr style="border-style: solid; border-width:1px">
                <th style="border-style: solid; border-width:1px">Livre</th>
                <th style="border-style: solid; border-width:1px">Emprunteur</th>
                <th style="border-style: solid; border-width:1px">Date d'emprunt</th>
                <th style="border-style: solid; border-width:1px">Libraire</th>
                <th style="border-style: solid; border-width:1px">Statut</th>
                <th style="border-style: solid; border-width:1px">Modifier</th>
                <th style="border-style: solid; border-width:1px">Supprimer</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${Liste_Emprunts}" var="i">
                <tr style="border-style: solid; border-width:1px">
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${livD.trouverParId(i.getId_livre()).getTitre()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${cliD.find(i.getId_client()).getNom()} ${cliD.find(i.getId_client()).getPrenom()} " escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getDate_emprunt()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${brD.find(i.getId_libraire()).getLib_nom()} ${brD.find(i.getId_libraire()).getLib_prenom()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <c:out value="${i.getStatut()}" escapeXml="false"/>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <a href="${pageContext.request.contextPath}/gestion/emprunts/details?idCli=${i.id_client}&idLiv=${i.id_livre}&idLib=${i.id_libraire}">
                            <button type="button">Modifier</button>
                        </a>
                    </td>
                    <td style="border-style: solid; border-width:1px">
                        <button type="submit" name="idSup" value="${i.id_client},${i.id_livre},${i.id_libraire}"
                                id="supprimerButton">Supprimer</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>

<div>
    <br/> <br/>
    <a href="${pageContext.request.contextPath}/gestion/emprunts/emprunter">
        <button type="button" name="ajoutEmp" id="ajoutEmpBut">Ajouter un emprunt</button>
    </a>
</div>

<script>

    function ajout(event){

    }

    var ajBut = document.getElementById("ajoutEmpBut");
    ajBut.addEventListener("click",ajout)
</script>