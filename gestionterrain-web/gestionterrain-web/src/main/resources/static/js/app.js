// API Base URL
const API_URL = '/api';

// State
let currentTab = 'terrains';
let terrains = [];
let clients = [];
let reservations = [];
let paiements = [];

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    loadTerrains();
    loadClients();
    loadReservations();
    loadPaiements();
});

// Tab Management
function showTab(tabName) {
    currentTab = tabName;
    
    // Update tab buttons
    document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
    event.target.closest('.tab-btn').classList.add('active');
    
    // Update tab content
    document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
    document.getElementById(`${tabName}-tab`).classList.add('active');
}

// Modal Management
function showModal(content) {
    document.getElementById('modal-body').innerHTML = content;
    document.getElementById('modal').classList.add('show');
}

function closeModal() {
    document.getElementById('modal').classList.remove('show');
}

// ============= TERRAINS =============

function loadTerrains() {
    fetch(`${API_URL}/terrains`)
        .then(response => response.json())
        .then(data => {
            terrains = data;
            renderTerrains();
        })
        .catch(error => console.error('Erreur:', error));
}

function renderTerrains() {
    const tbody = document.querySelector('#terrains-table tbody');
    tbody.innerHTML = terrains.map(terrain => `
        <tr>
            <td>${terrain.id}</td>
            <td>${terrain.nom}</td>
            <td>${terrain.type}</td>
            <td>${terrain.tarifHeure} MAD</td>
            <td><span class="badge badge-${getStatutClass(terrain.statut)}">${terrain.statut}</span></td>
            <td>
                <button class="btn btn-warning" onclick="editTerrain(${terrain.id})">Modifier</button>
                <button class="btn btn-danger" onclick="deleteTerrain(${terrain.id})">Supprimer</button>
            </td>
        </tr>
    `).join('');
}

function showTerrainForm(terrain = null) {
    const title = terrain ? 'Modifier le terrain' : 'Ajouter un terrain';
    const submitBtn = terrain ? 'Modifier' : 'Ajouter';
    
    const content = `
        <h2>${title}</h2>
        <form onsubmit="saveTerrain(event, ${terrain ? terrain.id : 'null'})">
            <div class="form-group">
                <label>Nom du terrain *</label>
                <input type="text" name="nom" value="${terrain ? terrain.nom : ''}" required>
            </div>
            <div class="form-group">
                <label>Type *</label>
                <select name="type" required>
                    <option value="">Sélectionner...</option>
                    <option value="Football" ${terrain && terrain.type === 'Football' ? 'selected' : ''}>Football</option>
                    <option value="Basketball" ${terrain && terrain.type === 'Basketball' ? 'selected' : ''}>Basketball</option>
                    <option value="Tennis" ${terrain && terrain.type === 'Tennis' ? 'selected' : ''}>Tennis</option>
                    <option value="Volleyball" ${terrain && terrain.type === 'Volleyball' ? 'selected' : ''}>Volleyball</option>
                </select>
            </div>
            <div class="form-group">
                <label>Tarif par heure (MAD) *</label>
                <input type="number" step="0.01" name="tarifHeure" value="${terrain ? terrain.tarifHeure : ''}" required>
            </div>
            <div class="form-group">
                <label>Statut *</label>
                <select name="statut" required>
                    <option value="Disponible" ${terrain && terrain.statut === 'Disponible' ? 'selected' : ''}>Disponible</option>
                    <option value="Occupé" ${terrain && terrain.statut === 'Occupé' ? 'selected' : ''}>Occupé</option>
                    <option value="Maintenance" ${terrain && terrain.statut === 'Maintenance' ? 'selected' : ''}>Maintenance</option>
                </select>
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea name="description" rows="3">${terrain ? terrain.description || '' : ''}</textarea>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-secondary" onclick="closeModal()">Annuler</button>
                <button type="submit" class="btn btn-primary">${submitBtn}</button>
            </div>
        </form>
    `;
    showModal(content);
}

function saveTerrain(event, id) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const terrain = {
        nom: formData.get('nom'),
        type: formData.get('type'),
        tarifHeure: parseFloat(formData.get('tarifHeure')),
        statut: formData.get('statut'),
        description: formData.get('description')
    };
    
    const url = id ? `${API_URL}/terrains/${id}` : `${API_URL}/terrains`;
    const method = id ? 'PUT' : 'POST';
    
    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(terrain)
    })
    .then(response => response.text())
    .then(() => {
        closeModal();
        loadTerrains();
        showAlert('success', 'Terrain enregistré avec succès!');
    })
    .catch(error => {
        showAlert('error', 'Erreur lors de l\'enregistrement');
        console.error('Erreur:', error);
    });
}

function editTerrain(id) {
    const terrain = terrains.find(t => t.id === id);
    showTerrainForm(terrain);
}

