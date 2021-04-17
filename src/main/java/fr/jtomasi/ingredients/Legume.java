package fr.jtomasi.ingredients;

public class Legume extends Ingredient{
    private double tauxFibre;


    public Legume(int id,String nom, boolean bio, double calories,double tauxFibre) {
        super(id,nom,calories, bio);
        this.tauxFibre = tauxFibre;
    }

    /**
     * Permet de définir le taux de fibre du légume
     * @param tauxFibre Le taux de fibre du légume
     */
    public void setTauxFibre(double tauxFibre){
        this.tauxFibre = tauxFibre;
    }

    /**
     * Renvoie le taux de fibre du légume
     * @return double
     */
    public double getTauxFibre(){
        return this.tauxFibre;
    }
}
