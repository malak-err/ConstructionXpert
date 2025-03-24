<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liste des Projets</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f5f5f5;
    }
    .container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }
    header {
      background-color: #051f4e;
      color: white;
      padding: 15px 0;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }
    .app-name {
      font-size: 24px;
      font-weight: bold;
      margin-left: 20px;
    }
    nav {
      background-color: #001c53;
      padding: 10px 0;
    }
    .sidebar {
      display: flex;
      gap: 59px;
      justify-content: center;
      color: #80b4dc;
    }
    .sidebar a {
      color: white;
      text-decoration: none;
      padding: 8px 15px;
      border-radius: 4px;
      transition: background-color 0.3s;
    }
    .sidebar a:hover, .sidebar a.active {
      background-color: #80b4dc;
    }
    .page {
      background-color: white;
      border-radius: 8px;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      margin-top: 20px;
      padding: 20px;
    }
    .page-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
      border-bottom: 1px solid #eee;
      padding-bottom: 10px;
    }
    .page-title {
      font-size: 22px;
      font-weight: bold;
      color: #1a5276;
    }
    .add-button {
      background-color: #80b4dc;
      color: white;
      border: none;
      padding: 8px 15px;
      border-radius: 4px;
      cursor: pointer;
      font-weight: bold;
      text-decoration: none;
    }
    .add-button:hover {
      background-color: #5a9bd4;
    }
  </style>
</head>
<body>
<header>
  <div class="container">
    <div class="app-name">ConstructionXpert</div>
  </div>
</header>
<nav>
  <div class="sidebar">
    <a href="/acceuil.jsp"><i class="fas fa-home me-2"></i> Accueil</a>
    <a href="/projet/liste"><i class="fa-solid fa-table-list"></i> Projets</a>
    <a href="/taches_liste.jsp"><i class="fa-solid fa-table-list"></i> Tâches</a>
    <a href="#"><i class="fa-solid fa-table-list"></i> Ressources</a>
    <a href="#"><i class="fas fa-sign-out-alt me-2"></i> Déconnexion</a>
  </div>
</nav>
<div class="container">
  <div id="projects" class="page">
    <div class="page-header">
      <h1 class="page-title">Liste des Projets</h1>
      <a href="/projet/new-form" class="add-button">+ Ajouter un projet</a>
    </div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Date de début</th>
        <th>Date de fin</th>
        <th>Budget</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="projet" items="${projets}">
        <tr>
          <td>${projet.id}</td>
          <td>${projet.nom}</td>
          <td>${projet.description}</td>
          <td>${projet.startdate}</td>
          <td>${projet.enddate}</td>
          <td>${projet.budjet}</td>
          <td>
            <button type="button" class="btn btn-link p-0" data-bs-toggle="modal" data-bs-target="#editModal"
                    data-id="${projet.id}" data-nom="${projet.nom}" data-description="${projet.description}"
                    data-startdate="${projet.startdate}" data-enddate="${projet.enddate}" data-budjet="${projet.budjet}">
              <i class="fa-solid fa-pen-to-square"></i>
            </button>
                
            <a href="/projet/delete?id=${projet.id}"><i class="fa-solid fa-trash"></i></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!-- Modale pour modifier un projet -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editModalLabel">Modifier un Projet</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="/projet/edit" method="POST">
          <input type="hidden" id="edit-id" name="id"> <!-- Champ caché pour l'id -->
          <div class="mb-3">
            <label for="edit-nom" class="form-label">Nom du Projet</label>
            <input type="text" class="form-control" id="edit-nom" name="nom" required>
          </div>
          <div class="mb-3">
            <label for="edit-description" class="form-label">Description</label>
            <textarea class="form-control" id="edit-description" name="description" rows="3" required></textarea>
          </div>
          <div class="mb-3">
            <label for="edit-startdate" class="form-label">Date de Début</label>
            <input type="date" class="form-control" id="edit-startdate" name="startdate" required>
          </div>
          <div class="mb-3">
            <label for="edit-enddate" class="form-label">Date de Fin</label>
            <input type="date" class="form-control" id="edit-enddate" name="enddate" required>
          </div>
          <div class="mb-3">
            <label for="edit-budjet" class="form-label">Budget (€)</label>
            <input type="number" class="form-control" id="edit-budjet" name="budjet" min="0" required>
          </div>
          <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  // Remplir la modale avec les données du projet au clic sur "Modifier"
  document.addEventListener('DOMContentLoaded', function () {
    var editModal = document.getElementById('editModal');
    editModal.addEventListener('show.bs.modal', function (event) {
      var button = event.relatedTarget; // Bouton qui a déclenché la modale
      var id = button.getAttribute('data-id');
      var nom = button.getAttribute('data-nom');
      var description = button.getAttribute('data-description');
      var startdate = button.getAttribute('data-startdate');
      var enddate = button.getAttribute('data-enddate');
      var budjet = button.getAttribute('data-budjet');

      // Remplir les champs de la modale
      document.getElementById('edit-id').value = id; // Remplir le champ caché
      document.getElementById('edit-nom').value = nom;
      document.getElementById('edit-description').value = description;
      document.getElementById('edit-startdate').value = startdate;
      document.getElementById('edit-enddate').value = enddate;
      document.getElementById('edit-budjet').value = budjet;
    });
  });
</script>
</body>
</html>