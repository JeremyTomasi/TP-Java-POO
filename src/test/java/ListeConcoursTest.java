import fr.jtomasi.concours.Concours;
import fr.jtomasi.concours.ListeConcours;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListeConcoursTest {

    private final ListeConcours listeConcours = new ListeConcours();
    private Chef chef = new Chef("Etchebest","Philippe",Genre.HOMME,"1234567",4,"Gastronomie",50);
    private final Padawan jeremy = new Padawan("Tomasi","Jeremy",Genre.HOMME,"1234567","21/3/2001");

    @Test
    public void testAddConcoursPrevu(){

        Concours topChef = new Concours("Top Chef","2021-04-23","2021-05-23",listeConcours);

        assertEquals("Top Chef",listeConcours.getConcoursPrevus().get(0).getNomConcours());
    }

    @Test
    public void testAddConcoursEnCours(){
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","2021-04-24","2021-05-24",listeConcours);

        Chef chef1 = new Chef("Pairet","Paul",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef2 = new Chef("Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef3 = new Chef("Darroze","Helene",Genre.FEMME,"",4,"Gastronomie",50);
        Chef chef4 = new Chef("Sarran","Michel",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef5 = new Chef("Piege","Jean-Francois",Genre.HOMME,"",4,"Gastronomie",50);

        meilleurPatissier.addChefConcours(chef1);
        meilleurPatissier.addChefConcours(chef2);
        meilleurPatissier.addChefConcours(chef3);
        meilleurPatissier.addChefConcours(chef4);
        meilleurPatissier.addChefConcours(chef5);

        MembreJury membreJury1 = new MembreJury("Marx","Thierry",Genre.HOMME,4);
        MembreJury membreJury2 = new MembreJury("Lignac","Cyril",Genre.HOMME,4);
        MembreJury membreJury3 = new MembreJury("Arabian","Ghislaine",Genre.FEMME,4);

        meilleurPatissier.addMembreJuryConcours(membreJury1);
        meilleurPatissier.addMembreJuryConcours(membreJury2);
        meilleurPatissier.addMembreJuryConcours(membreJury3);

        chef1.ajouterPadawan(jeremy);

        try {
            meilleurPatissier.demarrerConcours();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        listeConcours.displayListeConcoursEnCours();

        assertEquals("Le Meilleur Patissier",listeConcours.getConcoursEnCours().get(0).getNomConcours());
    }

    @Test
    public void testAddConcoursTermines(){
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","2021-04-24","2021-05-24",listeConcours);

        Chef chef1 = new Chef("Pairet","Paul",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef2 = new Chef("Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef3 = new Chef("Darroze","Helene",Genre.FEMME,"",4,"Gastronomie",50);
        Chef chef4 = new Chef("Sarran","Michel",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef5 = new Chef("Piege","Jean-Francois",Genre.HOMME,"",4,"Gastronomie",50);

        meilleurPatissier.addChefConcours(chef1);
        meilleurPatissier.addChefConcours(chef2);
        meilleurPatissier.addChefConcours(chef3);
        meilleurPatissier.addChefConcours(chef4);
        meilleurPatissier.addChefConcours(chef5);

        MembreJury membreJury1 = new MembreJury("Marx","Thierry",Genre.HOMME,4);
        MembreJury membreJury2 = new MembreJury("Lignac","Cyril",Genre.HOMME,4);
        MembreJury membreJury3 = new MembreJury("Arabian","Ghislaine",Genre.FEMME,4);

        meilleurPatissier.addMembreJuryConcours(membreJury1);
        meilleurPatissier.addMembreJuryConcours(membreJury2);
        meilleurPatissier.addMembreJuryConcours(membreJury3);

        chef1.ajouterPadawan(jeremy);

        try {
            meilleurPatissier.demarrerConcours();

            Plat gateau = new Plat("Gateau au chocolat",jeremy);

            meilleurPatissier.addPlatConcours(gateau);

            gateau.noterPlat(15);

            meilleurPatissier.finirConcours();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //listeConcours.displayListeConcoursTermines();

        assertEquals("Le Meilleur Patissier",listeConcours.getConcoursTermines().get(0).getNomConcours());
    }

    @Test
    public void testPassagePadawanChef(){
        Chef nouveauChef = null;
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","2021-04-24","2021-04-24",listeConcours);

        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));
        meilleurPatissier.addChefConcours(new Chef("Lignac","Cyril", Genre.HOMME,"123",4,"Patisserie",20));

        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));
        meilleurPatissier.addMembreJuryConcours(new MembreJury("Mercotte","Mercotte",Genre.FEMME,5));

        try {
            meilleurPatissier.demarrerConcours();

            listeConcours.addConcoursEnCours(meilleurPatissier);

            Plat gateau = new Plat("Gateau au chocolat",jeremy);

            meilleurPatissier.addPlatConcours(gateau);

            gateau.noterPlat(8);

            nouveauChef = meilleurPatissier.finirConcours();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(nouveauChef != null){
            assertEquals("Tomasi",nouveauChef.getNom());
            assertEquals("Jeremy",nouveauChef.getPrenom());
        }
    }
}
