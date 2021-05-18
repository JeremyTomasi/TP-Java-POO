package fr.jtomasi.concours;

import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.MembreJury;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.plats.Plat;
import fr.jtomasi.plats.Recette;
import fr.jtomasi.personnes.Personne;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
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
        this.concoursTermines.add(concours);
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

    /**
     * Sauvegarde la liste des concours ainsi que leurs infos relatives
     */
    public void saveBdd(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bdd");
        EntityManager em = emf.createEntityManager();

        // Sauvegarde concours
        writeBdd(em,this.concoursPrevus);
        writeBdd(em,this.concoursEnCours);
        writeBdd(em,this.concoursTermines);

        em.close();
        emf.close();
    }

    /**
     * Permet de sauvegarder en base de données un tableau de concours donné
     * @param em L'Entity Manager
     * @param tabConcours Le tableau de concours à sauvegarder
     */
    private void writeBdd(EntityManager em, List<Concours> tabConcours ){
        for(Concours c: tabConcours){

            for(Chef p: c.getChefConcours()){
                for(Padawan padawan : p.getPadawans()){
                    em.getTransaction().begin();
                    em.persist(padawan);
                    em.getTransaction().commit();
                }
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            }

            for(MembreJury p: c.getMembreJuryConcours()){
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            }

            for(Plat plat : c.getListePlats()){
                for (Recette recette : plat.getListeIngredients()){
                    em.getTransaction().begin();
                    em.persist(recette.getIngredient());
                    em.persist(recette);
                    em.getTransaction().commit();
                }
                em.getTransaction().begin();
                em.persist(plat);
                em.getTransaction().commit();
            }
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }
    }

    /**
     * Permet d'afficher la liste des concours auquel le chef passé en paramètre est inscrit
     * @param chef Le chef dont on veut vérifier ses participations aux concours
     */
    public void listeConcoursChefInscrit(Chef chef){
        logger.log(Level.INFO,"Liste des concours auquel le chef " + chef.getNom() + " " + chef.getPrenom());
        for(Concours concours : this.concoursEnCours){
            for(Chef chefInscrits : concours.getListeChefs()){
                logger.log(Level.INFO,"Nom du concours : "  + concours.getNomConcours());
            }
        }
    }

    /**
     * Permet d'afficher tous les ingrédients composant les différents plats des différents concours en cours et terminés
     */
    public void afficherIngredientsConnus(){
        logger.log(Level.INFO,"Affichage de la liste des ingrédients connus : \n");
        for(Concours c : this.concoursEnCours){
            for(Plat plat : c.getListePlats()){
                for(Recette recette : plat.getListeIngredients()){
                    logger.log(Level.INFO,"Nom de l'ingredient : " + recette.getIngredient().getNom());
                }
            }
        }

        for(Concours c : this.concoursTermines){
            for(Plat plat : c.getListePlats()){
                for(Recette recette : plat.getListeIngredients()){
                    logger.log(Level.INFO,"Nom de l'ingredient : " + recette.getIngredient().getNom());
                }
            }
        }
    }


    /**
     * Permet de sauvegarder la liste des ingrédients dans un fichier JSON
     */
    public void saveIngredientsJson(String nomFichier){
        Jsonb jb = JsonbBuilder.create();
        List<Recette> recettes = new ArrayList<>();

        for(Concours c : this.getConcoursEnCours()){
            for(Plat p: c.getListePlats()){
                recettes.addAll(p.getListeIngredients());
            }
        }

        for(Concours c : this.getConcoursTermines()){
            for(Plat p : c.getListePlats()){
                recettes.addAll(p.getListeIngredients());
            }
        }

        String convert = jb.toJson(recettes);
        try {
            FileWriter writer = new FileWriter(nomFichier);
            writer.write(convert);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        logger.log(Level.INFO,convert);
    }

    public void saveCuisine(){
        File saveFile = new File("cuisine.ser");
        try {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(Concours concours : this.concoursEnCours){
                for(Plat plat : concours.getListePlats()){
                    for(Recette recette : plat.getListeIngredients()){
                        oos.writeObject(recette);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
