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

    public void addConcoursPrevu(Concours concours){
        this.concoursPrevus.add(concours);
    }

    public void addConcoursEnCours(Concours concours){
        if(concours.isConcoursDemarre()){
            this.concoursEnCours.add(concours);
        }
    }

    public void addConcoursTermine(Concours concours){
        if(concours.isConcoursTermine()){
            this.concoursTermines.add(concours);
        }
    }

    public List<Concours> getConcoursPrevus() {
        return concoursPrevus;
    }

    public List<Concours> getConcoursEnCours(){
        return concoursEnCours;
    }

    public List<Concours> getConcoursTermines(){
        return concoursTermines;
    }

    public void displayListeConcoursPrevus(){
        for(Concours c : this.concoursPrevus){
            logger.log(Level.INFO,"Nom du concours : " + c.getNomConcours());
            logger.log(Level.INFO,"Date début concours : " + c.getDateDebutConcours());
            logger.log(Level.INFO, "Date fin concours : " + c.getDateFinConcours());
        }
    }

    public void displayListeConcoursEnCours(){
        for(Concours c : this.concoursEnCours){
            logger.log(Level.INFO,"Nom du councours : " + c.getNomConcours());
            logger.log(Level.INFO,"Date début concours : " + c.getDateDebutConcours());
            logger.log(Level.INFO, "Date fin concours : " + c.getDateFinConcours());
        }
    }

    public void displayListeConcoursTermines(){
        for(Concours c : this.concoursTermines){
            logger.log(Level.INFO,"Nom du councours : " + c.getNomConcours());
            logger.log(Level.INFO,"Date début concours : " + c.getDateDebutConcours());
            logger.log(Level.INFO, "Date fin concours : " + c.getDateFinConcours());
        }
    }
}
