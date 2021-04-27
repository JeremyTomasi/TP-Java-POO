import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;
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

        topChef.addChefConcours(new Chef(14,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(4522,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(452343,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(5434,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));
        topChef.addChefConcours(new Chef(4525,"Etchebest","Philippe",Genre.HOMME,"",4,"Gastronomie",50));

        topChef.addMembreJuryConcours(new MembreJury(87981,"Mercotte","Mercotte",Genre.FEMME,5));
        topChef.addMembreJuryConcours(new MembreJury(7856782,"Mercotte","Mercotte",Genre.FEMME,5));
        topChef.addMembreJuryConcours(new MembreJury(54673,"Mercotte","Mercotte",Genre.FEMME,5));

        topChef.addParticipant(new Padawan(56761,"Tomasi","Jeremy",Genre.HOMME,"1213"));
        try {
            topChef.demarrerConcours();

            listeConcours.saveBdd();
        } catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
}
