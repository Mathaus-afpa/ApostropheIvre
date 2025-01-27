<div style="display: flex; width: 90%;">
    <div style="width: 25%; margin-right: 5px;">
        <div style="width: 100%; position: sticky;"><h1>AUTEURS</h1></div>
        <div style="width: 100%; position: sticky;">
            <table style="width: 100%;">
                <tr>
                    <th>ID</th>
                    <th>VALEUR</th>
                    <th colspan="2">EDITION</th>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="button" value="Nouveau">
                    </td>
                </tr>
            </table>
        </div>
        <div style="width: 100%;height: 200px; overflow-y: scroll;">
            <jsp:include page="../Visiteur/Auteurs/auteurs.jsp"></jsp:include>
        </div>
    </div>
    <div style="width: 25%; margin-right: 5px;">
        <div style="width: 100%; position: sticky;"><h1>CATEGORIES</h1></div>
        <div style="width: 100%; position: sticky;">
            <table style="width: 100%;">
                <tr>
                    <th>ID</th>
                    <th>VALEUR</th>
                    <th>EDIT</th>
                    <th>DELETE</th>
                </tr>
            </table>
        </div>
        <div style="width: 100%;height: 200px; overflow-y: scroll;">
        </div>
    </div>
    <div style="width: 25%; margin-right: 5px;">
        <div style="width: 100%; position: sticky;"><h1>LIVRES</h1></div>
        <div style="width: 100%; position: sticky;">
            <table style="width: 100%;">
                <tr>
                    <th>ID</th>
                    <th>VALEUR</th>
                    <th>EDIT</th>
                    <th>DELETE</th>
                </tr>
            </table>
        </div>
        <div style="width: 100%;height: 200px; overflow-y: scroll;">
        </div>
    </div>
    <div style="width: 25%; margin-right: 5px;">
        <div style="width: 100%; position: sticky;"><h1>CLIENT</h1></div>
        <div style="width: 100%; position: sticky;">
            <table style="width: 100%;">
                <tr>
                    <th>ID</th>
                    <th>VALEUR</th>
                    <th>EDIT</th>
                    <th>DELETE</th>
                </tr>
            </table>
        </div>
        <div style="width: 100%;height: 200px; overflow-y: scroll;">
        </div>
    </div>
</div>
<jsp:include page="./auteur_modal.jsp"></jsp:include>