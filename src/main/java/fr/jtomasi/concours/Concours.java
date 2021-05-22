package fr.jtomasi.concours;

import fr.jtomasi.exceptions.*;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.utilities.Utilities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Concours implements Serializable, Comparable<Concours> {
    private boolean concoursDemarre = false;
    private boolean concoursTermine = false;

    @Transient
    private final List<Chef> chefConcours = new ArrayList<>();

    @Transient
    private final List<MembreJury> juryConcours = new ArrayList<>();

    @Transient
    private final List<Plat> listePlats = new ArrayList<>();

    @Id
    private final String idConcours = UUID.randomUUID().toString();
    private String nomConcours;
    private LocalDate dateDebutConcours;
    private LocalDate dateFinConcours;

    private static final Logger logger = Logger.getLogger(Concours.class.getName());

    @Transient
    private ListeConcours listeConcours;

    public Concours(){
        super();
    }

    public Concours(String nomConcours, String dateDebutConcours, ListeConcours listeConcours){
        this.nomConcours = nomConcours;
        this.dateDebutConcours = Utilities.generateDate(dateDebutConcours);

        this.listeConcours = listeConcours;
        listeConcours.addConcoursPrevu(this);
    }

    /**
     * Démarre le concours si les conditions sont requises
     * @throws NoNumberChefRequiredException S'il n'y a pas assez de chefs
     * @throws NoNumberMembreJuryRequiredException S'il n'y a pas assez de membre du jury
     * @throws NoParticipantsException S'il n'y a pas de participants
     */
    public void demarrerConcours() throws Exception {
        int minMembreJury = 3;
        int minChef = 5;
        int nbPadawans = 0;

        // on compte le nombre de Padawans au total
        for (Chef chefInscrit : this.chefConcours) {
            for (Padawan padawan : chefInscrit.getPadawans()) {
                nbPadawans++;
            }
        }

        // si on a pas assez de chefs
        if (chefConcours.size() < minChef) {
            throw new NoNumberChefRequiredException("Il manque des chefs !!");
        }
        // si on a pas assez de membres de jury
        else if (juryConcours.size() < minMembreJury) {
            throw new NoNumberMembreJuryRequiredException("Il manque des membres de jury !!");
        }
        // s'il n'y a pas de participants
        else if (nbPadawans == 0) {
            throw new NoParticipantsException("Il n'y a pas de participants !");
        }
        //si le concours précédent n'est pas terminé
        else if (listeConcours.getConcoursEnCours() != null && !(listeConcours.getConcoursEnCours().isConcoursTermine())) {
            throw new ConcoursNotFinishedException("Le concours precedent n'est pas termine !!");
        } else {
            concoursDemarre = true;
            listeConcours.setConcoursEnCours(this);
        }

    }

    /**
     * Récupère le gagnant du concours
     * @return Padawan
     */
    public Chef getWinnerConcours(){
        // on récupère la note du premier plat
        Plat platWithMaxNote = this.listePlats.get(0);
        //
        for(Plat plat : this.listePlats){
                // si la note du plat est supérieure à la note du plat avec la plus haute note
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
            // On vérifie si le plat a été noté
            if(plat.isNote()){
                nbPlatsNotes++;
            }
        }

        if(nbPlatsNotes == listePlats.size()){
            // On termine le concours
            this.concoursTermine = true;
            // On récupère le chef gagnant du concours
            chefGagnant = this.getWinnerConcours();
            // On ajoute une victoire au chef gagnant
            chefGagnant.ajouterVictoire();
            logger.log(Level.INFO,"Le chef gagnant est : " + chefGagnant.getNom() + " " + chefGagnant.getPrenom());
            listeConcours.addConcoursTermine(this);
            this.dateFinConcours = LocalDate.now();

            // On détermine le Padawan le plus ancien
            for(Padawan padawan : chefGagnant.getPadawans()){
                long days = ChronoUnit.DAYS.between(padawan.getDateNaissance(), LocalDate.now());
                if(days > nombreJoursPasses){
                    doyen = padawan;
                }
            }

            if(doyen != null){
                nouveauChef = new Chef(doyen.getNom(),doyen.getPrenom(),doyen.getGenre(),doyen.getTelephone(),chefGagnant.getSpecialite(),getNbPlatsRealisesPadawan(doyen));
            }
            //si le nombre de victoires est supérieur ou égal à 1
            if(chefGagnant.getNbVictoires() >= 1){
                chefGagnant.ajouterEtoiles(1);
            }
            //si le nombre de victoires est supérieur ou égal à 3
            else if(chefGagnant.getNbVictoires() >= 3){
                chefGagnant.ajouterEtoiles(2);
            }
            //si le nombre de victoires est supérieur ou égal à 5
            else if(chefGagnant.getNbVictoires() >= 5){
                chefGagnant.ajouterEtoiles(3);
            }
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
        // si la liste des membres du jury est vide
        if(this.juryConcours.size() == 0){
            this.juryConcours.add(membreJury);
            membreJury.ajouterParticipationConcours(this);
        } else {
            boolean membreJuryTrouve = false;
            // On vérifie pour chaque membre du jury déja présent si ce n'est pas le même
            for(MembreJury membreJuryInscrit : this.juryConcours){
                if (membreJuryInscrit.getNom().equals(membreJury.getNom())) {
                    membreJuryTrouve = true;
                    break;
                }
            }
            // si l'on a pas trouvé, on l'ajoute dans la liste des membres du jury
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
        // si la liste des chefs est vide, on l'ajoute dans la liste
        if(this.chefConcours.size() == 0){
            this.chefConcours.add(chef);
            chef.addParticipationConcours(this);
        } else {
            boolean chefTrouve = false;
            // On vérifie pour chaque chef dans la liste si ce n'est pas le même
            for(Chef chefInscrit : this.chefConcours){
                if(chefInscrit.getNom().equals(chef.getNom())){
                    chefTrouve = true;
                    break;
                }
            }
            // si on l'a pas trouvé, on l'ajoute à la liste
            if(!chefTrouve){
                this.chefConcours.add(chef);
                chef.addParticipationConcours(this);
            }
        }
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
        // On détermine si le plat est bio ou pas
        plat.determinerBio();
        // On calcule les calories du plat
        plat.calculCaloriesPlat();
        // On définit l'identifiant du concours au plat
        plat.setIdConcours(this.getIdConcours());
        // On ajoute le plat dans la liste
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
                logger.log(Level.INFO,"Nom du plat : " + plat.getNomPlat());
            }
        }
    }

    /**
     * Récupère la date de début du concours
     * @return String
     */
    public LocalDate getDateDebutConcours(){
        return this.dateDebutConcours;
    }

    /**
     * Récupère la date de fin du concours
     * @return String
     */
    public LocalDate getDateFinConcours(){
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
        // On compte tous les plats réalisés par le padawan
        for(Plat plat : this.listePlats){
            if(plat.getAuteurPlat().equals(padawan)){
                nbPlatsRealises++;
            }
        }
        return nbPlatsRealises;
    }

    @Override
    public int compareTo(Concours o) {
        return this.dateDebutConcours.compareTo(o.getDateDebutConcours());
    }

    /**
     * Retourne l'ID du concours
     * @return L'ID du concours
     */
    public String getIdConcours(){
        return this.idConcours;
    }
}
