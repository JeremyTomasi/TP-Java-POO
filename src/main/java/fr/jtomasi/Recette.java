package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;

public class Recette {
    private Ingredient ingredient;
    private int quantite;
    private String preparation;

    public Recette(Ingredient i, int quantite, String preparation){
        this.ingredient = i;
        this.quantite = quantite;
        this.preparation = preparation;
    }

    /**
     * Permet de récupérer l'ingrédient utilisé
     * @return Ingredient
     */
    public Ingredient getIngredient(){
        return ingredient;
    }

    /**
     * Récupère la quantité utilisée
     * @return int
     */
    public int getQuantite(){
        return quantite;
    }

    /**
     * Récupère la préparation de l'ingrédient
     * @return String
     */
    public String getPreparation(){
        return preparation;
    }
}
