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
    private Logger logger;

    public Chef(int id, String nom, String prenom, Genre genre, String telephone, int nbEtoiles, String specialite,int nbPlatsRealises) {
        super(id, nom, prenom, genre);
        logger = Logger.getLogger(this.getClass().getName());
        this.telephone = telephone;
        this.nbEtoiles = nbEtoiles;
        this.specialite = specialite;
        this.nbPlatsRealises = nbPlatsRealises;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNbPlatsRealises() {
        return nbPlatsRealises;
    }

    public void setNbPlatsRealises(int nbPlatsRealises) {
        this.nbPlatsRealises = nbPlatsRealises;
    }

    public void addParticipationConcours(Concours concours){
        this.participationsConcours.add(concours);
    }

    public void displayParticipationsConcours(){
        for(Concours concours : this.participationsConcours){
            logger.log(Level.INFO,"Nom du concours : " + concours.getNomConcours());
            logger.log(Level.INFO,"Date de debut du concours : " + concours.getDateDebutConcours());
            logger.log(Level.INFO,"Date de fin du concours : " + concours.getDateFinConcours());
        }
    }

    public void addPlatConnu(Plat plat){
        this.platConnu.add(plat);
    }

    public void displayPlatsConnus(){
        for(Plat plat : this.platConnu){
            logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            plat.displayIngredients();
        }
    }
}
