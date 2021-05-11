package fr.jtomasi.personnes;

import fr.jtomasi.concours.Concours;
import fr.jtomasi.plats.Plat;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Chef extends Personne {

    private String telephone;
    private int nbEtoiles;
    private String specialite;
    private int nbPlatsRealises;
    private int nbVictoires = 0;
    private transient List<Concours> participationsConcours = new ArrayList<>();
    private transient List<Plat> platConnu = new ArrayList<>();
    private transient List<Padawan> padawans= new ArrayList<>();
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    public Chef(){
        super();
    }

    public Chef(String nom, String prenom, Genre genre, String telephone, int nbEtoiles, String specialite,int nbPlatsRealises) {
        super(nom, prenom, genre);
        this.telephone = telephone;
        this.nbEtoiles = nbEtoiles;
        this.specialite = specialite;
        this.nbPlatsRealises = nbPlatsRealises;
    }

    public void ajouterPadawan(Padawan padawan){
        this.padawans.add(padawan);
        padawan.setChefRef(this);
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

    public void ajouterVictoire(){
        this.nbVictoires++;
    }

    public List<Padawan> getPadawans(){
        return padawans;
    }

    public void displayListePadawans(){
        logger.log(Level.INFO,"Padawans du chef " + this.getNom() + " " + this.getPrenom() + " :");
        for(Padawan padawan : this.padawans){
            logger.log(Level.INFO,"Nom du padawan : " + padawan.getNom());
            logger.log(Level.INFO,"Prenom du padawan : " + padawan.getPrenom());
            logger.log(Level.INFO,"Date de naissance du padawan : " + padawan.displayDateNaissance());
        }
    }
}
