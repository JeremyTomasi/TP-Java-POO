import fr.jtomasi.concours.Concours;
import fr.jtomasi.concours.ListeConcours;
import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.exceptions.TousPlatsNonNotesException;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.utilities.Utilities;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ConcoursTest {

    private final ListeConcours listeConcours = new ListeConcours();
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private Chef chef = new Chef("Etchebest","Philippe",Genre.HOMME,"1234567","Gastronomie",50);
    private final Padawan jeremy = new Padawan("Tomasi", "Jeremy", Genre.HOMME, "","21/3/2001");

    @Test
    public void testCreationConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-15", "2021-05-15", listeConcours);

        assertEquals("Top Chef", topChef.getNomConcours());
    }

    @Test
    public void testGetDateDebutConcours() {
        Concours topChef = new Concours("Top Chef", "15-04-2021", "15-04-2021", listeConcours);

        assertEquals("15/4/2021", Utilities.displayDate(topChef.getDateDebutConcours()));
    }

    @Test
    public void testGetDateFinConcours() {
        Concours topChef = new Concours("Top Chef", "15-04-2021", "15-05-2021", listeConcours);

        assertEquals("15/5/2021", Utilities.displayDate(topChef.getDateFinConcours()));
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
        Chef etchebest = new Chef("Etchebest", "Phillipe", Genre.HOMME, "", "Gastronomie", 50);
        Chef lignac = new Chef("Lignac", "Cyril", Genre.HOMME, "", "Patisserie", 20);
        Chef norbert = new Chef("Tarayre", "Norbert", Genre.HOMME, "", "Gastronomie", 20);

        topChef.addChefConcours(etchebest);
        topChef.addChefConcours(lignac);
        topChef.addChefConcours(norbert);

        logger.log(Level.INFO, "\n Test : testDisplayChefs \n");
        topChef.displayListeChefs();
    }

    @Test
    public void testDisplayMembreJury() {
        Concours topChef = new Concours("Top Chef", "2021-04-23", "2021-05-23", listeConcours);
        topChef.addMembreJuryConcours(new MembreJury("testa", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("testz", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("teste", "test", Genre.FEMME, 5));

        logger.log(Level.INFO, "\n Test : testDisplayMembreJury \n");
        topChef.displayListeMembreJury();
    }

    @Test
    public void testChefException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);

        assertThrows(NoNumberChefRequiredException.class, topChef::demarrerConcours);
    }

    @Test
    public void testMembreJuryException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        topChef.addChefConcours(new Chef( "Etchebesta", "Philippe", Genre.HOMME, "",  "Gastronomie", 50));
        topChef.addChefConcours(new Chef( "Etchebestz", "Philippe", Genre.HOMME, "",  "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebeste", "Philippe", Genre.HOMME, "", "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebestr", "Philippe", Genre.HOMME, "", "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebestt", "Philippe", Genre.HOMME, "", "Gastronomie", 50));

        assertThrows(NoNumberMembreJuryRequiredException.class, topChef::demarrerConcours);
    }

    @Test
    public void testParticipantsException() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        topChef.addChefConcours(new Chef("Etchebesta", "Philippe", Genre.HOMME, "", "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebestz", "Philippe", Genre.HOMME, "", "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebeste", "Philippe", Genre.HOMME, "", "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebestr", "Philippe", Genre.HOMME, "", "Gastronomie", 50));
        topChef.addChefConcours(new Chef("Etchebestt", "Philippe", Genre.HOMME, "","Gastronomie", 50));

        topChef.addMembreJuryConcours(new MembreJury("testa", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("testz", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("teste", "test", Genre.FEMME, 5));

        assertThrows(NoParticipantsException.class, topChef::demarrerConcours);
    }

    @Test
    public void testDemarrageConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);

        Chef chef1 = new Chef("Etchebesta", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef2 = new Chef("Etchebestz", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef3 = new Chef("Etchebeste", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef4 = new Chef("Etchebestr", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef5 = new Chef("Etchebestt", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        topChef.addChefConcours(chef5);

        topChef.addMembreJuryConcours(new MembreJury("testa", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("testz", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("teste", "test", Genre.FEMME, 5));

        chef1.ajouterPadawan(jeremy);

        logger.log(Level.INFO, "\n Test : testDemarrageConcours \n");

        try {
            topChef.demarrerConcours();
        } catch (Exception e){
            e.printStackTrace();
        }

        assertTrue(topChef.isConcoursDemarre());
    }

    @Test
    public void testFinirConcours() {
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        Chef chef1 = new Chef("Etchebesta", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef2 = new Chef("Etchebestz", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef3 = new Chef("Etchebeste", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef4 = new Chef("Etchebestr", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef5 = new Chef("Etchebestt", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        topChef.addChefConcours(chef5);

        topChef.addMembreJuryConcours(new MembreJury("testa", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("testz", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("teste", "test", Genre.FEMME, 5));

        chef1.ajouterPadawan(jeremy);

        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry", jeremy);
            rizCurry.addIngredient(new Ingredient( "Riz", 200, false), 2, "Bien cuit");

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
        Chef chef1 = new Chef("Etchebesta", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef2 = new Chef("Etchebestz", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef3 = new Chef("Etchebeste", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef4 = new Chef("Etchebestr", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef5 = new Chef("Etchebestt", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        topChef.addChefConcours(chef5);

        chef1.ajouterPadawan(jeremy);

        topChef.addMembreJuryConcours(new MembreJury("testa", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("testz", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("teste", "test", Genre.FEMME, 5));

        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry", jeremy);
            rizCurry.addIngredient(new Ingredient("Riz", 200, false), 2, "Bien cuit");

            topChef.addPlatConcours(rizCurry);

            topChef.finirConcours();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertThrows(TousPlatsNonNotesException.class, topChef::finirConcours);
        assertFalse(topChef.isConcoursTermine());
    }

    @Test
    public void testDisplayClassement(){
        Concours topChef = new Concours("Top Chef", "2021-04-19", "2021-05-19", listeConcours);
        Chef chef1 = new Chef("Etchebesta", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef2 = new Chef("Etchebestz", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef3 = new Chef("Etchebeste", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef4 = new Chef("Etchebestr", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);
        Chef chef5 = new Chef("Etchebestt", "Philippe", Genre.HOMME, Utilities.generateNumTel(), "Gastronomie", 50);

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        topChef.addChefConcours(chef5);

        topChef.addMembreJuryConcours(new MembreJury("testa", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("teste", "test", Genre.FEMME, 5));
        topChef.addMembreJuryConcours(new MembreJury("testz", "test", Genre.FEMME, 5));

        chef1.ajouterPadawan(jeremy);
        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry", jeremy);
            rizCurry.addIngredient(new Ingredient("Riz", 200, false), 2, "Bien cuit");

            topChef.addPlatConcours(rizCurry);

            topChef.displayClassement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSortConcours(){
        ListeConcours listeConcours = new ListeConcours();

        Concours topChef = new Concours("Top Chef","20/05/2020","20/06/2020",listeConcours);
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","21/05/2020","21/06/2020",listeConcours);

        listeConcours.displayListeConcoursPrevus();

        assertEquals("Top Chef",listeConcours.getConcoursPrevus().get(0).getNomConcours());
    }

}