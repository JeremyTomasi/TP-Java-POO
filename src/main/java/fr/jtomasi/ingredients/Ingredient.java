package fr.jtomasi.ingredients;

public class Ingredient {
    protected int id;
    protected String nom;
    protected boolean bio;
    protected int calories;
    protected String consigne = null;
    protected int quantite;

    public Ingredient(int id,String nom, boolean bio,int calories,int quantite, String consigne){
        this.id = id;
        this.nom = nom;
        this.bio = bio;
        this.calories = calories;
        this.quantite = quantite;
        this.consigne = consigne;
    }

    public Ingredient(int id,String nom, boolean bio,int calories,int quantite){
        this.id = id;
        this.nom = nom;
        this.bio = bio;
        this.calories = calories;
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

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
