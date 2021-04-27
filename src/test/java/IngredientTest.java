import fr.jtomasi.ingredients.Epice;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Poisson;
import fr.jtomasi.ingredients.Viande;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testCreationEpice(){
        Epice paprika = new Epice(1,"Paprika",false,200);

        assertEquals("Paprika",paprika.getNom());
        assertFalse(paprika.isBio());
    }

    @Test
    public void testCreationLegume(){
        Legume salade = new Legume("Salade",true,400,10.0);

        assertEquals("Salade",salade.getNom());
        assertTrue(salade.isBio());
        assertEquals(10.0,salade.getTauxFibre());
    }

    @Test
    public void testCreationPoisson(){
        Poisson poisson = new Poisson("Bar",200,true);

        assertEquals("Bar",poisson.getNom());
        assertTrue(poisson.isBio());

        Poisson poisson2 = new Poisson("Bar",200,false);

        assertEquals("Bar",poisson2.getNom());
        assertFalse(poisson2.isBio());
    }

    @Test
    public void testCreationViande(){
        Viande boeuf = new Viande("Boeuf",false,500,2);

        assertEquals("Boeuf",boeuf.getNom());
        assertFalse(boeuf.isBio());

        Viande boeuf2 = new Viande("Boeuf",true,500,5.0);

        assertEquals("Boeuf",boeuf2.getNom());
        assertTrue(boeuf2.isBio());
    }
}
