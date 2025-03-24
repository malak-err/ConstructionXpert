package controllers;

import DAO.TacheDao;
import DAO.ProjetDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Tache;
import models.Projet;

import java.io.IOException;
import java.util.List;

@WebServlet("/taches/*")
public class TacheServlet extends HttpServlet {
    TacheDao tacheDao = new TacheDao();
    ProjetDao projetDao = new ProjetDao();

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
            default:
                resp.sendRedirect("/taches/liste");
        }
    }

    private void createtache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String startdate = req.getParameter("startdate");
        String enddate = req.getParameter("enddate");
        int projetId = Integer.parseInt(req.getParameter("projetId"));

        Tache tache = new Tache(nom, startdate, enddate, projetId);
        HttpSession session = req.getSession();
        try {
            tacheDao.createtache(tache);
            session.setAttribute("message", "Tâche ajoutée avec succès");
            session.setAttribute("messageType", "success");
            System.out.println("[TacheServlet] Tâche créée : " + nom);
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            System.err.println("[TacheServlet] Erreur lors de la création : " + e.getMessage());
        }
        resp.sendRedirect("/taches/liste");
    }

    private void addtacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAlltaches();
        List<Projet> projets = projetDao.getAllprojets();
        req.setAttribute("taches", taches);
        req.setAttribute("projets", projets);
        req.getRequestDispatcher("/taches_form.jsp").forward(req, resp);
    }

    private void listtache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAlltaches();
        List<Projet> projets = projetDao.getAllprojets(); // Récupérer tous les projets
        req.setAttribute("taches", taches);
        req.setAttribute("projets", projets); // Passer les projets à la JSP
        System.out.println("[TacheServlet] Liste des tâches, taille : " + taches.size());
        req.getRequestDispatcher("/taches_liste.jsp").forward(req, resp);
    }

    private void updatetacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Tache tache = tacheDao.gettacheById(Integer.parseInt(id));
        List<Projet> projets = projetDao.getAllprojets();
        req.setAttribute("tache", tache);
        req.setAttribute("projets", projets);
        req.getRequestDispatcher("/taches_form.jsp").forward(req, resp);
    }

    private void updatetache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String startdate = req.getParameter("startdate");
        String enddate = req.getParameter("enddate");
        int projetId = Integer.parseInt(req.getParameter("projetId"));

        HttpSession session = req.getSession();
        try {
            Tache tache = tacheDao.gettacheById(id);
            tache.setNom(nom);
            tache.setStartdate(startdate);
            tache.setEnddate(enddate);
            tache.setProjetId(projetId);
            tacheDao.updatetache(tache);
            session.setAttribute("message", "Tâche modifiée avec succès");
            session.setAttribute("messageType", "success");
            System.out.println("[TacheServlet] Tâche ID " + id + " mise à jour");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            System.err.println("[TacheServlet] Erreur lors de la mise à jour : " + e.getMessage());
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
            System.out.println("[TacheServlet] Tâche ID " + id + " supprimée");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            System.err.println("[TacheServlet] Erreur lors de la suppression : " + e.getMessage());
        }
        resp.sendRedirect("/taches/liste");
    }
}