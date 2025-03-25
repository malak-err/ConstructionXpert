package DAO;

import models.Tache;
import models.Ressource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDao {
    private static Connection connection;

    public TacheDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/constructionxpert", "root", "");
            if (this.connection == null) {
                throw new SQLException("Failed to establish database connection!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Méthodes existantes (inchangées)
    public void createtache(Tache tache) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }
        String query = "INSERT INTO tache (nom, startdate, enddate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tache.getNom());
            stmt.setString(2, tache.getStartdate());
            stmt.setString(3, tache.getEnddate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting tache: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Tache> getAlltaches() {
        List<Tache> tacheList = new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return tacheList;
        }
        String query = "SELECT * FROM tache";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Tache tache = new Tache(
                        rs.getInt("idtache"),
                        rs.getString("nom"),
                        rs.getString("startdate"),
                        rs.getString("enddate")
                );
                tacheList.add(tache);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching taches: " + e.getMessage());
            e.printStackTrace();
        }
        return tacheList;
    }

    public void updatetache(Tache tache) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }
        String query = "UPDATE tache SET nom = ?, startdate = ?, enddate = ? WHERE idtache = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tache.getNom());
            stmt.setString(2, tache.getStartdate());
            stmt.setString(3, tache.getEnddate());
            stmt.setInt(4, tache.getIdtache());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating tache: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletetache(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }
        String query = "DELETE FROM tache WHERE idtache = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting tache: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Tache gettacheById(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return null;
        }
        Tache tache = null;
        String query = "SELECT * FROM tache WHERE idtache = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tache = new Tache(
                            rs.getInt("idtache"),
                            rs.getString("nom"),
                            rs.getString("startdate"),
                            rs.getString("enddate")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tache by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return tache;
    }

    // Méthode pour associer une ressource avec une quantité personnalisée
    public void associerRessource(int idtache, int idressource, int quantite) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }
        String query = "INSERT INTO tache_ressource (idtache, idressource, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idtache);
            stmt.setInt(2, idressource);
            stmt.setInt(3, quantite);
            stmt.executeUpdate();
            System.out.println("[TacheDao] Ressource ID " + idressource + " associée à la tâche ID " + idtache + " avec quantité " + quantite);
        } catch (SQLException e) {
            System.err.println("Error associating ressource to tache: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer les ressources associées à une tâche
    public List<Ressource> getRessourcesByTacheId(int idtache) {
        List<Ressource> ressources = new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return ressources;
        }
        String query = "SELECT r.*, tr.quantite AS quantite_associee FROM ressource r " +
                "JOIN tache_ressource tr ON r.id = tr.idressource " +
                "WHERE tr.idtache = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idtache);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ressource ressource = new Ressource(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("type"),
                            rs.getInt("quantite"),
                            rs.getString("fournisseur")
                    );
                    ressource.setQuantiteAssociee(rs.getInt("quantite_associee"));
                    ressources.add(ressource);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching ressources for tache: " + e.getMessage());
            e.printStackTrace();
        }
        return ressources;
    }
}