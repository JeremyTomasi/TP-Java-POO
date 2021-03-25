package fr.jtomasi.personnes;

public class MembreJury extends Personne{

    private int nbParticipations;

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
}
