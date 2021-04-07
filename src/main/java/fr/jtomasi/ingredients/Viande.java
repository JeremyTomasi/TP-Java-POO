package fr.jtomasi.ingredients;

public class Viande extends Ingredient{

    private double tauxGraisse;


    public Viande(String nom, boolean bio, int calories, double poids, int quantite, String consigne) {
        super(nom, bio, calories, poids, quantite, consigne);
    }

    public Viande(String nom, boolean bio, int calories, double poids, int quantite, String consigne, double tauxGraisse) {
        super(nom, bio, calories, poids, quantite, consigne);
        this.tauxGraisse = tauxGraisse;
    }

    public void setTauxGraisse(double tauxGraisse){
        this.tauxGraisse = tauxGraisse;
    }
}
