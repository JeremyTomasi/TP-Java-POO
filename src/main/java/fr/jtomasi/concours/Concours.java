package fr.jtomasi.concours;

import fr.jtomasi.plats.Plat;
import fr.jtomasi.exceptions.NoNumberChefRequiredException;
import fr.jtomasi.exceptions.NoNumberMembreJuryRequiredException;
import fr.jtomasi.exceptions.NoParticipantsException;
import fr.jtomasi.exceptions.TousPlatsNonNotesException;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static fr.jtomasi.utilities.Utilities.ucfirst;

public class Concours {
    private boolean concoursDemarre = false;
    private boolean concoursTermine = false;

    private final List<Chef> chefConcours = new ArrayList<>();
    private final List<MembreJury> juryConcours = new ArrayList<>();
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
        int nbPadawans = 0;
        for(Chef chefInscrit : this.chefConcours){
            for(Padawan padawan : chefInscrit.getPadawans()){
                nbPadawans++;
            }
        }
        if(chefConcours.size() < minChef){
            throw new NoNumberChefRequiredException("Il manque des chefs !!");
        } else if(juryConcours.size() < minMembreJury){
            throw new NoNumberMembreJuryRequiredException("Il manque des membres de jury !!");
        } else if(nbPadawans == 0) {
            throw new NoParticipantsException("Il n'y a pas de participants !");
        } else {
            concoursDemarre = true;
            listeConcours.addConcoursEnCours(this);
        }
    }

    /**
     * Récupère le gagnant du concours
     * @return Padawan
     */
    public Chef getWinnerConcours(){
        Plat platWithMaxNote = this.listePlats.get(0);
        for(Plat plat : this.listePlats){
                if(plat.getNotePlat() > platWithMaxNote.getNotePlat()){
                    platWithMaxNote = plat;
            }
        }
        return platWithMaxNote.getAuteurPlat().getChefRef();
    }

    /**
     * Finit le concours si tous les plats ont été notés
     * @throws TousPlatsNonNotesException Si tous les plats n'ont pas été notés
     */
    public Chef finirConcours() throws TousPlatsNonNotesException {
        Chef nouveauChef = null;
        Chef chefGagnant;
        int nombreJoursPasses = 0;
        Padawan doyen = null;

        int nbPlatsNotes = 0;
        for(Plat plat : this.listePlats){
            if(plat.isNote()){
                nbPlatsNotes++;
            }
        }

        if(nbPlatsNotes == listePlats.size()){
            this.concoursTermine = true;
            chefGagnant = this.getWinnerConcours();
            chefGagnant.ajouterVictoire();
            logger.log(Level.INFO,"Le gagnant est : " + chefGagnant.getNom() + " " + chefGagnant.getPrenom());
            listeConcours.addConcoursTermine(this);
            listeConcours.getConcoursEnCours().remove(this);

            /*
            for(Padawan padawan : chefGagnant.getPadawans()){
                long days = ChronoUnit.DAYS.between(padawan.getDateNaissance(),LocalDate.now());
                if(days > nombreJoursPasses){
                    doyen = padawan;
                }
            }
             */
        } else {
            throw new TousPlatsNonNotesException("Tous les plats n'ont pas ete notes !");
        }
        return nouveauChef;
    }


    /**
     * Ajoute un membre de jury au concours
     * @param membreJury Membre à ajouter au concours
     */
    public void addMembreJuryConcours(MembreJury membreJury){
        membreJury.setPrenom(ucfirst(membreJury.getPrenom()));
        membreJury.setNom(ucfirst(membreJury.getNom()));
        if(this.juryConcours.size() == 0){
            this.juryConcours.add(membreJury);
            membreJury.ajouterParticipationConcours(this);
        } else {
            boolean membreJuryTrouve = false;
            for(MembreJury membreJuryInscrit : this.juryConcours){
                if(membreJuryInscrit.getNom().equals(membreJury.getNom())){
                    membreJuryTrouve = true;
                }
            }
            if(!membreJuryTrouve){
                this.juryConcours.add(membreJury);
                membreJury.ajouterParticipationConcours(this);
            }
        }
    }


    /**
     * Ajoute un chef au concours
     * @param chef Chef à ajouter au concours
     */
    public void addChefConcours(Chef chef){
        chef.setPrenom(ucfirst(chef.getPrenom()));
        chef.setNom(ucfirst(chef.getNom()));
        if(this.chefConcours.size() == 0){
            this.chefConcours.add(chef);
            chef.addParticipationConcours(this);
        } else {
            boolean chefTrouve = false;
            for(Chef chefInscrit : this.chefConcours){
                if(chefInscrit.getNom().equals(chef.getNom())){
                    chefTrouve = true;
                    break;
                }
            }
            if(!chefTrouve){
                this.chefConcours.add(chef);
                chef.addParticipationConcours(this);
            }
        }
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
     * Ajoute un plat au concours
     * @param plat Plat à ajouter au concours
     */
    public void addPlatConcours(Plat plat){
        plat.determinerBio();
        plat.calculCaloriesPlat();
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
        logger.log(Level.INFO,"Affichage de la liste des plats : \n");
        for(Plat plat : this.getListePlats()){
            logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            logger.log(Level.INFO,"Calories du plat : " + plat.getCaloriesPlat());
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

    /**
     * Renvoie la liste des chefs inscrits au concours
     * @return La liste des chefs inscrits au concours
     */
    public List<Chef> getChefConcours(){
        return this.chefConcours;
    }

    /**
     * Renvoie la liste des membres du jury du concours
     * @return La liste des membres du jury du concours
     */
    public List<MembreJury> getMembreJuryConcours(){
        return this.juryConcours;
    }

    /**
     * Affiche le classement
     */
    public void displayClassement(){
        for(Plat p : this.listePlats){
            logger.log(Level.INFO,"Nom du plat : " + p.getNomPlat());
            logger.log(Level.INFO,"Nom de l'auteur du plat : " + p.getAuteurPlat().getNom() + " " + p.getAuteurPlat().getPrenom());
            logger.log(Level.INFO,"Nom du chef référent de l'auteur du plat : " + p.getAuteurPlat().getChefRef().getNom() + " " + p.getAuteurPlat().getChefRef().getPrenom());
            if(p.getNotePlat() != -1){
                logger.log(Level.INFO,"Note du plat : " + p.getNotePlat() + "\n");
            } else {
                logger.log(Level.INFO,"Ce plat n'a pas encore ete note !\n");
            }
        }
    }


    /**
     * Renvoie la liste des chefs inscrits au concours
     * @return La liste des chefs inscrits au concours
     */
    public List<Chef> getListeChefs() {
        return this.chefConcours;
    }

    /**
     * Renvoie le nombre de plats réalisés par un Padawan
     * @param padawan Le padawan dont on veut connaître le nombre de plats réalisés
     * @return Le nombre de plats réalisés
     */
    public int getNbPlatsRealisesPadawan(Padawan padawan){
        int nbPlatsRealises = 0;
        for(Plat plat : this.listePlats){
            if(plat.getAuteurPlat().equals(padawan)){
                nbPlatsRealises++;
            }
        }
        return nbPlatsRealises;
    }
}
