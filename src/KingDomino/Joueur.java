package KingDomino;

public class Joueur {

    private String nom;
    private Royaume royaume;

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
}
