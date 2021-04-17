package fr.jtomasi.ingredients;

public class Ingredient {
    protected int id;
    protected String nom;
    protected boolean bio;
    private double calories;

    public Ingredient(int id,String nom,double calories, boolean bio){
        this.id = id;
        this.nom = nom;
        this.bio = bio;
        this.calories = calories;
    }

    /**
     * Permet de savoir si l'ingrédient est bio ou pas
     * @return boolean
     */
    public boolean isBio() {
        return bio;
    }

    /**
     * Renvoie le nombre de calories
     * @return double
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Renvoie le nom de l'ingrédient
     * @return String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie l'identifiant de l'ingrédient
     * @return int
     */
    public int getId(){
        return this.id;
    }
}
