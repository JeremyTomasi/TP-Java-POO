import fr.jtomasi.concours.Concours;
import fr.jtomasi.concours.ListeConcours;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListeConcoursTest {

    private final ListeConcours listeConcours = new ListeConcours();
    private final Padawan jeremy = new Padawan("Tomasi","Jeremy",Genre.HOMME,"");

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

        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));

        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));

        meilleurPatissier.addParticipant(new Padawan("Tomasi","Jeremy",Genre.HOMME,"0605442051"));

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

        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));

        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));

        meilleurPatissier.addParticipant(new Padawan("Tomasi","Jeremy",Genre.HOMME,"0605442051"));

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
