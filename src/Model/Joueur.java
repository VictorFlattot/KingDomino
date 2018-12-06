package Model;

public class Joueur {

    private String nom;
    private Royaume royaume;
    private boolean estJoueurActuel;

    public Joueur(String nom) {
        this.nom = nom;
        royaume = new Royaume(5);
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
}
