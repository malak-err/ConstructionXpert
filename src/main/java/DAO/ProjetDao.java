package DAO;

import models.Projet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDao {
    private static Connection connection;

    public ProjetDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/constructionxpert", "root", "");
            if (connection == null) {
                throw new SQLException("Échec de la connexion à la base de données !");
            }
            System.out.println("[ProjetDao] Connexion à la base de données établie");
        } catch (ClassNotFoundException e) {
            System.err.println("[ProjetDao] Pilote JDBC MySQL non trouvé : " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Échec de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void createprojet(Projet projet) {
        System.out.println("[ProjetDao] Création du projet : " + projet.getNom());
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "INSERT INTO projet (nom, description, startdate, enddate, budjet) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projet.getNom());
            stmt.setString(2, projet.getDescription());
            stmt.setString(3, projet.getStartdate());
            stmt.setString(4, projet.getEnddate());
            stmt.setInt(5, projet.getBudjet());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("[ProjetDao] Projet '" + projet.getNom() + "' inséré, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de l’insertion du projet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Projet> getAllprojets() {
        List<Projet> projetList = new ArrayList<>();
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return projetList;
        }
        String query = "SELECT * FROM projet";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Projet projet = new Projet(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("startdate"),
                        rs.getString("enddate"),
                        rs.getInt("budjet")
                );
                projetList.add(projet);
            }
            System.out.println("[ProjetDao] " + projetList.size() + " projets récupérés");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la récupération des projets : " + e.getMessage());
            e.printStackTrace();
        }
        return projetList;
    }

    public void updateprojet(Projet projet) {
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "UPDATE projet SET nom = ?, description = ?, startdate = ?, enddate = ?, budjet = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, projet.getNom());
            stmt.setString(2, projet.getDescription());
            stmt.setString(3, projet.getStartdate());
            stmt.setString(4, projet.getEnddate());
            stmt.setInt(5, projet.getBudjet());
            stmt.setInt(6, projet.getId());
            stmt.executeUpdate();
            System.out.println("[ProjetDao] Projet ID " + projet.getId() + " mis à jour");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la mise à jour du projet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteprojet(int id) {
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "DELETE FROM projet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("[ProjetDao] Projet ID " + id + " supprimé");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la suppression du projet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Projet getprojetById(int id) {
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return null;
        }
        Projet projet = null;
        String query = "SELECT * FROM projet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    projet = new Projet(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("description"),
                            rs.getString("startdate"),
                            rs.getString("enddate"),
                            rs.getInt("budjet")
                    );
                }
            }
            System.out.println("[ProjetDao] Projet ID " + id + " récupéré");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la récupération du projet par ID : " + e.getMessage());
            e.printStackTrace();
        }
        return projet;
    }
}