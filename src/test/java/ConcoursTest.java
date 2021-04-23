import fr.jtomasi.Concours;
import fr.jtomasi.Plat;
import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
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


        topChef.addPlatConcours(rizCurry);

        topChef.displayListePlats();

        Assertions.assertEquals("Riz au curry",topChef.getListePlats().get(0).getNomPlat());
    }

    @Test
    public void testDisplayChefs(){
        Concours topChef = new Concours("Top Chef","2021-04-17","2021-05-17");
        Chef etchebest = new Chef(1,"Etchebest","Phillipe",Genre.HOMME,"",4,"Gastronomie",50);
        Chef lignac = new Chef(1,"Lignac","Cyril",Genre.HOMME,"",4,"Patisserie",20);
        Chef norbert = new Chef(1,"Tarayre","Norbert",Genre.HOMME,"",3,"Gastronomie",20);

        topChef.addChefConcours(etchebest);
        topChef.addChefConcours(lignac);
        topChef.addChefConcours(norbert);

        topChef.displayListeChefs();
    }

    @Test
    public void testDisplayParticipants(){
        Concours topChef = new Concours("Top Chef","2021-04-17","2021-05-17");
        Padawan padawan1 = new Padawan(1,"Tomasi","Jeremy",Genre.HOMME,"0605442051");

        topChef.addParticipant(padawan1);

        topChef.displayListeParticipants();
    }

    @Test
    public void testChefException(){
        Concours topChef = new Concours("Top Chef","2021-04-19","2021-05-19");

        Assertions.assertThrows(NoNumberChefRequiredException.class, topChef::demarrerConcours);
    }

    @Test
    public void testMembreJuryException(){
        Concours topChef = new Concours("Top Chef","2021-04-19","2021-05-19");
        topChef.addChefConcours(new Chef(1,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(2,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(3,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(4,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(5,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));

        Assertions.assertThrows(NoNumberMembreJuryRequiredException.class, topChef::demarrerConcours);
    }

    @Test
    public void testParticipantsException(){
        Concours topChef = new Concours("Top Chef","2021-04-19","2021-05-19");
        topChef.addChefConcours(new Chef(1,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(2,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(3,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(4,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(5,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));

        topChef.addMembreJuryConcours(new MembreJury(1,"test","test",Genre.FEMME,5));
        topChef.addMembreJuryConcours(new MembreJury(2,"test","test",Genre.FEMME,5));
        topChef.addMembreJuryConcours(new MembreJury(3,"test","test",Genre.FEMME,5));

        Assertions.assertThrows(NoParticipantsException.class, topChef::demarrerConcours);
    }

}
