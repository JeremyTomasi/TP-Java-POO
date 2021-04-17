package fr.jtomasi.ingredients;

public class Poisson extends Ingredient{
    private double tauxGraisse;


    public Poisson(int id,String nom, boolean bio, int calories, int quantite, String consigne) {
        super(id,nom, bio, calories, quantite, consigne);
    }

    public Poisson(int id,String nom, boolean bio, int calories, int quantite, String consigne, double tauxGraisse) {
        super(id,nom, bio, calories, quantite, consigne);
        this.tauxGraisse = tauxGraisse;
    }

    public void setTauxGraisse(double tauxGraisse){
        this.tauxGraisse = tauxGraisse;
    }

    public double getTauxGraisse(){
        return tauxGraisse;
    }
}
