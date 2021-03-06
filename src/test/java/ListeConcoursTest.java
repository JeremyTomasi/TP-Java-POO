import fr.jtomasi.concours.Concours;
import fr.jtomasi.concours.ListeConcours;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.utilities.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListeConcoursTest {

    private final ListeConcours listeConcours = new ListeConcours();
    private Chef chef = new Chef("Etchebest","Philippe",Genre.HOMME,"1234567","Gastronomie",50);
    private final Padawan jeremy = new Padawan("Tomasi","Jeremy",Genre.HOMME,"1234567","21/3/2001");

    @Test
    public void testAddConcoursPrevu(){

        Concours topChef = new Concours("Top Chef","2021-04-23",listeConcours);

        assertEquals("Top Chef",listeConcours.getConcoursPrevus().get(0).getNomConcours());
    }

    @Test
    public void testAddConcoursEnCours(){
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","24-04-2021",listeConcours);

        Chef chef1 = new Chef("Pairet","Paul",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef2 = new Chef("Etchebest","Philippe",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef3 = new Chef("Darroze","Helene",Genre.FEMME,"","Gastronomie",50);
        Chef chef4 = new Chef("Sarran","Michel",Genre.HOMME,"","Gastronomie",50);
        Chef chef5 = new Chef("Piege","Jean-Francois",Genre.HOMME,"","Gastronomie",50);

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

        listeConcours.displayConcoursEnCours();

        assertEquals("Le Meilleur Patissier",listeConcours.getConcoursEnCours().getNomConcours());
    }

    @Test
    public void testAddConcoursTermines(){
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","24-04-2021",listeConcours);

        Chef chef1 = new Chef("Pairet","Paul",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef2 = new Chef("Etchebest","Philippe",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef3 = new Chef("Darroze","Helene",Genre.FEMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef4 = new Chef("Sarran","Michel",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef5 = new Chef("Piege","Jean-Francois",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);

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

        listeConcours.displayListeConcoursTermines();

        assertEquals("Le Meilleur Patissier",listeConcours.getConcoursTermines().get(0).getNomConcours());
    }

    @Test
    public void testPassagePadawanChef(){
        Chef nouveauChef = null;
        Concours meilleurPatissier = new Concours("Le Meilleur Patissier","24-04-2021",listeConcours);

        Chef chef1 = new Chef("Lignac","Cyril",Genre.HOMME,Utilities.generateNumTel(),"Patisserie",25);
        Chef chef2 = new Chef("Lignaca","Cyril",Genre.HOMME,Utilities.generateNumTel(),"Patisserie",25);
        Chef chef3 = new Chef("Lignacz","Cyril",Genre.HOMME,Utilities.generateNumTel(),"Patisserie",25);
        Chef chef4 = new Chef("Lignace","Cyril",Genre.HOMME,Utilities.generateNumTel(),"Patisserie",25);
        Chef chef5 = new Chef("Lignacr","Cyril",Genre.HOMME,Utilities.generateNumTel(),"Patisserie",25);

        meilleurPatissier.addChefConcours(chef1);
        meilleurPatissier.addChefConcours(chef2);
        meilleurPatissier.addChefConcours(chef3);
        meilleurPatissier.addChefConcours(chef4);
        meilleurPatissier.addChefConcours(chef5);

        MembreJury membreJury1 = new MembreJury("Mercotte","Mercotte",Genre.FEMME,5);
        MembreJury membreJury2 = new MembreJury("Mercottea","Mercotte",Genre.FEMME,5);
        MembreJury membreJury3 = new MembreJury("Mercottez","Mercotte",Genre.FEMME,5);

        meilleurPatissier.addMembreJuryConcours(membreJury1);
        meilleurPatissier.addMembreJuryConcours(membreJury2);
        meilleurPatissier.addMembreJuryConcours(membreJury3);

        chef1.ajouterPadawan(jeremy);

        try {
            meilleurPatissier.demarrerConcours();

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

    @Test
    public void testReadFile(){
        ListeConcours liste = ListeConcours.loadCuisine("cuisine.ser");
        liste.displayConcoursEnCours();
    }
}
