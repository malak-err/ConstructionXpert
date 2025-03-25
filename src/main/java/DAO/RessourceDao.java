package DAO;

import models.Projet;
import models.Ressource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceDao {
    private static Connection connection;

    public RessourceDao() {
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

    public void createressource(Ressource ressource) {
        if (connection == null) {
            System.err.println("[RessourceDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "INSERT INTO ressource (nom, type, quantite, fournisseur) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ressource.getNom());
            stmt.setString(2, ressource.getType());
            stmt.setInt(3, ressource.getQuantite());
            stmt.setString(4, ressource.getFournisseur());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("[RessourceDao] Ressource '" + ressource.getNom() + "' insérée, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("[RessourceDao] Erreur lors de l’insertion de la ressource : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Ressource> getAllressources() {
        List<Ressource> ressourceList = new ArrayList<>();
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
        }
        String query = "SELECT * FROM ressource";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ressource ressource = new Ressource(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getInt("quantite"),
                        rs.getString("fournisseur")

                );
                ressourceList.add(ressource);
            }
            System.out.println("[ProjetDao] " + ressourceList.size() + " projets récupérés");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la récupération des projets : " + e.getMessage());
            e.printStackTrace();
        }
        return ressourceList;
    }

    public void updateressource(Ressource ressource) {
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "UPDATE ressource SET nom = ?, type = ?, quantite= ?, fournisseur = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ressource.getNom());
            stmt.setString(2, ressource.getType());
            stmt.setInt(3, ressource.getQuantite());
            stmt.setString(4, ressource.getFournisseur());

            stmt.executeUpdate();
            System.out.println("[ProjetDao] Projet ID " + ressource.getId() + " mis à jour");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la mise à jour du projet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteressource(int id) {
        if (connection == null) {
            System.err.println("[ProjetDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "DELETE FROM ressource WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("[ProjetDao] Projet ID " + id + " supprimé");
        } catch (SQLException e) {
            System.err.println("[ProjetDao] Erreur lors de la suppression du projet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Ressource getressourceById(int id) {
        if (connection == null) {
            return null;
        }
        Ressource ressource = null;
        String query = "SELECT * FROM ressource WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ressource = new Ressource(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("type"),
                            rs.getInt("quantite"),
                            rs.getString("fournisseur")
                    );
                }
            }
            System.out.println("[RessourceDao] Projet ID " + id + " récupéré");
        } catch (SQLException e) {
            System.err.println("[RessourceDao] Erreur lors de la récupération du projet par ID : " + e.getMessage());
            e.printStackTrace();
        }
        return ressource;
    }}

