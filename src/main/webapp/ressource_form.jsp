<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <c:if test="${ressource != null}">
            <form action="/ressource/edit" method="post">
                </c:if>
                <c:if test="${ressource == null}">
                <form action="/ressource/new" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${ressource != null}">
                                Modifier le ressource
                            </c:if>
                            <c:if test="${ressource == null}">
                                Ajouter un nouveau ressource
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${ressource != null}">
                        <input type="hidden" name="id" value="${ressource.id}" />
                    </c:if>
                    <fieldset class="form-group">
                        <label>Nom du ressource</label>
                        <input type="text" value="${ressource.nom}" class="form-control" name="nom" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>type</label>
                        <textarea class="form-control" name="type" rows="3" required>${ressource.type}</textarea>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>quantité</label>
                        <input type="date" value="${ressource.quantite}" class="form-control" name="quantite" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>fournisseur</label>
                        <input type="date" value="${ressource.fournisseur}" class="form-control" name="fournisseur" required>
                    </fieldset>

                    <button type="submit" class="btn btn-success" style="background-color: #001c53">Enregistrer</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>