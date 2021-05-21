package fr.jtomasi.personnes;

import fr.jtomasi.concours.Concours;
import fr.jtomasi.utilities.Utilities;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class MembreJury extends Personne implements Serializable {

    private int nbParticipations;

    @Transient
    private final List<Concours> participationsConcours = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(MembreJury.class.getName());

    public MembreJury(){
        super();
    }

    public MembreJury( String nom, String prenom, Genre genre, int nbParticipations) {
        super(nom, prenom, genre);
        this.nbParticipations = nbParticipations;
    }

    /**
     * Récupère le nombre de participations à un concours en tant que jury
     * @return int
     */
    public int getNbParticipations() {
        return nbParticipations;
    }

    /**
     * Ajoute une nouvelle participation à son compteur
     */
    public void addParticipation() {
        this.nbParticipations++;
    }

    /**
     * Ajoute la participation du membre du jury au concours
     * @param concours Le concours auquel il participe en tant que membre du jury
     */
    public void ajouterParticipationConcours(Concours concours){
        this.participationsConcours.add(concours);
        this.nbParticipations++;
    }

    public void displayParticipations(){
        for(Concours concours : participationsConcours){
            logger.log(Level.INFO,"Nom du concours : " + concours.getNomConcours());
            logger.log(Level.INFO,"Date de debut du concours : " + Utilities.displayDate(concours.getDateDebutConcours()));
        }
    }
}
