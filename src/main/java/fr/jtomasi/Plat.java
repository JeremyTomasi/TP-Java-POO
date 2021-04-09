package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Plat {

    private String nomPlat;
    private List<Ingredient> listeIngredients = new ArrayList<>();
    private int notePlat;

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
}
