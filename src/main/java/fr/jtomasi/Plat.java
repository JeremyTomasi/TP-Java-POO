package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;

import java.util.List;

public class Plat {

    private String nomPlat;
    private List<Ingredient> listeIngredients;

    public Plat(String nomPlat){
        this.nomPlat = nomPlat;
    }

    public void addIngredient(Ingredient ingredient){
        this.listeIngredients.add(ingredient);
    }
}
