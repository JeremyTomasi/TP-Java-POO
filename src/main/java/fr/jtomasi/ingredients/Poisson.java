package fr.jtomasi.ingredients;

import javax.persistence.Entity;

@Entity
public class Poisson extends Ingredient{
    private double tauxGraisse;

    public Poisson() {
        super();
    }


    public Poisson(int id,String nom,double calories, boolean bio) {
        super(id,nom, calories,bio);
    }

    public Poisson(int id,String nom, boolean bio,double calories, double tauxGraisse) {
        super(id,nom, calories,bio);
        this.tauxGraisse = tauxGraisse;
    }

    /**
     * Permet de définir le taux de graisse du poisson
     * @param tauxGraisse Le taux de graisse du poisson
     */
    public void setTauxGraisse(double tauxGraisse){
        this.tauxGraisse = tauxGraisse;
    }

    /**
     * Renvoie le taux de graisse du poisson
     * @return double
     */
    public double getTauxGraisse(){
        return tauxGraisse;
    }
}
