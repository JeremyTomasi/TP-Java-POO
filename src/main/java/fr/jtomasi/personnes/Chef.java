package fr.jtomasi.personnes;

public class Chef extends Personne{

    private String telephone;
    private int nbEtoiles;
    private String specialite;
    private int nbPlatsRealises;

    public Chef(int id, String nom, String prenom, Genre genre, String telephone, int nbEtoiles, String specialite,int nbPlatsRealises) {
        super(id, nom, prenom, genre);
        this.telephone = telephone;
        this.nbEtoiles = nbEtoiles;
        this.specialite = specialite;
        this.nbPlatsRealises = nbPlatsRealises;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNbPlatsRealises() {
        return nbPlatsRealises;
    }

    public void setNbPlatsRealises(int nbPlatsRealises) {
        this.nbPlatsRealises = nbPlatsRealises;
    }
}
