package fr.jtomasi.personnes;

import fr.jtomasi.Concours;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MembreJury extends Personne{

    private int nbParticipations;

    private transient List<Concours> participationsConcours = new ArrayList<>();

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
}
