package fr.jtomasi.ingredients;

public class Viande extends Ingredient{

    private double tauxGraisse;

    public Viande(String nom, boolean bio, int calories, double poids, int quantite, double tauxGraisse) {
        super(nom, bio, calories, poids, quantite);
        this.tauxGraisse = tauxGraisse;
    }
}
