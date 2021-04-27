package fr.jtomasi.concours;

import fr.jtomasi.plats.Plat;
import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.exceptions.TousPlatsNonNotesException;
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

    private final List<Chef> chefConcours = new ArrayList<>();
    private final List<MembreJury> juryConcours = new ArrayList<>();
    private final List<Padawan> participantsConcours = new ArrayList<>();
    private final List<Plat> listePlats = new ArrayList<>();

    private final String nomConcours;
    private final String dateDebutConcours;
    private final String dateFinConcours;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final ListeConcours listeConcours;

    /**
     * Démarre le concours si les conditions sont requises
     * @throws NoNumberChefRequiredException S'il n'y a pas assez de chefs
     * @throws NoNumberMembreJuryRequiredException S'il n'y a pas assez de membre du jury
     * @throws NoParticipantsException S'il n'y a pas de participants
     */
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

    /**
     * Récupère le gagnant du concours
     * @return Padawan
     */
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

    /**
     * Finit le concours si tous les plats ont été notés
     * @throws TousPlatsNonNotesException Si tous les plats n'ont pas été notés
     */
    public void finirConcours() throws TousPlatsNonNotesException {
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
        } else {
            throw new TousPlatsNonNotesException("Tous les plats n'ont pas ete notes !");
        }
    }


    /**
     * Ajoute un membre de jury au concours
     * @param membreJury Membre à ajouter au concours
     */
    public void addMembreJuryConcours(MembreJury membreJury){
        this.juryConcours.add(membreJury);
        membreJury.ajouterParticipationConcours(this);
    }

    /**
     * Ajoute un chef au concours
     * @param chef Chef à ajouter au concours
     */
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

    /**
     * Récupère le nom du concours
     * @return String
     */
    public String getNomConcours(){
        return this.nomConcours;
    }

    /**
     * Ajoute un nouvel participant au concours
     * @param padawan Participant à ajouter au concours
     */
    public void addParticipant(Padawan padawan){
        this.participantsConcours.add(padawan);
    }

    /**
     * Ajoute un plat au concours
     * @param plat Plat à ajouter au concours
     */
    public void addPlatConcours(Plat plat){
        this.listePlats.add(plat);
    }

    /**
     * Récupère la liste des plats
     * @return List<Plat>
     */
    public List<Plat> getListePlats(){
        return this.listePlats;
    }

    /**
     * Affiche la liste des plats et leurs ingrédients
     */
    public void displayListePlats(){
        for(Plat plat : this.getListePlats()){
            logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            plat.displayIngredients();
        }
    }

    /**
     * Affiche la liste des chefs
     */
    public void displayListeChefs(){
        for(Chef chef : this.chefConcours){
            logger.log(Level.INFO,"Nom du chef : " + chef.getNom());
            logger.log(Level.INFO,"Prenom du chef : " + chef.getPrenom());
            logger.log(Level.INFO,"Specialite : " + chef.getSpecialite());
            logger.log(Level.INFO,"Nombre d'etoiles : " + chef.getNbEtoiles() + " etoiles");
            logger.log(Level.INFO,"Nombre de plats realises : " + chef.getNbPlatsRealises());
        }
    }

    /**
     * Affiche la liste des membres du jury
     */
    public void displayListeMembreJury(){
        for(MembreJury jury : this.juryConcours){
            logger.log(Level.INFO,"Nom du membre du jury : " + jury.getNom());
            logger.log(Level.INFO,"Prenom du membre du jury : " + jury.getPrenom());
            logger.log(Level.INFO,"Nombre de participations : " + jury.getNbParticipations() + " participations");
        }
    }

    /**
     * Affiche la liste des participants
     */
    public void displayListeParticipants(){
        for(Padawan participant : this.participantsConcours){
            logger.log(Level.INFO,"Nom du participant : " + participant.getNom());
            logger.log(Level.INFO,"Prenom du participant : " + participant.getPrenom());
            if(participant.getChefRef() != null){
                logger.log(Level.INFO,"Nom / Prenom de son chef referent : " + participant.getChefRef().getNom() + " " + participant.getChefRef().getPrenom());
            }
        }
    }

    /**
     * Affiche tous les plats bio
     */
    public void displayPlatBio(){
        for(Plat plat : this.listePlats){
            if(plat.isBio()){
                this.logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            }
        }
    }

    /**
     * Récupère la date de début du concours
     * @return String
     */
    public String getDateDebutConcours(){
        return this.dateDebutConcours;
    }

    /**
     * Récupère la date de fin du concours
     * @return String
     */
    public String getDateFinConcours(){
        return this.dateFinConcours;
    }

    /**
     * Permet de savoir si le concours à démarré
     * @return boolean
     */
    public boolean isConcoursDemarre(){
        return concoursDemarre;
    }

    /**
     * Permet de savoir si le concours est terminé
     * @return boolean
     */
    public boolean isConcoursTermine() {
        return concoursTermine;
    }

    public List<Chef> getChefConcours(){
        return this.chefConcours;
    }

    public List<MembreJury> getMembreJuryConcours(){
        return this.juryConcours;
    }

    public List<Padawan> getParticipants(){
        return this.participantsConcours;
    }
}
