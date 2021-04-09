package fr.jtomasi;

import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;

import java.util.ArrayList;
import java.util.List;

public class Concours {
    private int minChef = 5;
    private int minMembreJury = 3;

    private List<Chef> chefConcours = new ArrayList<>();
    private List<MembreJury> juryConcours = new ArrayList<>();
    private List<Padawan> participantsConcours = new ArrayList<>();
    private List<Concours> concoursEnCours = new ArrayList<>();
    private List<Concours> concoursTermines = new ArrayList<>();

    private String nomConcours;
    private String dateDebutConcours;
    private String dateFinConcours;



    public void addMembreJuryConcours(MembreJury membreJury){
        this.juryConcours.add(membreJury);
        membreJury.ajouterParticipationConcours(this);
    }

    public void addChefConcours(Chef chef){
        this.chefConcours.add(chef);
        chef.addParticipationConcours(this);
    }

    public Concours(String nomConcours, String dateDebutConcours, String dateFinConcours){
        this.nomConcours = nomConcours;
        this.dateDebutConcours = dateDebutConcours;
        this.dateFinConcours = dateFinConcours;
    }

    public void addParticipant(Padawan padawan){
        this.participantsConcours.add(padawan);
    }



}
