<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Associer une Ressource</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container col-md-5">
  <div class="card mt-5">
    <div class="card-body">
      <form action="/taches/associer-ressource" method="post">
        <h2>Associer une ressource à la tâche : ${tache.nom}</h2>
        <input type="hidden" name="idtache" value="${tache.id}" />
        <fieldset class="form-group">
          <label>Ressource</label>
          <select class="form-control" name="idressource" required>
            <option value="">-- Sélectionner une ressource --</option>
            <c:forEach var="ressource" items="${ressources}">
              <option value="${ressource.id}">${ressource.nom} (${ressource.type})</option>
            </c:forEach>
          </select>
        </fieldset>
        <fieldset class="form-group">
          <label>Quantité souhaitée</label>
          <input type="number" class="form-control" name="quantite" min="1" required placeholder="Entrez la quantité">
        </fieldset>
        <button type="submit" class="btn btn-success" style="background-color: #001c53">Enregistrer</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>