function deleteTerrain(id) {
    if (confirm('Voulez-vous vraiment supprimer ce terrain?')) {
        fetch(`${API_URL}/terrains/${id}`, { method: 'DELETE' })
            .then(() => {
                loadTerrains();
                showAlert('success', 'Terrain supprimé avec succès!');
            })
            .catch(error => {
                showAlert('error', 'Erreur lors de la suppression');
                console.error('Erreur:', error);
            });
    }
}

// ============= CLIENTS =============

function loadClients() {
    fetch(`${API_URL}/clients`)
        .then(response => response.json())
        .then(data => {
            clients = data;
            renderClients();
        })
        .catch(error => console.error('Erreur:', error));
}

function renderClients() {
    const tbody = document.querySelector('#clients-table tbody');
    tbody.innerHTML = clients.map(client => `
        <tr>
            <td>${client.id}</td>
            <td>${client.nom}</td>
            <td>${client.prenom}</td>
            <td>${client.email}</td>
            <td>${client.telephone}</td>
            <td>
                <button class="btn btn-warning" onclick="editClient(${client.id})">Modifier</button>
                <button class="btn btn-danger" onclick="deleteClient(${client.id})">Supprimer</button>
            </td>
        </tr>
    `).join('');
}

function showClientForm(client = null) {
    const title = client ? 'Modifier le client' : 'Ajouter un client';
    const submitBtn = client ? 'Modifier' : 'Ajouter';
    
    const content = `
        <h2>${title}</h2>
        <form onsubmit="saveClient(event, ${client ? client.id : 'null'})">
            <div class="form-group">
                <label>Nom *</label>
                <input type="text" name="nom" value="${client ? client.nom : ''}" required>
            </div>
            <div class="form-group">
                <label>Prénom *</label>
                <input type="text" name="prenom" value="${client ? client.prenom : ''}" required>
            </div>
            <div class="form-group">
                <label>Email *</label>
                <input type="email" name="email" value="${client ? client.email : ''}" required>
            </div>
            <div class="form-group">
                <label>Téléphone *</label>
                <input type="tel" name="telephone" value="${client ? client.telephone : ''}" required>
            </div>
            <div class="form-group">
                <label>Adresse</label>
                <textarea name="adresse" rows="2">${client ? client.adresse || '' : ''}</textarea>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-secondary" onclick="closeModal()">Annuler</button>
                <button type="submit" class="btn btn-primary">${submitBtn}</button>
            </div>
        </form>
    `;
    showModal(content);
}

function saveClient(event, id) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const client = {
        nom: formData.get('nom'),
        prenom: formData.get('prenom'),
        email: formData.get('email'),
        telephone: formData.get('telephone'),
        adresse: formData.get('adresse')
    };
    
    const url = id ? `${API_URL}/clients/${id}` : `${API_URL}/clients`;
    const method = id ? 'PUT' : 'POST';
    
    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(client)
    })
    .then(response => response.text())
    .then(() => {
        closeModal();
        loadClients();
        showAlert('success', 'Client enregistré avec succès!');
    })
    .catch(error => {
        showAlert('error', 'Erreur lors de l\'enregistrement');
        console.error('Erreur:', error);
    });
}

function editClient(id) {
    const client = clients.find(c => c.id === id);
    showClientForm(client);
}

function deleteClient(id) {
    if (confirm('Voulez-vous vraiment supprimer ce client?')) {
        fetch(`${API_URL}/clients/${id}`, { method: 'DELETE' })
            .then(() => {
                loadClients();
                showAlert('success', 'Client supprimé avec succès!');
            })
            .catch(error => {
                showAlert('error', 'Erreur lors de la suppression');
                console.error('Erreur:', error);
            });
    }
}

// ============= RESERVATIONS =============

function loadReservations() {
    fetch(`${API_URL}/reservations`)
        .then(response => response.json())
        .then(data => {
            reservations = data;
            renderReservations();
        })
        .catch(error => console.error('Erreur:', error));
}

function renderReservations() {
    const tbody = document.querySelector('#reservations-table tbody');
    tbody.innerHTML = reservations.map(reservation => `
        <tr>
            <td>${reservation.id}</td>
            <td>${reservation.terrain.nom}</td>
            <td>${reservation.client.nom} ${reservation.client.prenom}</td>
            <td>${formatDateTime(reservation.dateDebut)}</td>
            <td>${formatDateTime(reservation.dateFin)}</td>
            <td>${reservation.montantTotal} MAD</td>
            <td><span class="badge badge-${getStatutClass(reservation.statut)}">${reservation.statut}</span></td>
            <td>
                <button class="btn btn-warning" onclick="editReservation(${reservation.id})">Modifier</button>
                <button class="btn btn-danger" onclick="deleteReservation(${reservation.id})">Supprimer</button>
            </td>
        </tr>
    `).join('');
}

