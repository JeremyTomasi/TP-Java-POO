package fr.jtomasi.personnes;

import fr.jtomasi.Concours;

import java.util.ArrayList;
import java.util.List;

public class MembreJury extends Personne{

    private int nbParticipations;

    private List<Concours> participationsConcours = new ArrayList<>();

    public MembreJury(int id, String nom, String prenom, Genre genre, int nbParticipations) {
        super(id, nom, prenom, genre);
        this.nbParticipations = nbParticipations;
    }

    public int getNbParticipations() {
        return nbParticipations;
    }

    public void setNbParticipations(int nbParticipations) {
        this.nbParticipations = nbParticipations;
    }

    public void ajouterParticipationConcours(Concours concours){
        this.participationsConcours.add(concours);
        this.nbParticipations++;
    }
}
