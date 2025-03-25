<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Tâches</title>
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
        <a href="/projet_liste.jsp"><i class="fa-solid fa-table-list"></i> Projets</a>
        <a href="/taches_liste.jsp"><i class="fa-solid fa-table-list"></i> Tâches</a>
        <a href="/ressource/liste"><i class="fa-solid fa-table-list"></i> Ressources</a>
        <a href="#"><i class="fas fa-sign-out-alt me-2"></i> Déconnexion</a>
    </div>
</nav>
<div class="container">
    <div id="taches" class="page">
        <div class="page-header">
            <h1 class="page-title">Liste des Tâches</h1>
            <a href="/taches/new-form" class="add-button">+ Ajouter une tâche</a>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Date de début</th>
                <th>Date de fin</th>
                <th>Ressources Associées</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tache" items="${taches}">
                <tr>
                    <td><c:out value="${tache.idtache}" /></td>
                    <td><c:out value="${tache.nom}" /></td>
                    <td><c:out value="${tache.startdate}" /></td>
                    <td><c:out value="${tache.enddate}" /></td>
                    <td>
                        <c:forEach var="ressource" items="${tacheRessources[tache.idtache]}">
                            <c:out value="${ressource.nom} (${ressource.quantiteAssociee})" /><br/>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="/taches/edit-form?id=<c:out value='${tache.idtache}' />"><i class="fa-solid fa-pen-to-square"></i></a>

                        <a href="/taches/delete?id=<c:out value='${tache.idtache}' />"><i class="fa-solid fa-trash"></i></a>
                        <c:if test="${not empty tache.idtache}">
                            <a href="/taches/associer-ressource-form?id=<c:out value='${tache.idtache}' />"><i class="fa-solid fa-box"></i></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>