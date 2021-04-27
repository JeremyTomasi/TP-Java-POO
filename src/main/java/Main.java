import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;
import fr.jtomasi.Plat;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Viande;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        ListeConcours listeConcours = new ListeConcours();
        Logger logger = Logger.getLogger("Main");

        Concours topChef = new Concours("TopChef","2021-04-23","2021-05-23",listeConcours);

        Chef chef1 = new Chef("Pairet","Paul",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef2 = new Chef("Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef3 = new Chef("Darroze","Helene",Genre.FEMME,"",4,"Gastronomie",50);
        Chef chef4 = new Chef("Sarran","Michel",Genre.HOMME,"",4,"Gastronomie",50);
        Chef chef5 = new Chef("Piege","Jean-Francois",Genre.HOMME,"",4,"Gastronomie",50);

        MembreJury membreJury1 = new MembreJury("Marx","Thierry",Genre.HOMME,4);
        MembreJury membreJury2 = new MembreJury("Lignac","Cyril",Genre.HOMME,4);
        MembreJury membreJury3 = new MembreJury("Sarran","Michel",Genre.HOMME,4);

        Padawan padawan1 = new Padawan("Tomasi","Jeremy",Genre.HOMME,"1513");

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        topChef.addChefConcours(chef5);

        topChef.addMembreJuryConcours(membreJury1);
        topChef.addMembreJuryConcours(membreJury2);
        topChef.addMembreJuryConcours(membreJury3);

        topChef.addParticipant(padawan1);
        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry",padawan1);
            rizCurry.addIngredient(new Ingredient("Riz",200,true),3,"Bien cuit");
            rizCurry.addIngredient(new Viande("Aiguillettes de poulet",true,200,20),6,"Bien cuit");
            rizCurry.addIngredient(new Ingredient("Sauce Curry",200,false),2,"Chaude");

            topChef.addPlatConcours(rizCurry);

            listeConcours.saveBdd();
        } catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
}
