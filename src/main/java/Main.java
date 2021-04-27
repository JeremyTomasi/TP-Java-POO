import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;
import fr.jtomasi.Plat;
import fr.jtomasi.Recette;
import fr.jtomasi.ingredients.Viande;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Genre;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;

import javax.persistence.StoredProcedureParameter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        ListeConcours listeConcours = new ListeConcours();
        Logger logger = Logger.getLogger("Main");

        Concours topChef = new Concours("TopChef","2021-04-23","2021-05-23",listeConcours);
        try {
            topChef.demarrerConcours();

            listeConcours.saveBdd();
        } catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
}
