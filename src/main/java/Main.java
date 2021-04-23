import fr.jtomasi.Concours;
import fr.jtomasi.ListeConcours;

public class Main {
    public static void main(String[] args) {
        ListeConcours listeConcours = new ListeConcours();

        Concours topChef = new Concours("TopChef","2021-04-23","2021-05-23",listeConcours);

        try {
            topChef.demarrerConcours();
        } catch (Exception e){

        }
    }
}
