import fr.jtomasi.Concours;
import fr.jtomasi.Plat;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Viande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConcoursTest {

    @Test
    public void testCreationConcours(){
        Concours topChef = new Concours("Top Chef","2021-04-15","2021-05-15");

        Assertions.assertEquals("Top Chef",topChef.getNomConcours());
    }

    @Test
    public void testAjoutPlat(){
        Concours topChef = new Concours("Top Chef","2021-04-15","2021-05-15");

        Plat rizCurry = new Plat("Riz au curry");
        rizCurry.addIngredient(new Viande(1,"Poulet",true,500,1,"Bien cuit"));
        rizCurry.addIngredient(new Ingredient(1,"Riz",false,200,1,"Al dente"));
        rizCurry.addIngredient(new Ingredient(2,"Sauce Curry",false,300,1));

        topChef.addPlatConcours(rizCurry);

        Assertions.assertEquals("Riz au curry",topChef.getListePlats().get(0).getNomPlat());
    }

}
