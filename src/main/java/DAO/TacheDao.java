package DAO;

import models.Tache;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDao {
    private static Connection connection;

    public TacheDao() {
        initializeConnection();
    }

    private void initializeConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/constructionxpert", "root", "");
                System.out.println("[TacheDao] Connexion à la base de données établie");
            } catch (ClassNotFoundException e) {
                System.err.println("[TacheDao] Pilote JDBC MySQL non trouvé : " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("[TacheDao] Échec de la connexion : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void createtache(Tache tache) {
        System.out.println("[TacheDao] Création de la tâche : " + tache.getNom());
        initializeConnection();
        if (connection == null) {
            System.err.println("[TacheDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "INSERT INTO tache (nom, startdate, enddate, projet_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tache.getNom());
            stmt.setString(2, tache.getStartdate());
            stmt.setString(3, tache.getEnddate());
            stmt.setInt(4, tache.getProjetId());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("[TacheDao] Tâche '" + tache.getNom() + "' insérée, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("[TacheDao] Erreur lors de l’insertion de la tâche : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Tache> getAlltaches() {
        List<Tache> tacheList = new ArrayList<>();
        initializeConnection();
        if (connection == null) {
            System.err.println("[TacheDao] La connexion à la base de données n’est pas initialisée !");
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
                        rs.getString("enddate"),
                        rs.getInt("projet_id")
                );
                tacheList.add(tache);
            }
            System.out.println("[TacheDao] " + tacheList.size() + " tâches récupérées");
        } catch (SQLException e) {
            System.err.println("[TacheDao] Erreur lors de la récupération des tâches : " + e.getMessage());
            e.printStackTrace();
        }
        return tacheList;
    }

    public void updatetache(Tache tache) {
        initializeConnection();
        if (connection == null) {
            System.err.println("[TacheDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "UPDATE tache SET nom = ?, startdate = ?, enddate = ?, projet_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tache.getNom());
            stmt.setString(2, tache.getStartdate());
            stmt.setString(3, tache.getEnddate());
            stmt.setInt(4, tache.getProjetId());
            stmt.setInt(5, tache.getIdtache());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("[TacheDao] Tâche ID " + tache.getIdtache() + " mise à jour, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("[TacheDao] Erreur lors de la mise à jour de la tâche : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletetache(int idtache) {
        initializeConnection();
        if (connection == null) {
            System.err.println("[TacheDao] La connexion à la base de données n’est pas initialisée !");
            return;
        }
        String query = "DELETE FROM tache WHERE idtache = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idtache);
            stmt.executeUpdate();
            System.out.println("[TacheDao] Tâche ID " + idtache + " supprimée");
        } catch (SQLException e) {
            System.err.println("[TacheDao] Erreur lors de la suppression de la tâche : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Tache gettacheById(int idtache) {
        initializeConnection();
        if (connection == null) {
            System.err.println("[TacheDao] La connexion à la base de données n’est pas initialisée !");
            return null;
        }
        Tache tache = null;
        String query = "SELECT * FROM tache WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idtache);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tache = new Tache(
                            rs.getInt("idtache"),
                            rs.getString("nom"),
                            rs.getString("startdate"),
                            rs.getString("enddate"),
                            rs.getInt("projet_id")
                    );
                }
            }
            System.out.println("[TacheDao] Tâche ID " + idtache + " récupérée");
        } catch (SQLException e) {
            System.err.println("[TacheDao] Erreur lors de la récupération de la tâche par ID : " + e.getMessage());
            e.printStackTrace();
        }
        return tache;
    }
}