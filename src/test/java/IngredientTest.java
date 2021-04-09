import fr.jtomasi.ingredients.Epice;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Poisson;
import fr.jtomasi.ingredients.Viande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testCreationEpice(){
        Epice paprika = new Epice("Paprika",false,200,5);

        assertEquals("Paprika",paprika.getNom());
        assertFalse(paprika.isBio());
        assertEquals(200,paprika.getCalories());
        assertEquals(5,paprika.getQuantite());
    }

    @Test
    public void testCreationLegume(){
        Legume salade = new Legume("Salade",true,200,2,"Cru");

        assertEquals("Salade",salade.getNom());
        assertTrue(salade.isBio());
        assertEquals(200,salade.getCalories());
        assertEquals(2,salade.getQuantite());
    }

    @Test
    public void testCreationPoisson(){
        Poisson poisson = new Poisson("Bar",true,500,1,"Saignant");

        assertEquals("Bar",poisson.getNom());
        assertTrue(poisson.isBio());
        assertEquals(500,poisson.getCalories());
        assertEquals(1,poisson.getQuantite());
        assertEquals("Saignant",poisson.getConsigne());
    }

    @Test
    public void testCreationViande(){
        Viande boeuf = new Viande("Boeuf",false,800,1,"Saignant");

        assertEquals("Boeuf",boeuf.getNom());
        assertFalse(boeuf.isBio());
        assertEquals(800,boeuf.getCalories());
        assertEquals(1,boeuf.getQuantite());
        assertEquals("Saignant",boeuf.getConsigne());
    }
}
