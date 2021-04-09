import fr.jtomasi.Plat;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Viande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlatTest {

    @Test
    public void testListIngredients(){
        Plat plat = new Plat("Burger");

        Viande steak = new Viande("Steak hache",true,800,1,"A point");
        Legume cornichon = new Legume("Cornichon",false,500,2,5.0);
        Ingredient sauce = new Ingredient("Sauce tomate",true,200,1);
        Ingredient pain = new Ingredient("Pain burger",true,300,1);

        plat.addIngredient(steak);
        plat.addIngredient(cornichon);
        plat.addIngredient(sauce);
        plat.addIngredient(pain);

        Assertions.assertEquals("Steak hache",plat.getListeIngredients().get(0).getNom());
    }

}