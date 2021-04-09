package fr.jtomasi.ingredients;

public class Legume extends Ingredient{
    private double tauxFibre;


    public Legume(String nom, boolean bio, int calories, int quantite, String consigne) {
        super(nom, bio, calories, quantite, consigne);
    }

    public Legume(String nom, boolean bio, int calories, int quantite ,String consigne, double tauxFibre) {
        super(nom, bio, calories, quantite, consigne);
        this.tauxFibre = tauxFibre;
    }

    public Legume(String nom, boolean bio, int calories, int quantite,double tauxFibre) {
        super(nom, bio, calories, quantite);
        this.tauxFibre = tauxFibre;
    }

    public void setTauxFibre(double tauxFibre){
        this.tauxFibre = tauxFibre;
    }

    public double getTauxFibre(){
        return this.tauxFibre;
    }
}
