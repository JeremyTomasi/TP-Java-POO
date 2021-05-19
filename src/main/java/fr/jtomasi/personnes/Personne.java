package fr.jtomasi.personnes;

import fr.jtomasi.utilities.Utilities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personne implements Serializable {
    @Id
    protected String id = UUID.randomUUID().toString();
    protected String nom;
    protected String prenom;
    protected Genre genre;

    public Personne(){
        super();
    }
    public Personne(String nom,String prenom,Genre genre){
        this.nom = Utilities.ucfirst(nom);
        this.prenom = Utilities.ucfirst(prenom);
        this.genre = genre;
    }

    /**
     * Récupère l'identifiant de la personne
     * @return int
     */
    public String getId() {
        return id;
    }

    /**
     * Définit un nouvel identifiant
     * @param id Nouvel identifiant
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Récupère le nom de la personne
     * @return String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Change le nom de la personne
     * @param nom Nouveau nom de la personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom de la personne
     * @return String
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Change le prénom de la personne
     * @param prenom Nouveau prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère le genre de la personne
     * @return Genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Change le genre de la personne
     * @param genre Nouveau genre de la personne
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
