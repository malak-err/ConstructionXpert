package controllers;


import DAO.RessourceDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Ressource;

import java.io.IOException;
import java.util.List;

@WebServlet("/ressource/*")
public class RessourceServlet extends HttpServlet {
       RessourceDao ressourceDao = new RessourceDao();

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) action = "/liste";
        System.out.println("[RessourceServlet] Action : " + action);

        switch (action) {
            case "/ressource/new":
                createressource(req, resp);
                break;
            case "/new-form":
                addressourceForm(req, resp);
                break;
            case "/edit":
                updateressource (req, resp);
                break;
            case "/edit-form":
                updateressourceForm(req, resp);
                break;
            case "/delete":
                deleteressource (req, resp);
                break;
            case "/liste":
                listressource  (req, resp);
                break;
            default:
                resp.sendRedirect("/ressource/liste");
        }
    }
    private void createressource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String type = req.getParameter("type");
       int quantite = Integer.parseInt(req.getParameter("quantite"));
        String fournisseur = req.getParameter("fournisseur");
        Ressource ressource = new Ressource(nom, type, quantite, fournisseur);
        HttpSession session = req.getSession();
        try {
            ressourceDao.createressource(ressource);
            session.setAttribute("message", "ressource ajouté avec succès");
            session.setAttribute("messageType", "success");
            System.out.println("[RessourceServlet] Projet créé : " + nom);
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            System.err.println("[RessourceServlet] Erreur lors de la création du projet : " + e.getMessage());
        }
        resp.sendRedirect("/ressource/liste");
    }
    private void addressourceForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ressource> ressources = ressourceDao.getAllressources();
        req.setAttribute("ressources", ressources);
        System.out.println("[RessourceServlet] Chargement de new-form, projets récupérés : " + ressources.size());
        req.getRequestDispatcher("/ressource_form.jsp").forward(req, resp);
    }
    private void listressource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ressource> ressources = ressourceDao.getAllressources();
        req.setAttribute("ressources", ressources);
        System.out.println("[RessourceServlet] Liste des projets, taille : " + ressources.size());
        req.getRequestDispatcher("/ressource_liste.jsp").forward(req, resp);
    }
    private void updateressourceForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Ressource ressource = ressourceDao.getressourceById(Integer.parseInt(id));
        req.setAttribute("ressource", ressource);
        System.out.println("[RessourceServlet] Chargement de edit-form pour le projet ID : " + id);
        req.getRequestDispatcher("/ressource_form.jsp").forward(req, resp);
    }
    private void updateressource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String type = req.getParameter("type");
        int quantite = Integer.parseInt(req.getParameter("quantite"));
        String fournisseur = req.getParameter("fournisseur");

        HttpSession session = req.getSession();
        try {
            Ressource ressource = ressourceDao.getressourceById(id);
            ressource.setNom(nom);
            ressource.setNom(nom);
            ressource.setType(type);
            ressource.setQuantite(quantite);
            ressource.setFournisseur(fournisseur);
            ressourceDao.updateressource(ressource);
            session.setAttribute("message", "Projet modifié avec succès");
            session.setAttribute("messageType", "success");
            System.out.println("[RessourceServlet] Ressource ID " + id + " mis à jour");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            System.err.println("[RessourceServlet] Erreur lors de la mise à jour du ressource : " + e.getMessage());
        }
        resp.sendRedirect("/ressource/liste");
    }
    private void deleteressource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        try {
            ressourceDao.deleteressource(Integer.parseInt(id));
            session.setAttribute("message", "Projet supprimé avec succès");
            session.setAttribute("messageType", "success");
            System.out.println("[RessourceServlet] Projet ID " + id + " supprimé");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            System.err.println("[RessourceServlet] Erreur lors de la suppression du projet : " + e.getMessage());
        }
        resp.sendRedirect("/ressource/liste");
    }
}
