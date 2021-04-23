import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;
import fr.jtomasi.Plat;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListeConcoursTest {

    private ListeConcours listeConcours = new ListeConcours();
    private Padawan jeremy = new Padawan(1,"Tomasi","Jeremy",Genre.HOMME,"");

    @Test
    public void testAddConcoursPrevu(){

        Concours topChef = new Concours("Top Chef","2021-04-23","2021-05-23",listeConcours);

        try {
            topChef.demarrerConcours();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        Assertions.assertEquals("Top Chef",listeConcours.getConcoursPrevus().get(0).getNomConcours());
    }

    @Test
    public void testAddConcoursEnCours(){
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","2021-04-24","2021-04-24",listeConcours);

        meilleurPatissier.addChefConcours(new Chef(1,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(2,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(3,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(4,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(5,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));

        meilleurPatissier.addMembreJuryConcours(new MembreJury(1,"Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury(2,"Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury(3,"Mercotte","Mercotte",Genre.FEMME,5));

        meilleurPatissier.addParticipant(new Padawan(1,"Tomasi","Jeremy",Genre.HOMME,"0605442051"));

        try {
            meilleurPatissier.demarrerConcours();

            listeConcours.addConcoursEnCours(meilleurPatissier);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        listeConcours.displayListeConcoursEnCours();

        Assertions.assertEquals("Le Meilleur Patissier",listeConcours.getConcoursEnCours().get(0).getNomConcours());
    }

    @Test
    public void testAddConcoursTermines(){
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","2021-04-24","2021-04-24",listeConcours);

        meilleurPatissier.addChefConcours(new Chef(1,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(2,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(3,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(4,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef(5,"Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));

        meilleurPatissier.addMembreJuryConcours(new MembreJury(1,"Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury(2,"Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury(3,"Mercotte","Mercotte",Genre.FEMME,5));

        meilleurPatissier.addParticipant(new Padawan(1,"Tomasi","Jeremy",Genre.HOMME,"0605442051"));

        try {
            meilleurPatissier.demarrerConcours();

            listeConcours.addConcoursEnCours(meilleurPatissier);

            Plat gateau = new Plat("Gateau au chocolat",jeremy);

            gateau.noterPlat(8);

            meilleurPatissier.finirConcours();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        listeConcours.displayListeConcoursTermines();

        Assertions.assertEquals("Le Meilleur Patissier",listeConcours.getConcoursTermines().get(0).getNomConcours());
    }
}
