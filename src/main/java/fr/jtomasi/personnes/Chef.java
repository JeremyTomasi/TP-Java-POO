package fr.jtomasi.personnes;

import com.sun.tools.jconsole.JConsoleContext;
import fr.jtomasi.Concours;
import fr.jtomasi.Plat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chef extends Personne {

    private String telephone;
    private int nbEtoiles;
    private String specialite;
    private int nbPlatsRealises;
    private List<Concours> participationsConcours = new ArrayList<>();
    private List<Plat> platConnu = new ArrayList<>();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Chef(int id, String nom, String prenom, Genre genre, String telephone, int nbEtoiles, String specialite,int nbPlatsRealises) {
        super(id, nom, prenom, genre);
        this.telephone = telephone;
        this.nbEtoiles = nbEtoiles;
        this.specialite = specialite;
        this.nbPlatsRealises = nbPlatsRealises;
    }

    /**
     * Recupère le numéro de telephone
     * @return String
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Change le numéro de téléphone
     * @param telephone Le nouveau numéro de téléphone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Récupère le nombre d'étoiles
     * @return int
     */
    public int getNbEtoiles() {
        return nbEtoiles;
    }

    /**
     * @param nbEtoiles Le nouveau nombre d'étoiles
     */
    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    /**
     * Récupère la spécialité du chef
     * @return
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * Définit une nouvelle spécialité
     * @param specialite Spécialité à définir
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    /**
     * Récupère le nombre de plats réalisés
     * @return int
     */
    public int getNbPlatsRealises() {
        return nbPlatsRealises;
    }

    /**
     * Modifie le nombre de plats réalisés
     * @param nbPlatsRealises Nouveau nombre de plats réalisés
     */
    public void setNbPlatsRealises(int nbPlatsRealises) {
        this.nbPlatsRealises = nbPlatsRealises;
    }

    /**
     * Ajoute une participation à un concours
     * @param concours Concours auquel le chef participe
     */
    public void addParticipationConcours(Concours concours){
        this.participationsConcours.add(concours);
    }

    /**
     * Affiche les participations du chef aux concours
     */
    public void displayParticipationsConcours(){
        for(Concours concours : this.participationsConcours){
            logger.log(Level.INFO,"Nom du concours : " + concours.getNomConcours());
            logger.log(Level.INFO,"Date de debut du concours : " + concours.getDateDebutConcours());
            logger.log(Level.INFO,"Date de fin du concours : " + concours.getDateFinConcours());
        }
    }

    /**
     * Ajoute un plat à sa liste de plats connus
     * @param plat
     */
    public void addPlatConnu(Plat plat){
        this.platConnu.add(plat);
    }

    /**
     * Affiche la liste des plats connus
     */
    public void displayPlatsConnus(){
        for(Plat plat : this.platConnu){
            logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            logger.log(Level.INFO,"Calories du plat : " + plat.getCaloriesPlat());
            plat.displayIngredients();
        }
    }
}
