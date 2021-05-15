package fr.jtomasi.personnes;

import fr.jtomasi.exceptions.AlreadyHasChefException;
import fr.jtomasi.utilities.Utilities;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Entity
public class Padawan extends Personne{
    private String telephone;
    private transient Chef chefRef;
    private String idChefRef;
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

    public void setChefRef(Chef chefRef) throws AlreadyHasChefException {
        if(this.chefRef == null){
            this.chefRef = chefRef;
            this.idChefRef = chefRef.getId();
        } else {
            throw new AlreadyHasChefException("Ce padawan a déja un chef !");
        }
    }

    public String displayDateNaissance(){
        return naissance.getDayOfMonth() + "/" + naissance.getMonthValue() + "/" + naissance.getYear();
    }

    public LocalDate getDateNaissance(){
        return naissance;
    }

    public LocalDate setDateNaissance(String dateNaissance){
        return Utilities.generateDate(dateNaissance);
    }
}
