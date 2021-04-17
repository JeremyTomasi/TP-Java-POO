package fr.jtomasi;

import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Concours {
    private int minChef = 5;
    private int minMembreJury = 3;
    private boolean concoursDemarre = false;
    private boolean concoursTermine = false;

    private List<Chef> chefConcours = new ArrayList<>();
    private List<MembreJury> juryConcours = new ArrayList<>();
    private List<Padawan> participantsConcours = new ArrayList<>();
    private List<Concours> concoursEnCours = new ArrayList<>();
    private List<Concours> concoursTermines = new ArrayList<>();
    private List<Plat> listePlats = new ArrayList<>();

    private String nomConcours;
    private String dateDebutConcours;
    private String dateFinConcours;

    private Logger logger;

    public void demarrerConcours() throws Exception{
        if(chefConcours.size() < this.minChef || juryConcours.size() < this.minMembreJury){
            throw new Exception("Il manque des chefs ou des membres de jury !!!");
        } else if(participantsConcours.size() == 0){
            throw new Exception("Il manque des participants dans le concours !!!");
        } else {
            concoursDemarre = true;
        }
    }

    public void finirConcours(){
        int nbPlatsNotes = 0;
        for(Plat plat : this.listePlats){
            if(plat.getNotePlat() != -1){
                nbPlatsNotes++;
            }
        }

        if(nbPlatsNotes == listePlats.size()){
            concoursTermine = true;
            concoursDemarre = false;
        }
    }


    public void addMembreJuryConcours(MembreJury membreJury){
        this.juryConcours.add(membreJury);
        membreJury.ajouterParticipationConcours(this);
    }

    public void addChefConcours(Chef chef){
        this.chefConcours.add(chef);
        chef.addParticipationConcours(this);
    }

    public Concours(String nomConcours, String dateDebutConcours, String dateFinConcours){
        logger = Logger.getLogger(this.getClass().getName());
        this.nomConcours = nomConcours;
        this.dateDebutConcours = dateDebutConcours;
        this.dateFinConcours = dateFinConcours;
    }

    public String getNomConcours(){
        return this.nomConcours;
    }

    public void addParticipant(Padawan padawan){
        this.participantsConcours.add(padawan);
    }

    public void addPlatConcours(Plat plat){
        this.listePlats.add(plat);
    }

    public List<Plat> getListePlats(){
        return this.listePlats;
    }

    public void displayListePlats(){
        for(Plat plat : this.getListePlats()){
            logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            plat.displayIngredients();
        }
    }

    public void displayListeChefs(){
        for(Chef chef : this.chefConcours){
            logger.log(Level.INFO,"Nom du chef : " + chef.getNom());
            logger.log(Level.INFO,"Prenom du chef : " + chef.getPrenom());
            logger.log(Level.INFO,"Specialite : " + chef.getSpecialite());
            logger.log(Level.INFO,"Nombre d'etoiles : " + chef.getNbEtoiles() + " etoiles");
            logger.log(Level.INFO,"Nombre de plats realises : " + chef.getNbPlatsRealises());
        }
    }

    public void displayListeMembreJury(){
        for(MembreJury jury : this.juryConcours){
            logger.log(Level.INFO,"Nom du membre du jury : " + jury.getNom());
            logger.log(Level.INFO,"Prenom du membre du jury : " + jury.getPrenom());
            logger.log(Level.INFO,"Nombre de participations : " + jury.getNbParticipations() + " participations");
        }
    }

    public void displayListeParticipants(){
        for(Padawan participant : this.participantsConcours){
            logger.log(Level.INFO,"Nom du participant : " + participant.getNom());
            logger.log(Level.INFO,"Prenom du participant : " + participant.getPrenom());
            if(participant.getChefRef() != null){
                logger.log(Level.INFO,"Nom / Prénom de son chef referent : " + participant.getChefRef().getNom() + participant.getChefRef().getPrenom());
            }
        }
    }

    public void displayPlatBio(){
        for(Plat plat : this.listePlats){
            if(plat.isBio()){
                this.logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            }
        }
    }

    public String getDateDebutConcours(){
        return this.dateDebutConcours;
    }

    public String getDateFinConcours(){
        return this.dateFinConcours;
    }


}
