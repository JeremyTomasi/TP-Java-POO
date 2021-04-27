package fr.jtomasi.ingredients;

import javax.persistence.Entity;

@Entity
public class Epice extends Ingredient{

    public Epice(){
        super();
    }

    public Epice(int id,String nom, boolean bio, int calories) {
        super(id,nom, calories, bio);
    }
}