function showReservationForm(reservation = null) {
    const title = reservation ? 'Modifier la réservation' : 'Nouvelle réservation';
    const submitBtn = reservation ? 'Modifier' : 'Créer';
    
    const terrainsOptions = terrains.map(t => 
        `<option value="${t.id}" ${reservation && reservation.terrain.id === t.id ? 'selected' : ''}>${t.nom} (${t.type})</option>`
    ).join('');
    
    const clientsOptions = clients.map(c => 
        `<option value="${c.id}" ${reservation && reservation.client.id === c.id ? 'selected' : ''}>${c.nom} ${c.prenom}</option>`
    ).join('');
    
    const content = `
        <h2>${title}</h2>
        <form onsubmit="saveReservation(event, ${reservation ? reservation.id : 'null'})">
            <div class="form-group">
                <label>Terrain *</label>
                <select name="terrainId" required>
                    <option value="">Sélectionner un terrain...</option>
                    ${terrainsOptions}
                </select>
            </div>
            <div class="form-group">
                <label>Client *</label>
                <select name="clientId" required>
                    <option value="">Sélectionner un client...</option>
                    ${clientsOptions}
                </select>
            </div>
            <div class="form-group">
                <label>Date et heure de début *</label>
                <input type="datetime-local" name="dateDebut" 
                       value="${reservation ? formatDateTimeInput(reservation.dateDebut) : ''}" required>
            </div>
            <div class="form-group">
                <label>Date et heure de fin *</label>
                <input type="datetime-local" name="dateFin" 
                       value="${reservation ? formatDateTimeInput(reservation.dateFin) : ''}" required>
            </div>
            <div class="form-group">
                <label>Montant total (MAD) *</label>
                <input type="number" step="0.01" name="montantTotal" 
                       value="${reservation ? reservation.montantTotal : ''}" required>
            </div>
            <div class="form-group">
                <label>Statut *</label>
                <select name="statut" required>
                    <option value="Confirmée" ${reservation && reservation.statut === 'Confirmée' ? 'selected' : ''}>Confirmée</option>
                    <option value="En attente" ${reservation && reservation.statut === 'En attente' ? 'selected' : ''}>En attente</option>
                    <option value="Annulée" ${reservation && reservation.statut === 'Annulée' ? 'selected' : ''}>Annulée</option>
                </select>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-secondary" onclick="closeModal()">Annuler</button>
                <button type="submit" class="btn btn-primary">${submitBtn}</button>
            </div>
        </form>
    `;
    showModal(content);
}

function saveReservation(event, id) {
    event.preventDefault();
    const formData = new FormData(event.target);
    
    const reservation = {
        clientId: parseInt(formData.get('clientId')),
        terrainId: parseInt(formData.get('terrainId')),
        dateDebut: formData.get('dateDebut'),
        dateFin: formData.get('dateFin'),
        montantTotal: parseFloat(formData.get('montantTotal')),
        statut: formData.get('statut')
    };
    
    const url = id ? `${API_URL}/reservations/${id}` : `${API_URL}/reservations`;
    const method = id ? 'PUT' : 'POST';
    
    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(reservation)
    })
    .then(response => response.text())
    .then(() => {
        closeModal();
        loadReservations();
        showAlert('success', 'Réservation enregistrée avec succès!');
    })
    .catch(error => {
        showAlert('error', 'Erreur lors de l\'enregistrement');
        console.error('Erreur:', error);
    });
}

function editReservation(id) {
    const reservation = reservations.find(r => r.id === id);
    showReservationForm(reservation);
}

function deleteReservation(id) {
    if (confirm('Voulez-vous vraiment supprimer cette réservation?')) {
        fetch(`${API_URL}/reservations/${id}`, { method: 'DELETE' })
            .then(() => {
                loadReservations();
                showAlert('success', 'Réservation supprimée avec succès!');
            })
            .catch(error => {
                showAlert('error', 'Erreur lors de la suppression');
                console.error('Erreur:', error);
            });
    }
}

// ============= PAIEMENTS =============

function loadPaiements() {
    fetch(`${API_URL}/paiements`)
        .then(response => response.json())
        .then(data => {
            paiements = data;
            renderPaiements();
        })
        .catch(error => console.error('Erreur:', error));
}

function renderPaiements() {
    const tbody = document.querySelector('#paiements-table tbody');
    tbody.innerHTML = paiements.map(paiement => `
        <tr>
            <td>${paiement.id}</td>
            <td>Rés. #${paiement.reservation.id}</td>
            <td>${paiement.montant} MAD</td>
            <td>${paiement.modePaiement}</td>
            <td>${formatDateTime(paiement.datePaiement)}</td>
            <td><span class="badge badge-${getStatutClass(paiement.statut)}">${paiement.statut}</span></td>
            <td>
                <button class="btn btn-warning" onclick="editPaiement(${paiement.id})">Modifier</button>
                <button class="btn btn-danger" onclick="deletePaiement(${paiement.id})">Supprimer</button>
            </td>
        </tr>
    `).join('');
}

