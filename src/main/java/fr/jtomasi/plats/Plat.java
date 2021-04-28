package fr.jtomasi.plats;

import fr.jtomasi.ingredients.Ingredient;
import fr.jtomasi.personnes.Chef;
import fr.jtomasi.personnes.Padawan;
import fr.jtomasi.personnes.Personne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPlat;
    private String nomPlat;
    private transient final List<Recette> listeIngredients = new ArrayList<>();
    private int notePlat = -1;
    private transient Personne auteurPlat;
    private String idAuteurPlat;
    private boolean bio = false;
    private double caloriesPlat;
    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    public Plat(String nomPlat, Padawan auteurPlat){
        this.nomPlat = nomPlat;
        this.auteurPlat = auteurPlat;
        this.idAuteurPlat = auteurPlat.getId();
    }

    public Plat(String nomPlat, Chef auteurPlat){
        this.nomPlat = nomPlat;
        this.auteurPlat = auteurPlat;
        this.idAuteurPlat = auteurPlat.getId();
    }

    public Plat(String nomPlat){
        this.nomPlat = nomPlat;
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
    public Personne getAuteurPlat(){
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
        }
    }

    /**
     * Calcule le nombre de calories du plat
    **/
    public void setCaloriesPlat(){
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
     * @return boolean
     */
    public boolean isBio() {
        int nbIngredientsBio = 0;
        for(Recette recette : this.listeIngredients){
            if(recette.getIngredient().isBio()){
                nbIngredientsBio++;
            }
        }
        bio = nbIngredientsBio == listeIngredients.size();
        return bio;
    }
}
