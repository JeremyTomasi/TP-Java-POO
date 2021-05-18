package fr.jtomasi.ingredients;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ingredient implements Serializable {

    @Id
    protected String id;
    protected String nom;
    protected boolean bio;
    private double calories;

    public Ingredient(){
        super();
    }

    public Ingredient(String nom,double calories, boolean bio){
        this.nom = nom;
        this.bio = bio;
        this.calories = calories;
        this.id= UUID.randomUUID().toString();
    }

    /**
     * Permet de savoir si l'ingrédient est bio ou pas
     * @return boolean
     */
    public boolean isBio() {
        return bio;
    }

    /**
     * Renvoie le nombre de calories
     * @return double
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Renvoie le nom de l'ingrédient
     * @return String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie l'identifiant de l'ingrédient
     * @return int
     */
    public String getId(){
        return this.id;
    }
}
