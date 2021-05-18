package fr.jtomasi.ingredients;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Epice extends Ingredient implements Serializable {

    public Epice(){
        super();
    }

    public Epice(int id,String nom, boolean bio, int calories) {
        super(nom, calories, bio);
    }
}
