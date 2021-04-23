package fr.jtomasi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListeConcours {

    private final List<Concours> concoursPrevus = new ArrayList<>();
    private final List<Concours> concoursEnCours = new ArrayList<>();
    private final List<Concours> concoursTermines = new ArrayList<>();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Ajout un concours à la liste des concours prévus
     * @param concours Concours prévu
     */
    public void addConcoursPrevu(Concours concours){
        this.concoursPrevus.add(concours);
    }

    /**
     * Ajoute un concours à la liste des concours en cours
     * @param concours Concours en cours
     */
    public void addConcoursEnCours(Concours concours){
        if(concours.isConcoursDemarre()){
            this.concoursEnCours.add(concours);
        }
    }

    /**
     * Ajoute un concours à la liste des concours terminés
     * @param concours Concours terminé
     */
    public void addConcoursTermine(Concours concours){
        if(concours.isConcoursTermine()){
            this.concoursTermines.add(concours);
        }
    }

    /**
     * Récupère la liste des concours prévus
     * @return List<Concours>
     */
    public List<Concours> getConcoursPrevus() {
        return concoursPrevus;
    }

    /**
     * Récupère la liste des concours en cours
     * @return List<Concours>
     */
    public List<Concours> getConcoursEnCours(){
        return concoursEnCours;
    }

    /**
     * Récupère la liste des concours terminés
     * @return List<Concours>
     **/
    public List<Concours> getConcoursTermines(){
        return concoursTermines;
    }

    /**
     * Affiche la liste des concours prévus
     */
    public void displayListeConcoursPrevus(){
        for(Concours c : this.concoursPrevus){
            logger.log(Level.INFO,"Nom du concours : " + c.getNomConcours());
            logger.log(Level.INFO,"Date début concours : " + c.getDateDebutConcours());
            logger.log(Level.INFO, "Date fin concours : " + c.getDateFinConcours());
        }
    }

    /**
     * Affiche la liste des concours en cours
     */
    public void displayListeConcoursEnCours(){
        for(Concours c : this.concoursEnCours){
            logger.log(Level.INFO,"Nom du councours : " + c.getNomConcours());
            logger.log(Level.INFO,"Date début concours : " + c.getDateDebutConcours());
            logger.log(Level.INFO, "Date fin concours : " + c.getDateFinConcours());
        }
    }

    /**
     * Affiche la liste des concours terminés
     */
    public void displayListeConcoursTermines(){
        for(Concours c : this.concoursTermines){
            logger.log(Level.INFO,"Nom du councours : " + c.getNomConcours());
            logger.log(Level.INFO,"Date début concours : " + c.getDateDebutConcours());
            logger.log(Level.INFO, "Date fin concours : " + c.getDateFinConcours());
        }
    }
}
