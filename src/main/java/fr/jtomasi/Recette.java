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

    public Ingredient getIngredient(){
        return ingredient;
    }

    public int getQuantite(){
        return quantite;
    }

    public String getPreparation(){
        return preparation;
    }
}
