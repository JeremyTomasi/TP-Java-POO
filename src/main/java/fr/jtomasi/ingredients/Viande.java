package fr.jtomasi.ingredients;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Viande extends Ingredient implements Serializable {

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
