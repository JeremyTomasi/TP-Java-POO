package fr.jtomasi.personnes;

import fr.jtomasi.concours.Concours;
import fr.jtomasi.exceptions.AlreadyHasChefException;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.utilities.Utilities;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Chef extends Personne implements Serializable {

    private String telephone;
    private int nbEtoiles;
    private String specialite;
    private int nbPlatsRealises;
    private int nbVictoires = 0;
    @Transient
    private final List<Concours> participationsConcours = new ArrayList<>();
    @Transient
    private final List<Plat> platConnu = new ArrayList<>();
    @Transient
    private final List<Padawan> padawans = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Chef.class.getName());

    public Chef(){
        super();
    }

    public Chef(String nom, String prenom, Genre genre, String telephone, String specialite,int nbPlatsRealises) {
        super(nom, prenom, genre);
        this.telephone = telephone;
        this.nbEtoiles = 0;
        this.specialite = specialite;
        this.nbPlatsRealises = nbPlatsRealises;
    }

    /**
     * Ajoute un nouveau Padawan dans la liste des Padawans du chef
     * @param padawan Le padawan à ajouter
     */
    public void ajouterPadawan(Padawan padawan){
        // On ajoute le Padawan dans la liste des padawans
        this.padawans.add(padawan);
        try {
            // On essaie de définir un chef référent pour le padawan
            padawan.setChefRef(this);
        } catch (AlreadyHasChefException e){
            e.printStackTrace();
        }
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
     * @return String
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
     * @param plat Plat à ajouter
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

    /**
     * Ajoute une victoire au chef s'il gagne le concours
     */
    public void ajouterVictoire(){
        this.nbVictoires++;
    }

    /**
     * Récupère la liste des padawans
     * @return La liste des padawans
     */
    public List<Padawan> getPadawans(){
        return padawans;
    }

    /**
     * Affiche la liste des padawans
     */
    public void displayListePadawans(){
        logger.log(Level.INFO,"Padawans du chef " + this.getNom() + " " + this.getPrenom() + " :");
        for(Padawan padawan : this.padawans){
            logger.log(Level.INFO,"Nom du padawan : " + padawan.getNom());
            logger.log(Level.INFO,"Prenom du padawan : " + padawan.getPrenom());
            logger.log(Level.INFO,"Date de naissance du padawan : " + Utilities.displayDate(padawan.getDateNaissance()));
        }
    }

    /**
     * Récupère le nombre de victoires du chef
     * @return Le nombre de victoires du chef
     */
    public int getNbVictoires(){
        return this.nbVictoires;
    }

    /**
     * Ajoute des étoiles au chef en cas de victoire
     * @param nbEtoiles Le nombre d'étoiles à ajouter
     */
    public void ajouterEtoiles(int nbEtoiles){
        this.nbEtoiles += nbEtoiles;
    }
}
