package controllers;

import DAO.TacheDao;
import DAO.RessourceDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Tache;
import models.Ressource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/taches/*")
public class TacheServlet extends HttpServlet {
    TacheDao tacheDao = new TacheDao();
    RessourceDao ressourceDao = new RessourceDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) action = "/liste";
        System.out.println("[TacheServlet] Action : " + action);

        switch (action) {
            case "/new":
                createtache(req, resp);
                break;
            case "/new-form":
                addtacheForm(req, resp);
                break;
            case "/edit":
                updatetache(req, resp);
                break;
            case "/edit-form":
                updatetacheForm(req, resp);
                break;
            case "/delete":
                deletetache(req, resp);
                break;
            case "/liste":
                listtache(req, resp);
                break;
            case "/associer-ressource-form":
                associerRessourceForm(req, resp);
                break;
            case "/associer-ressource":
                associerRessource(req, resp);
                break;
            default:
                resp.sendRedirect("/taches/liste");
        }
    }

    private void createtache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String startdate = req.getParameter("startdate");
        String enddate = req.getParameter("enddate");
        Tache tache = new Tache(nom, startdate, enddate);
        HttpSession session = req.getSession();
        try {
            tacheDao.createtache(tache);
            session.setAttribute("message", "Tâche ajoutée avec succès");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/taches/liste");
    }

    private void addtacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAlltaches();
        req.setAttribute("taches", taches);
        req.getRequestDispatcher("/taches_form.jsp").forward(req, resp);
    }

    private void listtache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAlltaches();
        Map<Integer, List<Ressource>> tacheRessources = new HashMap<>();
        for (Tache tache : taches) {
            List<Ressource> ressources = tacheDao.getRessourcesByTacheId(tache.getIdtache());
            tacheRessources.put(tache.getIdtache(), ressources);
        }
        req.setAttribute("taches", taches);
        req.setAttribute("tacheRessources", tacheRessources);
        req.getRequestDispatcher("/taches_liste.jsp").forward(req, resp);
    }

    private void updatetacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Tache tache = tacheDao.gettacheById(Integer.parseInt(id));
        req.setAttribute("tache", tache);
        req.getRequestDispatcher("/taches_form.jsp").forward(req, resp);
    }

    private void updatetache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String startdate = req.getParameter("startdate");
        String enddate = req.getParameter("enddate");
        HttpSession session = req.getSession();
        try {
            Tache tache = tacheDao.gettacheById(id);
            tache.setNom(nom);
            tache.setStartdate(startdate);
            tache.setEnddate(enddate);
            tacheDao.updatetache(tache);
            session.setAttribute("message", "Tâche modifiée avec succès");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/taches/liste");
    }

    private void deletetache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        try {
            tacheDao.deletetache(Integer.parseInt(id));
            session.setAttribute("message", "Tâche supprimée avec succès");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/taches/liste");
    }

    protected void associerRessourceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "L'ID de la tâche est requis pour associer des ressources.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            // Votre logique existante pour récupérer les détails de la tâche et les ressources
            // par exemple, request.setAttribute("tache", tacheService.findById(id));
            request.getRequestDispatcher("/associer-ressource-form.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Format de l'ID de la tâche invalide.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }



    private void associerRessource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idtache = Integer.parseInt(req.getParameter("idtache"));
        int idressource = Integer.parseInt(req.getParameter("idressource"));
        int quantite = Integer.parseInt(req.getParameter("quantite"));
        HttpSession session = req.getSession();
        try {
            tacheDao.associerRessource(idtache, idressource, quantite);
            session.setAttribute("message", "Ressource associée avec succès");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/taches/liste");
    }
}