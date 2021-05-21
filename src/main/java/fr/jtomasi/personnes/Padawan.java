package fr.jtomasi.personnes;

import fr.jtomasi.exceptions.AlreadyHasChefException;
import fr.jtomasi.utilities.Utilities;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Entity
public class Padawan extends Personne implements Serializable {
    private String telephone;
    @Transient
    private Chef chefRef;
    private String idChefRef;
    private LocalDate naissance;
    private transient Logger logger = Logger.getLogger(Padawan.class.getName());

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

    /**
     * Définit un chef pour le Padawan
     * @param chefRef Le chef référent du Padawan
     * @throws AlreadyHasChefException Si le chef référent a déja été défini
     */
    public void setChefRef(Chef chefRef) throws AlreadyHasChefException {
        if(this.chefRef == null){
            this.chefRef = chefRef;
            this.idChefRef = chefRef.getId();
        } else {
            throw new AlreadyHasChefException("Ce padawan a déja un chef !");
        }
    }

    /**
     * Récupère la date de naissance du Padawan
     * @return La date de naissance
     */
    public LocalDate getDateNaissance(){
        return naissance;
    }

    /**
     * Définit la date de naissance du Padawan
     * @param dateNaissance La date de naissance
     * @return Un LocalDate avec la date de naissance
     */
    public LocalDate setDateNaissance(String dateNaissance){
        return Utilities.generateDate(dateNaissance);
    }
}
