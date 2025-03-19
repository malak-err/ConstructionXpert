package controllers;

import DAO.TacheDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import models.Tache;

import java.io.IOException;
import java.util.List;
@WebServlet("/taches/*")

public class TacheServlet extends HttpServlet {

    TacheDao tacheDao = new TacheDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        System.out.println(action);

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
                deletetache  (req, resp);
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

       Tache tache = new Tache(nom,  startdate, enddate );
        HttpSession session = req.getSession();
        try {
            tacheDao.createtache(tache);
            session.setAttribute("message", "Student added successfully");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }finally {
            resp.sendRedirect("/taches/liste");
        }

    }

    private void addtacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAlltaches();
        req.setAttribute("taches", taches);
        req.getRequestDispatcher("taches_form.jsp").forward(req, resp);


    }
    private void listtache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAlltaches();
        System.out.println("sizeoff");
        req.setAttribute("taches", taches);
        req.getRequestDispatcher("/taches_liste.jsp").forward(req, resp);
    }


    private void updatetacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Tache tache =tacheDao.gettacheById(Integer.parseInt(id));
        List<Tache> taches = tacheDao.getAlltaches();
        req.setAttribute("tache", tache);
        req.getRequestDispatcher("taches_form.jsp").forward(req, resp);
    }


    private void updatetache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String startdate = req.getParameter("startdate ");
        String enddate = req.getParameter("enddate ");

        HttpSession session = req.getSession();



        try {
           Tache tache = tacheDao.gettacheById(id);
            tache.setNom(nom);
            tache.setStartdate(startdate);
            tache.setEnddate(enddate);


            tacheDao.updatetache(tache);
            session.setAttribute("message", "tache edited successfully");
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
            session.setAttribute("message", "Student deleted successfully");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/taches/list");
    }
}


