package DAO;

import models.Projet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDao {
    private  Connection connection;

    public ProjetDao(){

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

    public  void createprojet(Projet projet) {
        System.out.println(projet.getNom());
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "INSERT INTO projet (nom,description, startdate,enddate,budjet) VALUES ( ?, ?, ?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, projet.getNom());
            stmt.setString(2, projet.getDescription());
            stmt.setString(3, projet.getStartdate());
            stmt.setString(4, projet.getEnddate());
            stmt.setInt(5, projet.getBudjet());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting projet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public  List<Projet> getAllprojets() {
        List<Projet> projetList = new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return projetList;
        }

        String query = "SELECT * FROM projet";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("hello");
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
        } catch (SQLException e) {
            System.err.println("Error fetching persons: " + e.getMessage());
            e.printStackTrace();
        }
        return projetList;
    }
    public  void updateprojet(Projet projet) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "UPDATE projet SET nom = ?,description = ?,startdate = ?,enddate = ?,budjet = ?  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.println("update");
            stmt.setString(1, projet.getNom());
            stmt.setString(2, projet.getDescription());
            stmt.setString(3, projet.getStartdate());
            stmt.setString(4, projet.getEnddate());
            stmt.setInt(5, projet.getBudjet());
            stmt.setInt(6, projet.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating projet: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("hiiiiiii");

}
    public  void deleteprojet(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "DELETE FROM projet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting student : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public  Projet getprojetById(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return null;
        }

       Projet projet = null;
        String query = "SELECT * FROM projet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    projet  = new Projet(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("description"),
                            rs.getString("startdate"),
                            rs.getString("enddate"),
                            rs.getInt("budjet")


                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching person by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return projet;
    }

}



