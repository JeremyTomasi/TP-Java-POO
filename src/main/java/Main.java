import com.github.javafaker.Faker;
import fr.jtomasi.concours.Concours;
import fr.jtomasi.concours.ListeConcours;
import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.ingredients.Viande;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.utilities.Utilities;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        ListeConcours listeConcours = new ListeConcours();
        Logger logger = Logger.getLogger("Main");

        Concours topChef = new Concours("TopChef","2021-04-23","2021-05-23",listeConcours);

        Chef chef1 = new Chef("Pairet","Paul",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef2 = new Chef("Etchebest","Philippe",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef3 = new Chef("Darroze","Helene",Genre.FEMME,Utilities.generateNumTel(),"Gastronomie",50);
        Chef chef4 = new Chef("Sarran","Michel",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);
        //Chef chef5 = new Chef("Piege","Jean-Francois",Genre.HOMME,Utilities.generateNumTel(),"Gastronomie",50);

        MembreJury membreJury1 = new MembreJury("Marx","Thierry",Genre.HOMME,4);
        MembreJury membreJury2 = new MembreJury("Lignac","Cyril",Genre.HOMME,4);
        MembreJury membreJury3 = new MembreJury("Arabian","Ghislaine",Genre.FEMME,4);

        Padawan padawan1 = new Padawan("Tomasi","Jeremy",Genre.HOMME,Utilities.generateNumTel(),"21/3/2001");

        chef1.ajouterPadawan(padawan1);

        topChef.addChefConcours(chef1);
        topChef.addChefConcours(chef2);
        topChef.addChefConcours(chef3);
        topChef.addChefConcours(chef4);
        //topChef.addChefConcours(chef5);

        topChef.addMembreJuryConcours(membreJury1);
        topChef.addMembreJuryConcours(membreJury2);
        topChef.addMembreJuryConcours(membreJury3);
        try {
            topChef.demarrerConcours();

            Plat rizCurry = new Plat("Riz au curry",padawan1);
            rizCurry.addIngredient(new Ingredient("Riz",200,true),3,"Bien cuit");
            rizCurry.addIngredient(new Viande("Aiguillettes de poulet",true,200,20),6,"Bien cuit");
            rizCurry.addIngredient(new Ingredient("Sauce Curry",200,true),2,"Chaude");

            topChef.addPlatConcours(rizCurry);

            rizCurry.noterPlat(15);

            topChef.displayListePlats();

            topChef.displayClassement();

            listeConcours.afficherIngredientsConnus();

            listeConcours.saveIngredientsJson("ingredients.json");

            listeConcours.saveCuisine();

            listeConcours.saveBdd();

        } catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
}
