import fr.jtomasi.Plat;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Viande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatTest {

    @Test
    public void testListIngredients(){
        Plat plat = new Plat("Burger");

        Viande steak = new Viande(1,"Steak hache",true,800,1,"A point");
        Legume cornichon = new Legume(1,"Cornichon",false,500,2,5.0);
        Ingredient sauce = new Ingredient(1,"Sauce tomate",true,200,1);
        Ingredient pain = new Ingredient(1,"Pain burger",true,300,1);

        plat.addIngredient(steak);
        plat.addIngredient(cornichon);
        plat.addIngredient(sauce);
        plat.addIngredient(pain);

        assertEquals("Steak hache",plat.getListeIngredients().get(0).getNom());
        assertEquals("Cornichon",plat.getListeIngredients().get(1).getNom());
        assertEquals("Sauce tomate",plat.getListeIngredients().get(2).getNom());
        assertEquals("Pain burger",plat.getListeIngredients().get(3).getNom());
    }

    @Test
    public void testPlatBio(){
        Plat plat = new Plat("Burger");

        Viande steak = new Viande(1,"Steak hache",true,800,1,"A point");
        Legume cornichon = new Legume(1,"Cornichon",true,500,2,5.0);
        Ingredient sauce = new Ingredient(1,"Sauce tomate",true,200,1);
        Ingredient pain = new Ingredient(1,"Pain burger",true,300,1);

        plat.addIngredient(steak);
        plat.addIngredient(cornichon);
        plat.addIngredient(sauce);
        plat.addIngredient(pain);

        assertTrue(plat.isBio());
        System.out.println(plat.isBio());
    }

}