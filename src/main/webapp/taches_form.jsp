<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Formulaire Tâche</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${tache != null}">
            <form action="/taches/edit" method="post">
                </c:if>
                <c:if test="${tache == null}">
                <form action="/taches/new" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${tache != null}">
                                Modifier la tâche
                            </c:if>
                            <c:if test="${tache == null}">
                                Ajouter une nouvelle tâche
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${tache != null}">
                        <input type="hidden" name="idtache" value="${tache.idtache}" />
                    </c:if>
                    <fieldset class="form-group">
                        <label>Nom</label>
                        <input type="text" value="${tache.nom}" class="form-control" name="nom" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Date de début</label>
                        <input type="date" value="${tache.startdate}" class="form-control" name="startdate" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Date de fin</label>
                        <input type="date" value="${tache.enddate}" class="form-control" name="enddate" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Projet Associé</label>
                        <select class="form-control" name="projetId" required>
                            <c:forEach var="projet" items="${projets}">
                                <option value="${projet.id}" ${tache != null && tache.projetId == projet.id ? 'selected' : ''}>
                                        ${projet.nom}
                                </option>
                            </c:forEach>
                        </select>
                    </fieldset>
                    <button type="submit" class="btn btn-success" style="background-color: #001c53">Enregistrer</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>