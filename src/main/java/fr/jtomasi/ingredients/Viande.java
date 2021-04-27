package fr.jtomasi.ingredients;

import javax.persistence.Entity;

@Entity
public class Viande extends Ingredient{

    private double tauxGraisse = 0;

    public Viande(){
        super();
    }

    public Viande(String nom, boolean bio, double calories, double tauxGraisse) {
        super(nom, calories, bio);
        this.tauxGraisse = tauxGraisse;
    }

    /**
     * Récupère le taux de graisse de la viande
     * @return double
     */
    public double getTauxGraisse(){
        return tauxGraisse;
    }
}
