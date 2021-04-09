package fr.jtomasi.ingredients;

public class Epice extends Ingredient{


    public Epice(String nom, boolean bio, int calories, int quantite) {
        super(nom, bio, calories, quantite);
    }

    public Epice(String nom, boolean bio, int calories, int quantite, String consigne) {
        super(nom, bio, calories, quantite, consigne);
    }
}
