package KingDomino;

public class ModelTest {
    private Paquet paquet;
    private TuilesAuCentre tuilesAuCentre;

    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        tuilesAuCentre = new TuilesAuCentre(paquet);
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
}
