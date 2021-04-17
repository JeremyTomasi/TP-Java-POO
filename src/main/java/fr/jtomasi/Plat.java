package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plat {

    private String nomPlat;
    private List<Recette> listeIngredients = new ArrayList<>();
    private int notePlat = -1;
    private boolean bio = false;
    private double caloriesPlat;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Plat(String nomPlat){
        this.nomPlat = nomPlat;
    }

    /**
     * Permet d'ajouter un ingrédient dans la liste
     * @param ingredient L'ingrédient à ajouter
     * @param quantite La quantité
     * @param preparation La préparation de l'ingrédient
     */
    public void addIngredient(Ingredient ingredient, int quantite, String preparation){
        listeIngredients.add(new Recette(ingredient,quantite,preparation));
    }

    public void addIngredient(Recette recette){
        listeIngredients.add(recette);
    }

    /**
     * Permet de noter le plat
     * @param notePlat La note du plat
     */
    public void noterPlat(int notePlat){
        this.notePlat = notePlat;
    }

    /**
     * Retourne le nom du plat
     * @return String
     */
    public String getNomPlat(){
        return this.nomPlat;
    }


    /**
     * Récupère la note du plat
     * @return int
     */
    public int getNotePlat(){
        return this.notePlat;
    }

    /**
     * Récupère la liste des ingrédients
     * @return List<Ingredient>
     */
    public List<Recette> getListeIngredients(){
        return listeIngredients;
    }

    /**
     * Affiche la liste des ingrédients
     */
    public void displayIngredients(){
        for(Recette recette : listeIngredients){
            logger.log(Level.INFO,"Nom de l'ingredient : " + recette.getIngredient().getNom());
            logger.log(Level.INFO,"Quantite : " + recette.getQuantite());
            logger.log(Level.INFO,"Preparation : " + recette.getPreparation());
        }
    }

    /**
     * Calcule le nombre de calories du plat
    **/
    public void setCaloriesPlat(){
        for(Recette recette : listeIngredients){
            caloriesPlat += recette.getQuantite() * recette.getIngredient().getCalories();
        }
    }

    /**
     * Renvoie le nombre de calories du plat
     * @return double
     */
    public double getCaloriesPlat() {
        return caloriesPlat;
    }

    /**
     * Permet de savoir si le plat est bio
     * @return boolean
     */
    public boolean isBio() {
        int nbIngredientsBio = 0;
        for(Recette recette : this.listeIngredients){
            if(recette.getIngredient().isBio()){
                nbIngredientsBio++;
            }
        }
        bio = nbIngredientsBio == listeIngredients.size();
        return bio;
    }
}
