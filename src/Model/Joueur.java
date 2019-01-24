package Model;

/**
 * The type Joueur.
 */
public class Joueur {
    private int id;
    private String nom;
    private Royaume royaume;
    private boolean estJoueurActuel;
    /**
     * The Score.
     */
    int score;

    /**
     * Instantiates a new Joueur.
     *
     * @param nom    the nom
     * @param id     the id
     * @param taille the taille
     */
    public Joueur(String nom, int id, int taille) {
        this.id = id;
        this.nom = nom;
        estJoueurActuel = false;
        royaume = new Royaume(taille);
        score = 0;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets royaume.
     *
     * @return the royaume
     */
    public Royaume getRoyaume() {
        return royaume;
    }

    /**
     * Sets royaume.
     *
     * @param royaume the royaume
     */
    public void setRoyaume(Royaume royaume) {
        this.royaume = royaume;
    }

    /**
     * Is est joueur actuel boolean.
     *
     * @return the boolean
     */
    public boolean isEstJoueurActuel() {
        return estJoueurActuel;
    }

    /**
     * Sets est joueur actuel.
     *
     * @param estJoueurActuel the est joueur actuel
     */
    public void setEstJoueurActuel(boolean estJoueurActuel) {
        this.estJoueurActuel = estJoueurActuel;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
