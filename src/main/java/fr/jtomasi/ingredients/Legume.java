package fr.jtomasi.ingredients;

public class Legume extends Ingredient{
    private double tauxFibre;


    public Legume(String nom, boolean bio, int calories, double poids, int quantite, String consigne) {
        super(nom, bio, calories, poids, quantite, consigne);
    }

    public Legume(String nom, boolean bio, int calories, double poids, int quantite, String consigne, double tauxFibre) {
        super(nom, bio, calories, poids, quantite, consigne);
        this.tauxFibre = tauxFibre;
    }

    public void setTauxFibre(double tauxFibre){
        this.tauxFibre = tauxFibre;
    }
}
