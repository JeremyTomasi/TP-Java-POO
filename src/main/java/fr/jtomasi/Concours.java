package fr.jtomasi;

import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;

import java.util.ArrayList;
import java.util.List;

public class Concours {
    private int minChef = 5;
    private int minMembreJury = 3;
    private List<Chef> chefConcours = new ArrayList<>();
    private List<MembreJury> juryConcours = new ArrayList<>();
    private String nomConcours;
    private String dateDebutConcours;


    public void addMembreJuryConcours(MembreJury membreJury){
        this.juryConcours.add(membreJury);
        membreJury.ajouterParticipationConcours(this);
    }

    public void addChefConcours(Chef chef){
        this.chefConcours.add(chef);
        chef.addParticipationConcours(this);
    }

    public Concours(String nomConcours, String dateDebutConcours){
        this.nomConcours = nomConcours;
        this.dateDebutConcours = dateDebutConcours;
    }

}
