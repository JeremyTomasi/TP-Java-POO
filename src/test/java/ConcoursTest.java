import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;
import fr.jtomasi.Plat;
import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.exceptions.TousPlatsNonNotesException;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ConcoursTest {

    private final ListeConcours listeConcours = new ListeConcours();
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Padawan jeremy = new Padawan(1, "Tomasi", "Jeremy", Genre.HOMME, "");

    @Test
    public void testCreationConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-15", "2021-05-15", listeConcours);

        assertEquals("Top Chef", topChef.getNomConcours());
    }

    @Test
    public void testGetDateDebutConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-15", "2021-05-15", listeConcours);

        assertEquals("2021-04-15", topChef.getDateDebutConcours());
    }

    @Test
    public void testGetDateFinConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-15", "2021-05-15", listeConcours);

        assertEquals("2021-05-15", topChef.getDateFinConcours());
    }

    @Test
    public void testAjoutPlat() {
        Concours topChef = new Concours("Top Chef", "2021-04-15", "2021-05-15", listeConcours);

        Plat rizCurry = new Plat("Riz au curry", jeremy);


        topChef.addPlatConcours(rizCurry);

        logger.log(Level.INFO, "Test : testAjoutPlat \n");
        topChef.displayListePlats();

        assertEquals("Riz au curry", topChef.getListePlats().get(0).getNomPlat());
    }

    @Test
    public void testDisplayChefs() {
        Concours topChef = new Concours("Top Chef", "2021-04-17", "2021-05-17", listeConcours);
        Chef etchebest = new Chef(1, "Etchebest", "Phillipe", Genre.HOMME, "", 4, "Gastronomie", 50);
        Chef lignac = new Chef(1, "Lignac", "Cyril", Genre.HOMME, "", 4, "Patisserie", 20);
        Chef norbert = new Chef(1, "Tarayre", "Norbert", Genre.HOMME, "", 3, "Gastronomie", 20);

        topChef.addChefConcours(etchebest);
        topChef.addChefConcours(lignac);
        topChef.addChefConcours(norbert);

        logger.log(Level.INFO, "\n Test : testDisplayChefs \n");
        topChef.displayListeChefs();
    }

    @Test
    public void testDisplayMembreJury() {
        Concours topChef = new Concours("Top Chef", "2021-04-23", "2021-05-23", listeConcours);
        topChef.addMembreJuryConcours(new MembreJury(1, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(2, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(3, "test", "test", Genre.FEMME, 5));

        logger.log(Level.INFO, "\n Test : testDisplayMembreJury \n");
        topChef.displayListeMembreJury();
    }

    @Test
    public void testDisplayParticipants() {
        Concours topChef = new Concours("Top Chef", "2021-04-17", "2021-05-17", listeConcours);
        Padawan padawan1 = new Padawan(1, "Tomasi", "Jeremy", Genre.HOMME, "0605442051");

        topChef.addParticipant(padawan1);

        logger.log(Level.INFO, "\n Test : testDisplayParticipants \n");
        topChef.displayListeParticipants();
    }

    @Test
    public void testChefException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);

        assertThrows(NoNumberChefRequiredException.class, topChef::demarrerConcours);
    }

    @Test
    public void testMembreJuryException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        topChef.addChefConcours(new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(2, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(3, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(4, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(5, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));

        assertThrows(NoNumberMembreJuryRequiredException.class, topChef::demarrerConcours);
    }

    @Test
    public void testParticipantsException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        topChef.addChefConcours(new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(2, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(3, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(4, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(5, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));

        topChef.addMembreJuryConcours(new MembreJury(1, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(2, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(3, "test", "test", Genre.FEMME, 5));

        assertThrows(NoParticipantsException.class, topChef::demarrerConcours);
    }

    @Test
    public void testDemarrageConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);

        Chef chef1 = new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50);
        Chef chef2 = new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50);
        Chef chef3 = new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50);
        Chef chef4 = new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50);
        Chef chef5 = new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50);

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        topChef.addChefConcours(chef5);

        topChef.addMembreJuryConcours(new MembreJury(1, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(2, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(3, "test", "test", Genre.FEMME, 5));

        topChef.addParticipant(new Padawan(1, "Tomasi", "Jeremy", Genre.HOMME, "0605442051", chef1));

        logger.log(Level.INFO, "\n Test : testDemarrageConcours \n");
        topChef.displayListeParticipants();

        try {
            topChef.demarrerConcours();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertTrue(topChef.isConcoursDemarre());
    }

    @Test
    public void testFinirConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        topChef.addChefConcours(new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(2, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(3, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(4, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(5, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));

        topChef.addMembreJuryConcours(new MembreJury(1, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(2, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(3, "test", "test", Genre.FEMME, 5));

        topChef.addParticipant(new Padawan(1, "Tomasi", "Jeremy", Genre.HOMME, "0605442051"));

        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry", jeremy);
            rizCurry.addIngredient(new Ingredient(1, "Riz", 200, false), 2, "Bien cuit");

            topChef.addPlatConcours(rizCurry);

            rizCurry.noterPlat(7);

            topChef.finirConcours();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertTrue(topChef.isConcoursTermine());
    }


    @Test
    public void testFinirConcoursException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        topChef.addChefConcours(new Chef(1, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(2, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(3, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(4, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));
        topChef.addChefConcours(new Chef(5, "Etchebest", "Philippe", Genre.HOMME, "", 4, "Gastronomie", 50));

        topChef.addMembreJuryConcours(new MembreJury(1, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(2, "test", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury(3, "test", "test", Genre.FEMME, 5));

        topChef.addParticipant(new Padawan(1, "Tomasi", "Jeremy", Genre.HOMME, "0605442051"));

        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry", jeremy);
            rizCurry.addIngredient(new Ingredient(1, "Riz", 200, false), 2, "Bien cuit");

            topChef.addPlatConcours(rizCurry);

            topChef.finirConcours();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertThrows(TousPlatsNonNotesException.class, topChef::finirConcours);
        assertFalse(topChef.isConcoursTermine());
    }

}