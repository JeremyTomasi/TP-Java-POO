package fr.jtomasi.ingredients;

public class Legume extends Ingredient{
    private double tauxFibre;

    public Legume(String nom, boolean bio, int calories, double poids, int quantite, double tauxFibre) {
        super(nom, bio, calories, poids, quantite);
        this.tauxFibre = tauxFibre;
    }
}
