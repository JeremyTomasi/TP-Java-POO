import fr.jtomasi.personnes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonneTest {

    @Test
    public void testCreationChef(){
        Chef chef = new Chef("Test","Test", Genre.HOMME,"0605442051",4,"Street Food",50);

        chef.displayPlatsConnus();

        assertEquals("Test",chef.getNom());
        assertEquals("Test",chef.getPrenom());
        assertEquals(Genre.HOMME,chef.getGenre());
        assertEquals("0605442051",chef.getTelephone());
        assertEquals(4,chef.getNbEtoiles());
        assertEquals("Street Food",chef.getSpecialite());
        assertEquals(50,chef.getNbPlatsRealises());

    }

    @Test
    public void testCreationPadawan(){
        Padawan padawan = new Padawan("Kenobi","Obi-wan",Genre.HOMME,"0605442051");

        assertEquals("Kenobi",padawan.getNom());
        assertEquals("Obi-wan",padawan.getPrenom());
        assertEquals(Genre.HOMME,padawan.getGenre());
        assertEquals("0605442051",padawan.getTelephone());
    }

    @Test
    public void testCreationMembreJury(){
        MembreJury membre = new MembreJury("Dark","Vador",Genre.HOMME,5);

        assertEquals("Dark",membre.getNom());
        assertEquals("Vador",membre.getPrenom());
        assertEquals(Genre.HOMME,membre.getGenre());
        assertEquals(5,membre.getNbParticipations());
    }

}
