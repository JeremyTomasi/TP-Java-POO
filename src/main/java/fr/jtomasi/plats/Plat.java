package fr.jtomasi.plats;

import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.personnes.Personne;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Plat implements Serializable {

    @Id
    private String idPlat = UUID.randomUUID().toString();
    private String idConcours;
    private String nomPlat;
    @Transient
    private final List<Recette> listeIngredients = new ArrayList<>();
    private int notePlat = -1;
    @Transient
    private Padawan auteurPlat;
    private String idAuteurPlat;
    private boolean bio = false;
    private double caloriesPlat;
    private final transient Logger logger = Logger.getLogger(Plat.class.getName());

    public Plat(String nomPlat, Padawan auteurPlat){
        this.nomPlat = nomPlat;
        this.auteurPlat = auteurPlat;
        this.idAuteurPlat = auteurPlat.getId();
    }

    public Plat(){
        super();
    }

    /**
     * Permet d'ajouter un ingrédient dans la liste
     * @param ingredient L'ingrédient à ajouter
     * @param quantite La quantité
     * @param preparation La préparation de l'ingrédient
     */
    public void addIngredient(Ingredient ingredient, int quantite, String preparation){
        listeIngredients.add(new Recette(ingredient,quantite,preparation));
    }

    /**
     * Definit l'ID du concours
     * @param idConcours ID du concours
     */
    public void setIdConcours(String idConcours){
        this.idConcours = idConcours;
    }

    /**
     * Permet d'ajouter une recette
     * @param recette Recette à ajouter
     */
    public void addIngredient(Recette recette){
        listeIngredients.add(recette);
    }

    /**
     * Récupère la personne auteur du plat
     * @return Personne
     */
    public Padawan getAuteurPlat(){
        return auteurPlat;
    }

    /**
     * Permet de noter le plat
     * @param notePlat La note du plat
     */
    public void noterPlat(int notePlat){
        this.notePlat = notePlat;
    }

    /**
     * Retourne le nom du plat
     * @return String
     */
    public String getNomPlat(){
        return this.nomPlat;
    }


    /**
     * Récupère la note du plat
     * @return int
     */
    public int getNotePlat(){
        return this.notePlat;
    }

    /**
     * Récupère la liste des ingrédients
     * @return List<Ingredient>
     */
    public List<Recette> getListeIngredients(){
        return listeIngredients;
    }

    /**
     * Affiche la liste des ingrédients
     */
    public void displayIngredients(){
        for(Recette recette : listeIngredients){
            logger.log(Level.INFO,"Nom de l'ingredient : " + recette.getIngredient().getNom());
            logger.log(Level.INFO,"Quantite : " + recette.getQuantite());
            logger.log(Level.INFO,"Preparation : " + recette.getPreparation());
            logger.log(Level.INFO,"Bio : "+ recette.getIngredient().isBio());
        }
    }

    /**
     * Calcule le nombre de calories du plat
    **/
    public void calculCaloriesPlat(){
        for(Recette recette : listeIngredients){
            caloriesPlat += recette.getQuantite() * recette.getIngredient().getCalories();
        }
    }

    /**
     * Renvoie le nombre de calories du plat
     * @return double
     */
    public double getCaloriesPlat() {
        return caloriesPlat;
    }

    /**
     * Permet de savoir si le plat est bio
     */
    public void determinerBio() {
        int nbIngredientsBio = 0;
        for(Recette recette : this.listeIngredients){
            if(recette.getIngredient().isBio()){
                nbIngredientsBio++;
            }
        }
        bio = (nbIngredientsBio == listeIngredients.size());
    }

    /**
     * Permet de savoir si le plat est bio ou pas
     * @return Un booléen qui dit si le plat est bio ou pas
     */
    public boolean isBio(){
        return bio;
    }

    /**
     * Permet de savoir si le plat est noté ou pas
     * @return Un booléen qui dit si le plat est noté ou pas
     */
    public boolean isNote(){
        return notePlat != -1;
    }
}
