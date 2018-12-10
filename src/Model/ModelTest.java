package Model;

public class ModelTest {
    private Paquet paquet;
    private TuilesAuCentre tuilesAuCentre;
    private Joueur[] joueurs;
    private int rotDominoSelect;
    private Domino dominoSelect;
    private boolean[] dominoDejaPlacé;
    private int nbJoueur;
    private Joueur[] ordreJoueurTourActuel;
    private Joueur[] ordreJoueurTourSuivant;
    private boolean faireUnNouveauTour;


    public ModelTest() {
        nbJoueur = 4;
        paquet = new Paquet();
        paquet.shuffle();
        tuilesAuCentre = new TuilesAuCentre(paquet);
        joueurs = new Joueur[nbJoueur];
        joueurs[0] = new Joueur("J1",0);
        joueurs[1] = new Joueur("J2",1);
        joueurs[2] = new Joueur("J3",2);
        joueurs[3] = new Joueur("J4",3);
        dominoDejaPlacé = new boolean[4];
        ordreJoueurTourActuel = new Joueur[nbJoueur];
        ordreJoueurTourSuivant = new Joueur[nbJoueur];
        faireUnNouveauTour = false;
        initOrdre();
        setJoueurActuel(0);
        showOrdreActuel();


    }

    private void initOrdre(){
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i] = joueurs[i];
        }

    }

    public void changementJoueur(){
        System.out.println(getPosJoueurActuel()+1);
        if (getPosJoueurActuel() == 3){
            setJoueurActuel(0);
            faireUnNouveauTour = true;
            System.out.println("new turn");
        }

        else{
            setJoueurActuel(getPosJoueurActuel()+1);
            faireUnNouveauTour = false;
        }




    }

    public void nouveauIndextourSuivant(int posDom){
        ordreJoueurTourSuivant[posDom]=getJoueurActuel();
        showOrdreNext();
    }

    public void nouveauTour(){
	    setFaireUnNouveauTour(false);
	    dominoDejaPlacé = new boolean[4];
        System.out.println("");
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i]= ordreJoueurTourSuivant[i];
        }
        tuilesAuCentre = new TuilesAuCentre(paquet);
        showOrdreActuel();
        System.out.println("");
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

    public void addDominoRoyaume(Domino domino,int idJoueur,int x,int y,int x2,int y2){
        joueurs[idJoueur].getRoyaume().addDominoRoyaume(domino,x,y,x2,y2);
    }

    public void setJoueurActuel(int index){
        for (Joueur joueur :
                ordreJoueurTourActuel) {
            joueur.setEstJoueurActuel(false);
        }
        ordreJoueurTourActuel[index].setEstJoueurActuel(true);
    }

    public int getPosJoueurActuel(){
        for (int i = 0; i < ordreJoueurTourActuel.length; i++) {
            if (ordreJoueurTourActuel[i].isEstJoueurActuel())
                return i;
        }
        return -1;
    }

    public Joueur getJoueurActuel(){
        for (int i = 0; i < ordreJoueurTourActuel.length; i++) {
            if (ordreJoueurTourActuel[i].isEstJoueurActuel())
                return ordreJoueurTourActuel[i];
        }
        return null;
    }

    public void setDominoDejaPlacé(int index,boolean b){
        nouveauIndextourSuivant(index);
        dominoDejaPlacé[index]=b;
    }

    public boolean[] getDominoDejaPlacé() {
        return dominoDejaPlacé;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

    public boolean faireUnNouveauTour() {
        return faireUnNouveauTour;
    }

    public void setFaireUnNouveauTour(boolean faireUnNouveauTour) {
        this.faireUnNouveauTour = faireUnNouveauTour;

    }

    private void showOrdreActuel(){
        for (Joueur joueur:
                ordreJoueurTourActuel) {
            if (joueur !=null){
                System.out.println(joueur.getNom());
            }else
                System.out.println("\"");
        }
    }


    private void showOrdreNext(){
        for (Joueur joueur:
                ordreJoueurTourSuivant) {
            if (joueur !=null){
                System.out.println(joueur.getNom());
            }else
                System.out.println("\"");

        }
    }
}
