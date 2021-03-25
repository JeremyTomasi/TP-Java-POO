package fr.jtomasi.ingredients;

public class Ingredient {
    protected String nom;
    protected boolean bio;
    protected int calories;
    protected String consigne;
    protected double poids;
    protected int quantite;

    public Ingredient(String nom, boolean bio,int calories,double poids,int quantite){
        this.nom = nom;
        this.bio = bio;
        this.calories = calories;
        this.poids = poids;
        this.quantite = quantite;
    }

    public boolean isBio() {
        return bio;
    }

    public void setBio(boolean bio) {
        this.bio = bio;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getConsigne() {
        return consigne;
    }

    public void setConsigne(String consigne) {
        this.consigne = consigne;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
