package DAO;

import models.Tache;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDao {

    private static Connection connection;

    public TacheDao (){

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
    public void createtache(Tache tache) {
        System.out.println("hello hhh");
        if (connection == null) {
            System.err.println("Database jjj connection is not initialized!");
            return;
        }

        String query = "INSERT INTO tache (nom, startdate,enddate) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tache.getNom());
            stmt.setString(2,tache.getStartdate());
            stmt.setString(3, tache.getEnddate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting projet: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public  List<Tache> getAlltaches() {
        List<Tache> tacheList= new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return tacheList;
        }

        String query = "SELECT * FROM tache";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("hello");
            while (rs.next()) {
                Tache tache = new Tache(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("startdate"),
                        rs.getString("enddate")

                );
                tacheList.add(tache);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching persons: " + e.getMessage());
            e.printStackTrace();
        }
        return tacheList;
    }

    public  void updatetache(Tache tache) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "UPDATE tache SET nom = ?,startdate = ?,enddate = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.println("update");
            stmt.setString(1, tache.getNom());
            stmt.setString(3, tache.getStartdate());
            stmt.setString(4, tache.getEnddate());
            stmt.setInt(6, tache.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating projet: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("hiiiiiii");

    }

    public  void deletetache(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return;
        }

        String query = "DELETE FROM tache WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting tache : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public Tache gettacheById(int id) {
        if (connection == null) {
            System.err.println("Database connection is not initialized!");
            return null;
        }

       Tache tache = null;
        String query = "SELECT * FROM tache WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                  tache  = new Tache(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("startdate"),
                            rs.getString("enddate")


                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching person by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return tache;
    }



}
