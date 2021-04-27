package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private transient Ingredient ingredient;
    private String idIngredient;
    private int quantite;
    private String preparation;

    public Recette(){
        super();
    }

    public Recette(Ingredient i, int quantite, String preparation){
        this.ingredient = i;
        this.quantite = quantite;
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


    /**
     * Retourne l'id de l'ingrédient
     * @return int
     */
    public String getIdIngredient(){
        return idIngredient;
    }
}
