<div style="display: flex; width: 90%;">
    <div style="width: 80%; margin-right: 5px;">
        <div style="width: 100%; position: sticky;"><h1>COMTE</h1></div>
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
            <table style="width: 100%;">
                <jsp:include page="../Administrateur/Comptes/comptes.jsp"></jsp:include>
            </table>
        </div>
    </div>
    <div style="width: 80%; margin-right: 5px;">
        <div style="width: 100%; position: sticky;"><h1>LIBRAIRE</h1></div>
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
            <table style="width: 100%;">
                <jsp:include page="../Administrateur/Libraires/libraires.jsp"></jsp:include>
            </table>
        </div>
    </div>
</div>