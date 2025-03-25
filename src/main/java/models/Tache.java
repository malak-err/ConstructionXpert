package models;

public class Tache {
    private int idtache;
    private String nom;
    private String startdate;
    private String enddate;
    private int projetId;

    public Tache(String nom, String startdate, String enddate, int projetId) {
        this.nom = nom;
        this.startdate = startdate;
        this.enddate = enddate;


        this.projetId = projetId;
    }

    public Tache(int idtache, String nom, String startdate, String enddate, int projetId) {
        this.idtache = idtache;
        this.nom = nom;
        this.startdate = startdate;
        this.enddate = enddate;
        this.projetId = projetId;
    }

    public Tache(int idtache, String nom, String startdate, String enddate) {
        this.idtache = idtache;
        this.nom = nom;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Tache(String nom, String startdate, String enddate) {
        this.nom = nom;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getIdtache() {
        return idtache;
    }

    public void setIdtache(int idtache) {
        this.idtache = idtache;
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