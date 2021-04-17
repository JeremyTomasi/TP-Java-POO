package fr.jtomasi.ingredients;

public class Viande extends Ingredient{

    private double tauxGraisse = 0;

    public Viande(int id,String nom, boolean bio, double calories, double tauxGraisse) {
        super(id,nom, calories, bio);
        this.tauxGraisse = tauxGraisse;
    }

    public double getTauxGraisse(){
        return tauxGraisse;
    }
}
