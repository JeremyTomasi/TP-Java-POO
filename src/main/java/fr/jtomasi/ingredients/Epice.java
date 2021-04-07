package fr.jtomasi.ingredients;

public class Epice extends Ingredient{


    public Epice(String nom, boolean bio, int calories, double poids, int quantite) {
        super(nom, bio, calories, poids, quantite);
    }

    public Epice(String nom, boolean bio, int calories, double poids, int quantite, String consigne) {
        super(nom, bio, calories, poids, quantite, consigne);
    }

    @Override
    public void setConsigne(String consigne) {
        super.setConsigne(consigne);
    }
}
