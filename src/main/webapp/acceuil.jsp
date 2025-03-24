<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ConstructionXpert - Application de Gestion de Projets de Construction</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #00205b;
            color: white;
        }

        .header {
            display: flex;
            align-items: center;
            padding: 15px 20px;
            position: relative;
        }

        .logo {
            font-size: 28px;
            font-weight: bold;
            color: white;
            text-decoration: none;
        }

        .toggle-btn {
            background: none;
            border: none;
            color: white;
            font-size: 24px;
            cursor: pointer;
            margin-left: 15px;
            transition: transform 0.3s ease;
        }

        .toggle-btn:hover {
            color: #e0e0e0;
        }

        .sidebar {
            position: fixed;
            top: 0;
            left: -250px;
            width: 250px;
            height: 100%;
            background-color: #80b4dc;
            transition: left 0.3s ease;
            z-index: 1000;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.2);
            padding-top: 60px;
        }

        .sidebar.active {
            left: 0;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .sidebar ul li {
            padding: 0;
        }

        .sidebar ul li a {
            display: block;
            padding: 15px 20px;
            color: white;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .sidebar ul li a:hover {
            background-color: #33607e;
        }

        .content {
            text-align: center;
            padding: 20px;
        }

        .main-image {
            max-width: 400px;
            margin: 40px auto;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 999;
            display: none;
        }

        .overlay.active {
            display: block;
        }
    </style>
</head>
<body>
<div class="header">
    <a href="#" class="logo">ConstructionXpert</a>
    <button class="toggle-btn" id="toggleSidebar">&#8594;</button>
</div>

<div class="sidebar" id="sidebar">
    <ul>
        <li><a href="acceuil.jsp">Accueil</a></li>
        <li><a href="/projet/liste">Projets</a></li>
        <li><a href="/taches/listes">Tâches</a></li>
        <li><a href="ressources.html">Ressources</a></li>
        <li><a href="budget.html">contacter_nous</a></li>
        <li><a href="rapports.html">Rapports</a></li>
        <li><a href="parametres.html">Paramètres</a></li>
    </ul>
</div>

<div class="overlay" id="overlay"></div>

<div class="content">
    <h1>Application de Gestion de Projets de Construction</h1>
    <div class="main-image">
        <img src="/image/icon-removebg-preview-removebg-preview%20(1).png" alt="Bâtiments" style="max-width: 100%;">
    </div>
</div>

<script>
    const toggleBtn = document.getElementById('toggleSidebar');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');

    toggleBtn.addEventListener('click', function() {
        sidebar.classList.toggle('active');
        overlay.classList.toggle('active');

        // Change arrow direction
        if (sidebar.classList.contains('active')) {
            toggleBtn.innerHTML = '&#8592;'; // Left arrow
        } else {
            toggleBtn.innerHTML = '&#8594;'; // Right arrow
        }
    });

    // Close sidebar when clicking outside
    overlay.addEventListener('click', function() {
        sidebar.classList.remove('active');
        overlay.classList.remove('active');
        toggleBtn.innerHTML = '&#8594;'; // Right arrow
    });
</script>
</body>
</html>