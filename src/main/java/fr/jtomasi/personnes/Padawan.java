package fr.jtomasi.personnes;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Entity
public class Padawan extends Personne{
    private String telephone;
    private transient Chef chefRef;
    private LocalDate naissance;
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    public Padawan(){
        super();
    }

    public Padawan(String nom, String prenom, Genre genre, String telephone, String dateNaissance) {
        super(nom, prenom, genre);
        this.telephone = telephone;
        naissance = setDateNaissance(dateNaissance);
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

    public String displayDateNaissance(){
        return naissance.getDayOfMonth() + "/" + naissance.getMonthValue() + "/" + naissance.getYear();
    }

    public LocalDate getDateNaissance(){
        return naissance;
    }

    public LocalDate setDateNaissance(String dateNaissance){
        LocalDate naissance = null;
        if(Pattern.matches("\\d+/\\d+/\\d+",dateNaissance)){
            String[] infosNaissances = dateNaissance.split("/");
            int jourNaissance = Integer.parseInt(infosNaissances[0]);
            int moisNaissance = Integer.parseInt(infosNaissances[1]);
            int anneeNaissance = Integer.parseInt(infosNaissances[2]);

            naissance = LocalDate.of(anneeNaissance,moisNaissance,jourNaissance);
        }
        return naissance;
    }
}
