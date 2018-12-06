package Model;

public class ModelTest {
    private Paquet paquet;
    private TuilesAuCentre tuilesAuCentre;
    private Joueur[] joueurs;
    private Joueur joueurActuel;
    private int rotDominoSelect;
    private Domino dominoSelect;

    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        tuilesAuCentre = new TuilesAuCentre(paquet);
        joueurs = new Joueur[4];
        joueurs[0] = new Joueur("J1");
        joueurActuel=joueurs[0];

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

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public int getRotDominoSelect() {
        return dominoSelect.getRotation();
    }

    public void setRotDominoSelect(int rotDominoSelect) {
        dominoSelect.setRotation(rotDominoSelect);
    }

    public Domino getDominoSelect() {
        return dominoSelect;
    }

    public void setDominoSelect(Domino dominoSelect) {
        this.dominoSelect = dominoSelect;
    }

    public void addDominoRoyaume(Domino domino,int idJoueur,int x,int y){
        joueurs[idJoueur].getRoyaume().addDominoRoyaume(domino,x,y);
    }

    public int indexJoueur(){
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurs[i] == joueurActuel)
                return i;
        }
        return -1;
    }
}
