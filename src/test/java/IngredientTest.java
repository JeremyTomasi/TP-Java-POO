import fr.jtomasi.ingredients.Epice;
import fr.jtomasi.ingredients.Legume;
import fr.jtomasi.ingredients.Poisson;
import fr.jtomasi.ingredients.Viande;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testCreationEpice(){
        Epice paprika = new Epice(1,"Paprika",false,200,5);

        assertEquals("Paprika",paprika.getNom());
        assertFalse(paprika.isBio());
        assertEquals(1000,paprika.getCalories());
        assertEquals(5,paprika.getQuantite());
    }

    @Test
    public void testCreationLegume(){
        Legume salade = new Legume(1,"Salade",true,200,2,"Cru");

        assertEquals("Salade",salade.getNom());
        assertTrue(salade.isBio());
        assertEquals(400,salade.getCalories());
        assertEquals(2,salade.getQuantite());

        Legume salade2 = new Legume(2,"Salade",true,200,2,"Cru",10.0);

        assertEquals("Salade",salade2.getNom());
        assertTrue(salade2.isBio());
        assertEquals(400,salade2.getCalories());
        assertEquals(2,salade2.getQuantite());
        assertEquals(10.0,salade2.getTauxFibre());
    }

    @Test
    public void testCreationPoisson(){
        Poisson poisson = new Poisson(1,"Bar",true,500,1,"Saignant");

        assertEquals("Bar",poisson.getNom());
        assertTrue(poisson.isBio());
        assertEquals(500,poisson.getCalories());
        assertEquals(1,poisson.getQuantite());
        assertEquals("Saignant",poisson.getConsigne());

        Poisson poisson2 = new Poisson(2,"Bar",true,500,1,"Saignant",5.0);

        assertEquals("Bar",poisson2.getNom());
        assertTrue(poisson2.isBio());
        assertEquals(500,poisson2.getCalories());
        assertEquals(1,poisson2.getQuantite());
        assertEquals("Saignant",poisson2.getConsigne());
    }

    @Test
    public void testCreationViande(){
        Viande boeuf = new Viande(1,"Boeuf",false,800,1,"Saignant");

        assertEquals("Boeuf",boeuf.getNom());
        assertFalse(boeuf.isBio());
        assertEquals(800,boeuf.getCalories());
        assertEquals(1,boeuf.getQuantite());
        assertEquals("Saignant",boeuf.getConsigne());

        Viande boeuf2 = new Viande(1,"Boeuf",true,800,2,"A point",5.0);

        assertEquals("Boeuf",boeuf2.getNom());
        assertTrue(boeuf2.isBio());
        assertEquals(1600,boeuf2.getCalories());
        assertEquals(2,boeuf2.getQuantite());
        assertEquals("A point",boeuf2.getConsigne());
    }
}
