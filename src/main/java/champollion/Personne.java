
package champollion;

/**
 *  Une Personne    , classe abstraite, sera spécifiée par des classes concrètes comme Étudiant ou Enseignant
 */
public abstract class Personne {
    protected String nom;
    protected String prenom;
    protected String email;


    /**
     * Constructeur de la classe Personne.
     * @param nom le nom de la personne
     * @param email l'adresse email de la personne
     */
    protected Personne(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public Personne() {

    }

    /**
     * Retourne le nom de la personne.
     * @return le nom de la personne
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne l'adresse email de la personne.
     * @return l'adresse email de la personne
     */
    public String getEmail() {
        return email;
    }
}
