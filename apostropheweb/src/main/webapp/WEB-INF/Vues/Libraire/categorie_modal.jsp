<div id="modalCategorie" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: white; border: 1px solid #ccc; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); padding: 1em; z-index: 1000;">
    <form id="formulaireCategorie" action="${pageContext.request.contextPath}/Categorie/details" method="post">
        <jsp:include page="/WEB-INF/Vues/Visiteur/Categories/Categorie_details.jsp" />
        <div><input type="file" id="url" name="url"></div>
        <div><input id="valider" type="submit" value="Valider"></div>
    </form>
    <button id="fermerModalCategorie">Annuler</button>
</div>
<script>
    /**
     * Script pour la gestion de formulaire d'Categorie, du modal et des opérations d'édition et suppression.
     * - Gère la soumission du formulaire avec des méthodes PUT ou POST selon la présence d'un ID.
     * - Ferme le modal lorsqu'un bouton est cliqué.
     * - Permet d'éditer un Categorie et de pré-remplir le formulaire avec ses données.
     * - Permet de supprimer un Categorie après confirmation.
     */
        // Sélection des éléments du DOM
    const fermerModalCategorie = document.getElementById('fermerModalCategorie');
    const modalCategorie = document.getElementById('modalCategorie');
    const formulaireCategorie = document.getElementById('formulaireCategorie');
    const urlCategorie = '${pageContext.request.contextPath}/Categorie/details';
    // Fonction pour fermer le modal
    fermerModalCategorie?.addEventListener('click', () => {
        if (modalCategorie) {
            modalCategorie.style.display = 'none';
        }
    });
    // Fonction pour soumettre le formulaire
    formulaireCategorie?.addEventListener('submit', function (event) {
        event.preventDefault(); // Empêche la soumission par défaut du formulaire
        // Crée un objet JSON à partir du formulaire
        const formData = new FormData(formulaireCategorie);
        const jsonObject = {
            "id" : formData.get("idCategorie"),
            "nom" : formData.get("nomCategorie"),
            "prenom" : formData.get("prenomCategorie"),
            "url" : formData.get("url")
        };
        formData.forEach((value, key) => {
            // Si le champ est une URL, utilise le nom du fichier, sinon garde la valeur
            jsonObject[key] = key !== 'url' ? value : value.name;
        });
        // Détermine la méthode HTTP (PUT ou POST) selon la présence d'un ID
        const method = jsonObject.id > 0 ? 'PUT' : 'POST';
        // Configuration pour les requêtes fetch
        const fetchOptions = {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(jsonObject)
        };
        // Envoi de la requête avec fetch
        fetch(urlCategorie, fetchOptions)
            .then(response => {
                if (!response.ok) { throw new Error(`Erreur HTTP : ${response.status}`); }
                return response.text();
            })
            .then(data => {
                console.log('Réponse du serveur :', data);
                // Gestion des retours (affichage, mise à jour de l'interface, etc.)
            })
            .catch(error => {
                console.error('Erreur lors de la requête :', error);
            });
    });
    // Fonction pour éditer un Categorie
    function edit(element) {
        const modal = document.getElementById("modalCategorie");
        const data = JSON.parse(element.getAttribute('data-json'));
        const form = document.getElementById('formulaireCategorie');
        if (modal && form) {
            modal.style.display = "block";
            form.querySelector(`[name="idCategorie"]`).value = data.id;
            form.querySelector(`[name="nomCategorie"]`).value = data.nom;
            form.querySelector(`[name="prenomCategorie"]`).value = data.prenom;
            form.querySelector(`[name="url"]`).value = "";
        }
    }
    // Fonction pour supprimer un Categorie
    function remove(id) {
        const confirmation = confirm("Are you sure you want to delete?");
        if (confirmation) {
            fetch(urlCategorie + `?id=` + id, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        alert('Deleted successfully!');
                    } else {
                        alert('Failed to delete.');
                    }
                })
                .catch(error => {
                    alert('An error occurred: ' + error.message);
                });
        } else {
            alert("Operation cancelled");
        }
    }
</script>