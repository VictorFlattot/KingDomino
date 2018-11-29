package KingDomino;

public class ModelTest {
    private Paquet paquet;
    private TuilesAuCentre tuilesAuCentre;
    private Joueur joueur;

    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        tuilesAuCentre = new TuilesAuCentre(paquet);
        joueur = new Joueur("J1");
    }


    public Paquet getPaquet() {
        return paquet;
    }

    public void setPaquet(Paquet paquet) {
        this.paquet = paquet;
    }

    public TuilesAuCentre getTuilesAuCentre() {
        return tuilesAuCentre;
    }

    public void setTuilesAuCentre(TuilesAuCentre tuilesAuCentre) {
        this.tuilesAuCentre = tuilesAuCentre;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
