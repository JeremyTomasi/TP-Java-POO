import fr.jtomasi.Plat;
import fr.jtomasi.Recette;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Viande;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.Padawan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatTest {
    private Padawan jeremy = new Padawan(1,"Tomasi","Jeremy", Genre.HOMME,"");
    @Test
    public void testListIngredients(){
        Plat plat = new Plat("Burger",jeremy);

        Viande steak = new Viande(1,"Steak hache",true,500,2.0);
        Legume cornichon = new Legume(1,"Cornichon",false,200,5.0);
        Ingredient sauce = new Ingredient(1,"Sauce tomate",500,true);
        Ingredient pain = new Ingredient(1,"Pain burger",500,true);

        plat.addIngredient(steak,1,"A point");
        plat.addIngredient(cornichon,1,"Découpe");
        plat.addIngredient(sauce,1,"Chaude");
        plat.addIngredient(pain,1,"Cuit au four");

        plat.displayIngredients();
    }

    @Test
    public void testPlatBio(){
        Plat plat = new Plat("Burger 2",jeremy);

        Viande steak = new Viande(1,"Steak hache",true,400,3.0);
        Legume cornichon = new Legume(1,"Cornichon",true,200,5.0);
        Ingredient sauce = new Ingredient(1,"Sauce tomate",200,true);
        Ingredient pain = new Ingredient(1,"Pain burger",200,true);

        plat.addIngredient(steak,1,"A point");
        plat.addIngredient(cornichon,1,"Découpe");
        plat.addIngredient(sauce,1,"Chaude");
        plat.addIngredient(pain,1,"Cuit au four");

        assertTrue(plat.isBio());
        System.out.println(plat.isBio());
    }

}