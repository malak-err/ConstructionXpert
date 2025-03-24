package models;

public class Projet {
    private int id;
    private String nom;
    private String description;
    private String startdate;
    private String enddate;
    private int budjet;

    public Projet(int id, String nom, String description, String startdate, String enddate, int budjet) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
        this.budjet = budjet;
    }

    public Projet(String nom, String description, String startdate, String enddate, int budjet) {
        this.nom = nom;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
        this.budjet = budjet;
    }

    public Projet(int projetID) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getBudjet() {
        return budjet;
    }

    public void setBudjet(int budjet) {
        this.budjet = budjet;
    }
}
