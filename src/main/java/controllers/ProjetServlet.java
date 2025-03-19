package controllers;

import DAO.ProjetDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Projet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@WebServlet("/projet/*")

public class ProjetServlet extends HttpServlet {
    ProjetDao projetDao = new ProjetDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        System.out.println(action);

        switch (action) {
            case "/new":
                createprojet(req, resp);
                break;
            case "/new-form":
                addprojetForm(req, resp);
                break;
            case "/edit":
                updateprojet(req,resp);
                break;
            case "/edit-form":
                updateprojetForm(req,resp);
                break;
            case "/delete":
                deleteprojet(req,resp);
                break;
            case "/liste":
                listprojet(req, resp);
                break;

            default:
                resp.sendRedirect("/projet/liste");

        }


    }

    private void createprojet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String startdate = req.getParameter("startdate");
        String enddate = req.getParameter("enddate");
        int budjet = Integer.parseInt(req.getParameter("budjet"));
        Projet projet = new Projet(nom, description, startdate, enddate, budjet);
        HttpSession session = req.getSession();
        try {
            projetDao.createprojet(projet);
            session.setAttribute("message", "Student added successfully");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/projet/liste");
    }


    private void addprojetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Projet> projets = projetDao.getAllprojets();

        req.setAttribute("projets", projets);
        req.getRequestDispatcher("projet_form.jsp").forward(req, resp);


    }

    private void listprojet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Projet> projets = projetDao.getAllprojets();
        System.out.println("sizeoff");
        req.setAttribute("projets", projets);
        req.getRequestDispatcher("/projet_liste.jsp").forward(req, resp);
    }

    private void updateprojetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Projet projet = projetDao.getprojetById(Integer.parseInt(id));
        List<Projet> projets = projetDao.getAllprojets();
        req.setAttribute("projet", projet);
        req.getRequestDispatcher("projet_form.jsp").forward(req, resp);
    }

    private void updateprojet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String startdate = req.getParameter("startdate ");
        String enddate = req.getParameter("enddate ");
        int budjet = Integer.parseInt(req.getParameter("budjet "));

        HttpSession session = req.getSession();

        try {
            Projet projet = projetDao.getprojetById(id);
            projet.setNom(nom);
            projet.setDescription(description);
            projet.setStartdate(startdate);
            projet.setEnddate(enddate);
            projet.setBudjet(budjet);

            projetDao.updateprojet(projet);
            session.setAttribute("message", "projet edited successfully");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }

        resp.sendRedirect("/projet/liste");

    }


    private void deleteprojet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        try {
           projetDao.deleteprojet(Integer.parseInt(id));
            session.setAttribute("message", "Student deleted successfully");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/projet/list");
    }
}