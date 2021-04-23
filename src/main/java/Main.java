import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        ListeConcours listeConcours = new ListeConcours();
        Logger logger = Logger.getLogger("Main");

        Concours topChef = new Concours("TopChef","2021-04-23","2021-05-23",listeConcours);
        try {
            topChef.demarrerConcours();
        } catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
}
