package fr.jtomasi;

import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Concours {
    private boolean concoursDemarre = false;
    private boolean concoursTermine = false;

    private List<Chef> chefConcours = new ArrayList<>();
    private List<MembreJury> juryConcours = new ArrayList<>();
    private List<Padawan> participantsConcours = new ArrayList<>();
    private List<Plat> listePlats = new ArrayList<>();

    private String nomConcours;
    private String dateDebutConcours;
    private String dateFinConcours;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private ListeConcours listeConcours;

    public void demarrerConcours() throws NoNumberChefRequiredException, NoNumberMembreJuryRequiredException,NoParticipantsException {
        int minMembreJury = 3;
        int minChef = 5;
        if(chefConcours.size() < minChef){
            throw new NoNumberChefRequiredException("Il manque des chefs !!");
        } else if(juryConcours.size() < minMembreJury){
            throw new NoNumberMembreJuryRequiredException("Il manque des membres de jury !!");
        } else if(participantsConcours.size() == 0){
            throw new NoParticipantsException("Il n'y a pas de participants !!");
        } else {
            concoursDemarre = true;
            listeConcours.getConcoursPrevus().remove(this);
            listeConcours.addConcoursEnCours(this);
        }
    }

    public Padawan getWinnerConcours(){
        Plat platWithMaxNote = this.listePlats.get(0);
        for(Plat plat : this.listePlats){
            if(plat.getAuteurPlat() instanceof Padawan){
                if(plat.getNotePlat() > platWithMaxNote.getNotePlat()){
                    platWithMaxNote = plat;
                }
            }
        }
        return (Padawan) platWithMaxNote.getAuteurPlat();
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
            Padawan winner = this.getWinnerConcours();
            logger.log(Level.INFO,"Le gagnant est : " + winner.getNom() + " " + winner.getPrenom());
            listeConcours.getConcoursEnCours().remove(this);
            listeConcours.addConcoursTermine(this);
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

    public Concours(String nomConcours, String dateDebutConcours, String dateFinConcours, ListeConcours listeConcours){
        this.nomConcours = nomConcours;
        this.dateDebutConcours = dateDebutConcours;
        this.dateFinConcours = dateFinConcours;

        this.listeConcours = listeConcours;
        listeConcours.addConcoursPrevu(this);
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
                logger.log(Level.INFO,"Nom / Prenom de son chef referent : " + participant.getChefRef().getNom() + " " + participant.getChefRef().getPrenom());
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

    public boolean isConcoursDemarre(){
        return concoursDemarre;
    }

    public boolean isConcoursTermine() {
        return concoursTermine;
    }
}
