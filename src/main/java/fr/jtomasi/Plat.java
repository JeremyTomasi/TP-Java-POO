package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plat {

    private String nomPlat;
    private List<Ingredient> listeIngredients = new ArrayList<>();
    private int notePlat = -1;
    private boolean bio = false;
    private double caloriesPlat;

    public Plat(String nomPlat){
        this.nomPlat = nomPlat;
    }

    public void addIngredient(Ingredient ingredient){
        this.listeIngredients.add(ingredient);
    }

    public void noterPlat(int notePlat){
        this.notePlat = notePlat;
    }

    public String getNomPlat(){
        return this.nomPlat;
    }

    public void setNomPlat(String nomPlat){
        this.nomPlat = nomPlat;
    }

    public int getNotePlat(){
        return this.notePlat;
    }

    public List<Ingredient> getListeIngredients(){
        return this.listeIngredients;
    }

    public void displayIngredients(){
        Logger logger = Logger.getLogger(this.getClass().getName());
        for(Ingredient i : this.getListeIngredients()){
            logger.log(Level.INFO,"Id de l'ingredient : " + i.getId());
            logger.log(Level.INFO,"Nom de l'ingredient : " + i.getNom());
            logger.log(Level.INFO,"Consigne de l'ingredient : " + i.getConsigne());
            logger.log(Level.INFO,"Quantite de l'ingredient : " + i.getQuantite());
            logger.log(Level.INFO,"Calories de l'ingredient : " + i.getCalories());
            logger.log(Level.INFO,"Est-il bio ? : " + i.isBio());
        }
    }

    public void setCaloriesPlat(){
        for(Ingredient i : this.listeIngredients){
            caloriesPlat += i.getCalories();
        }
    }

    public double getCaloriesPlat() {
        return caloriesPlat;
    }

    public boolean isBio() {
        int nbIngredientsBio = 0;
        for(Ingredient i : this.getListeIngredients()){
            if(i.isBio()){
                nbIngredientsBio++;
            }
        }
        if(nbIngredientsBio == this.getListeIngredients().size()){
            this.bio = true;
        }
        return bio;
    }
}
