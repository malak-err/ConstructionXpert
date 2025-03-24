package models;

public class Tache {
    private int id;
    private String nom;
    private String startdate;
    private String enddate;
    private int projetId; // Nouveau champ pour lier au projet

    public Tache(String nom, String startdate, String enddate, int projetId) {
        this.nom = nom;
        this.startdate = startdate;
        this.enddate = enddate;
        this.projetId = projetId;
    }

    public Tache(int id, String nom, String startdate, String enddate, int projetId) {
        this.id = id;
        this.nom = nom;
        this.startdate = startdate;
        this.enddate = enddate;
        this.projetId = projetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getProjetId() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }
}