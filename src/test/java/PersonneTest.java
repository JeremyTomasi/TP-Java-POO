import fr.jtomasi.personnes.*;
import fr.jtomasi.plats.Plat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonneTest {

    @Test
    public void testCreationChef(){
        Chef chef = new Chef("Test","Test", Genre.HOMME,"0605442051","Street Food",50);

        assertEquals("Test",chef.getNom());
        assertEquals("Test",chef.getPrenom());
        assertEquals(Genre.HOMME,chef.getGenre());
        assertEquals("0605442051",chef.getTelephone());
        assertEquals(0,chef.getNbEtoiles());
        assertEquals("Street Food",chef.getSpecialite());
        assertEquals(50,chef.getNbPlatsRealises());

    }

    @Test
    public void testCreationPadawan(){
        Chef chef = new Chef("Etchebest","Philippe",Genre.HOMME,"12564","Gastronomie",50);
        Padawan padawan = new Padawan("Kenobi","Obi-wan",Genre.HOMME,"0605442051","21/3/2001");

        assertEquals("Kenobi",padawan.getNom());
        assertEquals("Obi-wan",padawan.getPrenom());
        assertEquals(Genre.HOMME,padawan.getGenre());
        assertEquals("0605442051",padawan.getTelephone());
        assertEquals("21/3/2001",padawan.displayDateNaissance());
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