function showPaiementForm(paiement = null) {
    const title = paiement ? 'Modifier le paiement' : 'Ajouter un paiement';
    const submitBtn = paiement ? 'Modifier' : 'Ajouter';
    
    const reservationsOptions = reservations.map(r => 
        `<option value="${r.id}" ${paiement && paiement.reservation.id === r.id ? 'selected' : ''}>
            Rés. #${r.id} - ${r.terrain.nom} (${r.client.nom})
        </option>`
    ).join('');
    
    const content = `
        <h2>${title}</h2>
        <form onsubmit="savePaiement(event, ${paiement ? paiement.id : 'null'})">
            <div class="form-group">
                <label>Réservation *</label>
                <select name="reservationId" required>
                    <option value="">Sélectionner une réservation...</option>
                    ${reservationsOptions}
                </select>
            </div>
            <div class="form-group">
                <label>Montant (MAD) *</label>
                <input type="number" step="0.01" name="montant" 
                       value="${paiement ? paiement.montant : ''}" required>
            </div>
            <div class="form-group">
                <label>Mode de paiement *</label>
                <select name="modePaiement" required>
                    <option value="Espèces" ${paiement && paiement.modePaiement === 'Espèces' ? 'selected' : ''}>Espèces</option>
                    <option value="Carte bancaire" ${paiement && paiement.modePaiement === 'Carte bancaire' ? 'selected' : ''}>Carte bancaire</option>
                    <option value="Virement" ${paiement && paiement.modePaiement === 'Virement' ? 'selected' : ''}>Virement</option>
                </select>
            </div>
            <div class="form-group">
                <label>Statut *</label>
                <select name="statut" required>
                    <option value="Payé" ${paiement && paiement.statut === 'Payé' ? 'selected' : ''}>Payé</option>
                    <option value="En attente" ${paiement && paiement.statut === 'En attente' ? 'selected' : ''}>En attente</option>
                    <option value="Remboursé" ${paiement && paiement.statut === 'Remboursé' ? 'selected' : ''}>Remboursé</option>
                </select>
            </div>
            <div class="form-group">
                <label>Référence</label>
                <input type="text" name="reference" value="${paiement && paiement.reference ? paiement.reference : ''}">
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-secondary" onclick="closeModal()">Annuler</button>
                <button type="submit" class="btn btn-primary">${submitBtn}</button>
            </div>
        </form>
    `;
    showModal(content);
}

function savePaiement(event, id) {
    event.preventDefault();
    const formData = new FormData(event.target);
    
    const paiement = {
        reservationId: parseInt(formData.get('reservationId')),
        montant: parseFloat(formData.get('montant')),
        modePaiement: formData.get('modePaiement'),
        statut: formData.get('statut'),
        reference: formData.get('reference')
    };
    
    const url = id ? `${API_URL}/paiements/${id}` : `${API_URL}/paiements`;
    const method = id ? 'PUT' : 'POST';
    
    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(paiement)
    })
    .then(response => response.text())
    .then(() => {
        closeModal();
        loadPaiements();
        showAlert('success', 'Paiement enregistré avec succès!');
    })
    .catch(error => {
        showAlert('error', 'Erreur lors de l\'enregistrement');
        console.error('Erreur:', error);
    });
}

function editPaiement(id) {
    const paiement = paiements.find(p => p.id === id);
    showPaiementForm(paiement);
}

function deletePaiement(id) {
    if (confirm('Voulez-vous vraiment supprimer ce paiement?')) {
        fetch(`${API_URL}/paiements/${id}`, { method: 'DELETE' })
            .then(() => {
                loadPaiements();
                showAlert('success', 'Paiement supprimé avec succès!');
            })
            .catch(error => {
                showAlert('error', 'Erreur lors de la suppression');
                console.error('Erreur:', error);
            });
    }
}

// ============= UTILITIES =============

function getStatutClass(statut) {
    const map = {
        'Disponible': 'success',
        'Confirmée': 'success',
        'Payé': 'success',
        'Occupé': 'warning',
        'En attente': 'warning',
        'Maintenance': 'danger',
        'Annulée': 'danger',
        'Remboursé': 'info'
    };
    return map[statut] || 'info';
}

function formatDateTime(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleString('fr-FR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    });
}

function formatDateTimeInput(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toISOString().slice(0, 16);
}

function showAlert(type, message) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;
    
    const container = document.querySelector('.container');
    container.insertBefore(alertDiv, container.firstChild);
    
    setTimeout(() => alertDiv.remove(), 3000);
}
