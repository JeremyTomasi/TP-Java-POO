package fr.jtomasi.ingredients;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Legume extends Ingredient implements Serializable {
    private double tauxFibre;

    public Legume(){
        super();
    }

    public Legume(String nom, boolean bio, double calories,double tauxFibre) {
        super(nom,calories, bio);
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
