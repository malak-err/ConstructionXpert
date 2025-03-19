<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
<head>

  <meta charset="UTF-8">


  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard Bootstrap</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome pour les icônes -->
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

    .nav-links li {
      margin: 0 10px;
    }
    .nav-links a {
      color: white;
      text-decoration: none;
      padding: 8px 15px;
      border-radius: 4px;
      transition: background-color 0.3s;
    }
    .nav-links a:hover, .nav-links a.active {
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
      background-color: #27ae60;
      color: white;
      border: none;
      padding: 8px 15px;
      border-radius: 4px;
      cursor: pointer;
      font-weight: bold;
    }
    .add-button:hover {
      background-color: #229954;
    }

    .item-actions button {
      border: none;
      padding: 5px 10px;
      border-radius: 4px;
      cursor: pointer;
    }


    .form-group label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
      color: #333;
    }


    @media (max-width: 768px) {


      .nav-links li {
        margin: 5px 0;
      }
      .sidebar {
        height: 100vh;
        width: 250px;
        position: fixed;
        top: 0;
        left: 0;
        background-color: #2c3e50;
        padding-top: 20px;
        transition: 0.3s;
      }
      .sidebar a {
        padding: 15px 20px;
        text-decoration: none;
        font-size: 16px;
        color: white;
        display: block;
        transition: 0.3s;
      }
      .sidebar a:hover {
        background-color: #34495e;
      }


        .sidebar {
          width: 0;
        display : flex;
          gap: 50px

        }

        .sidebar.active {
          width: 250px;
          display : flex;
          gap: 50px
        }
        .add-button{
          background-color: #80b4dc;
        }
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
  <div class="sidebar" style="display: flex;gap: 59px ; justify-content: center ; color: #80b4dc ">
    <a href="/acceuil.jsp"><i class="fas fa-home me-2"></i> Accueil</a>
    <a href="#"> <i class="fa-solid fa-table-list"></i>  Projets</a>

    <a href="/taches_liste.jsp"><i class="fa-solid fa-table-list" ></i> taches</a>
    <a href="#"><i class="fa-solid fa-table-list"></i> ressources</a>
    <a href="#"><i class="fas fa-sign-out-alt me-2"></i> Déconnexion</a>
  </div>
</nav>


<div class="container">
  <!-- PAGE 1: LISTE DES PROJETS -->
  <div id="projects" class="page">
    <div class="page-header">
      <h1 class="page-title"> Listes des Projets</h1>
      <a href="<%=request.getContextPath()%>/projet_form.jsp" class="add-button" onclick="openModal('projet-modal'); " style="background-color: #80b4dc">
        + Ajouter un projet
      </a>    </div>

    <table class="table table-bordered">
      <thead>
      <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>date de debut</th>
        <th>date de fin</th>
        <th>budjet</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <!--   for (Todo todo: todos) {  -->
      <c:forEach var="projet" items="${projets}">

        <tr>
          <td><c:out value="${projet.id}" /></td>
          <td><c:out value="${projet.nom}" /></td>
          <td><c:out value="${projet.description}" /></td>
          <td><c:out value="${projet.startdate}" /></td>
          <td><c:out value="${projet.enddate}" /></td>
          <td><c:out value="${projet.budjet}" /></td>

          <td><a href="/projet/edit-form?id=<c:out value='${projet.id}' />" ><i class="fa-solid fa-pen-to-square"></i></a>
            &nbsp;&nbsp;&nbsp;&nbsp;
           <a   href="/projet/delete?id=<c:out value='${projet.id}' /> " data-toggle="modal" data-target="#modal"><i class="fa-solid fa-trash"></i></a> </td>
        </tr>

      </c:forEach>

      </tbody>

    </table>







  </div>

  </div>
</div>
</body>