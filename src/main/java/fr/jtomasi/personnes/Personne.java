package fr.jtomasi.personnes;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;
    protected String nom;
    protected String prenom;
    protected Genre genre;

    public Personne(){
        super();
    }
    public Personne(String nom,String prenom,Genre genre){
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
    }

    /**
     * Récupère l'identifiant de la personne
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Définit un nouvel identifiant
     * @param id Nouvel identifiant
     */
    public void setId(int id) {
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
