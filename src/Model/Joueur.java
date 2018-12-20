package Model;

public class Joueur {
    private int id;
    private String nom;
    private Royaume royaume;
    private boolean estJoueurActuel;
    int score;

    public Joueur(String nom,int id) {
        this.id = id;
        this.nom = nom;
        estJoueurActuel = false;
        royaume = new Royaume(5);
        score = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Royaume getRoyaume() {
        return royaume;
    }

    public void setRoyaume(Royaume royaume) {
        this.royaume = royaume;
    }

    public boolean isEstJoueurActuel() {
        return estJoueurActuel;
    }

    public void setEstJoueurActuel(boolean estJoueurActuel) {
        this.estJoueurActuel = estJoueurActuel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
