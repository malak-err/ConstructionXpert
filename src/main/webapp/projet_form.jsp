<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Projet</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${projet != null}">
            <form action="/projet/edit" method="post">
                </c:if>
                <c:if test="${projet == null}">
                <form action="/projet/new" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${projet != null}">
                                Modifier le projet
                            </c:if>
                            <c:if test="${projet == null}">
                                Ajouter un nouveau projet
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${projet != null}">
                        <input type="hidden" name="id" value="${projet.id}" />
                    </c:if>
                    <fieldset class="form-group">
                        <label>Nom du Projet</label>
                        <input type="text" value="${projet.nom}" class="form-control" name="nom" required placeholder="Ex: Projet à réaliser">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" name="description" rows="3" required placeholder="Description détaillée">${projet.description}</textarea>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Date de Début</label>
                        <input type="date" value="${projet.startdate}" class="form-control" name="startdate" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Date de Fin</label>
                        <input type="date" value="${projet.enddate}" class="form-control" name="enddate" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Budget (€)</label>
                        <input type="number" value="${projet.budjet}" class="form-control" name="budjet" min="0" required placeholder="Ex: 1000 €">
                    </fieldset>
                    <button type="submit" class="btn btn-success" style="background-color: #001c53">Enregistrer</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>