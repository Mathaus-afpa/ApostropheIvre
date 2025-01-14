<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="display: flex; height: 200px; overflow-x: auto;">
    <!-- Auteurs -->
    <div style="width: 25%; overflow-y: scroll; margin-right: 10px;">
        <table>
            <tr style="position:sticky; top:0; background-color: white;">
                <th>ID</th>
                <th>VALEUR</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var="auteur" items="${auteurs}" varStatus="status">
                <tr>
                    <td>${auteur.id}</td>
                    <td>${auteur.nom} ${auteur.prenom}</td>
                    <td><a href="${auteur.url}">Edit</a></td>
                    <td>
                        <button onclick="deleteItem(${auteur.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script>
        function deleteItem( id) {

            alert(id)
            fetch('./auteur/details?id=' + id,  {
                method : "DELETE"
            })
            alert("Out")
        }
    </script>
    <!-- Livres -->
    <div style="width: 25%; overflow-y: scroll; margin-right: 10px;">
        <table>
            <tr>
                <th>ID</th>
                <th>TITRE</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var="livre" items="${livres}" varStatus="status">
                <tr>
                    <td>${livre.id}</td>
                    <td>${livre.nom} ${livre.prenom}</td>
                    <td><a href="${livre.url}">Edit</a></td>
                    <td><a href="#">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!-- Categories -->
    <div style="width: 25%; overflow-y: scroll; margin-right: 10px;">
        <table>
            <tr>
                <th>ID</th>
                <th>CATEGORIE</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var="categorie" items="${categories}" varStatus="status">
                <tr>
                    <td>${categorie.id}</td>
                    <td>${categorie.nom} ${categorie.prenom}</td>
                    <td><a href="${categorie.url}">Edit</a></td>
                    <td><a href="#">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!-- Dinosaures -->
    <div style="width: 25%; overflow-y: scroll;">
        <table>
            <tr>
                <th>ID</th>
                <th>NOM</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var="dino" items="${dinosaures}" varStatus="status">
                <tr>
                    <td>${dino.id}</td>
                    <td>${dino.nom} ${dino.prenom}</td>
                    <td><a href="${dino.url}">Edit</a></td>
                    <td><a href="#">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
