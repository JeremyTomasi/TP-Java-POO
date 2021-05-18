package fr.jtomasi.ingredients;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Poisson extends Ingredient implements Serializable {
    private double tauxGraisse;

    public Poisson() {
        super();
    }


    public Poisson(String nom,double calories, boolean bio) {
        super(nom, calories,bio);
    }

    public Poisson(String nom, boolean bio,double calories, double tauxGraisse) {
        super(nom, calories,bio);
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
