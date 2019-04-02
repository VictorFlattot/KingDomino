package Model;

/**
 * The type Joueur.
 */
public class Joueur {

    /**
     * Valeur entière unique pour chaque joueur
     */
    private int id;

    /**
     * Chiane de caractères representant le nom du joueur
     */
    private String nom;

    /**
     * Le royaume du joueur
     *
     * @see Royaume
     */
    private Royaume royaume;

    /**
     * Définit si c'est le tour d'un joueur
     */
    private boolean estJoueurActuel;

    /**
     * Associe une couleur à un joueur
     *
     * @see Couleur
     */
    private Couleur couleur;

    /**
     * Le score du joueur
     *
     * @see ModelTest#calculScore(Joueur)
     */
    private int score;

    /**
     * Vrai si un joueur est une IA
     */
    private boolean IA;

    /**
     * Instantiates a new Joueur.
     *
     * @param nom     the nom
     * @param id      the id
     * @param taille  the taille
     * @param couleur the couleur
     * @param IA      the ia
     */
    public Joueur(String nom, int id, int taille, Couleur couleur,boolean IA) {
        this.id = id;
        this.nom = nom;
        estJoueurActuel = false;
        royaume = new Royaume(taille);
        score = 0;
        this.couleur = couleur;
        this.IA = IA;
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

    /**
     * Gets couleur.
     *
     * @return the couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Sets couleur.
     *
     * @param couleur the couleur
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Is ia boolean.
     *
     * @return the boolean
     */
    public boolean isIA() {
        return IA;
    }

    /**
     * Sets ia.
     *
     * @param IA the ia
     */
    public void setIA(boolean IA) {
        this.IA = IA;
    }
}
