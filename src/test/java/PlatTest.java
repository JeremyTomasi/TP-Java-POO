import fr.jtomasi.personnes.Chef;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Viande;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.Padawan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlatTest {
    private Chef chef = new Chef("Etchebest","Philippe",Genre.HOMME,"1234567",4,"Gastronomie",50);
    private Padawan jeremy = new Padawan("Tomasi","Jeremy", Genre.HOMME,"","1234567");
    @Test
    public void testListIngredients(){
        Plat plat = new Plat("Burger",jeremy);

        Viande steak = new Viande("Steak hache",true,500,2.0);
        Legume cornichon = new Legume("Cornichon",false,200,5.0);
        Ingredient sauce = new Ingredient("Sauce tomate",500,true);
        Ingredient pain = new Ingredient("Pain burger",500,true);

        plat.addIngredient(steak,1,"A point");
        plat.addIngredient(cornichon,1,"Découpe");
        plat.addIngredient(sauce,1,"Chaude");
        plat.addIngredient(pain,1,"Cuit au four");

        plat.displayIngredients();
    }

    @Test
    public void testPlatBio(){
        Plat plat = new Plat("Burger 2",jeremy);

        Viande steak = new Viande("Steak hache",true,400,3.0);
        Legume cornichon = new Legume("Cornichon",true,200,5.0);
        Ingredient sauce = new Ingredient("Sauce tomate",200,true);
        Ingredient pain = new Ingredient("Pain burger",200,true);

        plat.addIngredient(steak,1,"A point");
        plat.addIngredient(cornichon,1,"Découpe");
        plat.addIngredient(sauce,1,"Chaude");
        plat.addIngredient(pain,1,"Cuit au four");

        assertTrue(plat.isBio());
        System.out.println(plat.isBio());
    }

}