package fr.jtomasi.plats;

import fr.jtomasi.ingredients.Ingredient;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Recette implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Transient
    private Ingredient ingredient;
    private String idIngredient;
    private String nomIngredient;
    private int quantite;
    private String preparation;

    public Recette(){
        super();
    }

    public Recette(Ingredient i, int quantite, String preparation){
        this.ingredient = i;
        this.quantite = quantite;
        this.nomIngredient = i.getNom();
        this.preparation = preparation;
        this.idIngredient = i.getId();
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

    public String getNomIngredient(){
        return nomIngredient;
    }


    /**
     * Retourne l'id de l'ingrédient
     * @return int
     */
    public String getIdIngredient(){
        return idIngredient;
    }
}
