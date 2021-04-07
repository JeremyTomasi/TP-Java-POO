package fr.jtomasi.personnes;

public class Padawan extends Personne{
    private String telephone;
    private Chef chefRef;

    public Padawan(int id, String nom, String prenom, Genre genre, String telephone, Chef chefRef) {
        super(id, nom, prenom, genre);
        this.telephone = telephone;
        this.chefRef = chefRef;
    }

    public Padawan(int id, String nom, String prenom, Genre genre, String telephone) {
        super(id, nom, prenom, genre);
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Chef getChefRef() {
        return chefRef;
    }

    public void setChefRef(Chef chefRef) {
        this.chefRef = chefRef;
    }
}
