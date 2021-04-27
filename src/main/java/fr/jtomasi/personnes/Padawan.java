package fr.jtomasi.personnes;

import javax.persistence.Entity;

@Entity
public class Padawan extends Personne{
    private String telephone;
    private transient Chef chefRef;

    public Padawan(){
        super();
    }

    public Padawan(int id, String nom, String prenom, Genre genre, String telephone, Chef chefRef) {
        super(id, nom, prenom, genre);
        this.telephone = telephone;
        this.chefRef = chefRef;
    }

    public Padawan(int id, String nom, String prenom, Genre genre, String telephone) {
        super(id, nom, prenom, genre);
        this.telephone = telephone;
    }

    /**
     * Récupère le numéro de téléphone
     * @return String
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Définit un nouveau numéro de téléphone
     * @param telephone Nouveau numéro de téléphone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Récupère le chef référent du participant
     * @return Chef
     */
    public Chef getChefRef() {
        return chefRef;
    }

    public void setChefRef(Chef chefRef) {
        this.chefRef = chefRef;
    }
}
