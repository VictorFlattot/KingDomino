package Model;

public class ModelTest {
    private Paquet paquet;
    private TuilesAuCentre tuilesCentreAPLacer;
    private TuilesAuCentre tuilleCentreAChoisir;
    private Joueur[] joueurs;
    private Domino dominoSelect;
    private boolean[] dominoDejaPlace;
    private int nbJoueur;
    private Joueur[] ordreJoueurTourActuel;
    private Joueur[] ordreJoueurTourSuivant;
    private boolean faireUnNouveauTour;
    private int nbDominoCentre;
    private int nbTour;
    private int nbTourMax;


    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        dominoDejaPlace = new boolean[4];
        faireUnNouveauTour = false;
        nbTour = 1;
        nbTourMax = 12;
    }

    public void setNbJoueur(int nbJoueur){
        this.nbJoueur = nbJoueur;
        joueurs = new Joueur[nbJoueur];
        if (nbJoueur == 3) nbDominoCentre = 3;
        else nbDominoCentre = 4;
        for (int i = 0; i < nbJoueur; i++) {
            joueurs[i]=new Joueur("",i);
        }


        initTuileCentre();
        initOrdre(nbJoueur);
    }

    private void initTuileCentre() {
        tuilleCentreAChoisir = new TuilesAuCentre(paquet,nbDominoCentre);
        tuilesCentreAPLacer = new TuilesAuCentre(paquet,nbDominoCentre);
    }


    private void initOrdre(int nbJoueur){
        ordreJoueurTourActuel = new Joueur[nbJoueur];
        ordreJoueurTourSuivant = new Joueur[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i] = joueurs[i];
        }
        setJoueurActuel(0);

    }

    public void changementJoueur(){
        if (getPosJoueurActuel() == nbJoueur-1){
            setJoueurActuel(0);
            faireUnNouveauTour = true;
        }
        else{
            setJoueurActuel(getPosJoueurActuel()+1);
            faireUnNouveauTour = false;
        }

    }

    public void nouveauIndextourSuivant(int posDom){
        ordreJoueurTourSuivant[posDom]=getJoueurActuel();
    }

    public void nouveauTour(){
        System.out.println("nex turn");
        nbTour++;
        System.out.println(nbTour);
	    setFaireUnNouveauTour(false);
	    dominoDejaPlace = new boolean[4];
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i] = ordreJoueurTourSuivant[i];
        }
        tuilesCentreAPLacer = tuilleCentreAChoisir;
        tuilleCentreAChoisir = new TuilesAuCentre(paquet,nbDominoCentre);
        setJoueurActuel(0);
    }


    public Paquet getPaquet() {
        return paquet;
    }

    public void setPaquet(Paquet paquet) {
        this.paquet = paquet;
    }

    public TuilesAuCentre getTuilesCentreAPLacer() {
        return tuilesCentreAPLacer;
    }

    public void setTuilesCentreAPLacer(TuilesAuCentre tuilesCentreAPLacer) {
        this.tuilesCentreAPLacer = tuilesCentreAPLacer;
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

    public boolean addDominoRoyaume(Domino domino,int idJoueur,int x,int y,int x2,int y2){
        try {
            joueurs[idJoueur].getRoyaume().addDominoRoyaume(domino, x, y, x2, y2);
        }catch (ArrayIndexOutOfBoundsException | UnconnectedException e1){
            return false;
        }
        return  true;
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

    public Joueur getJoueur(int id) {
        for (int i = 0; i < ordreJoueurTourActuel.length; i++) {
            if (ordreJoueurTourActuel[i].getId() == id)
                return ordreJoueurTourActuel[i];
        }
        return null;
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
        dominoDejaPlace[index]=b;
    }

    public boolean[] getDominoDejaPlace() {
        return dominoDejaPlace;
    }

    public int getNbJoueur() {
        return nbJoueur;
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

    public boolean isPartieFinie(){
        return nbTour > nbTourMax;
    }

    public int calculScore(Joueur joueur){ //renvoie le score d'dun joueur
        int score=0;
        boolean memeTerrain = false;
        Royaume royaumeJoueur = joueur.getRoyaume();
        Royaume test = new Royaume(5); //TEST
        for (int i =0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                test.addTuille(new Tuile(Terrain.FORET,1),i,j); //TEST
                if(royaumeJoueur.getTuile(i,j).getCouronne()==1) {
                    score = 1;

                }
                    int nbTuilleSame =0;


                nbTuilleSame = getNbTuilleSame(royaumeJoueur, i, nbTuilleSame, royaumeJoueur.getTuile(i, j), i + 1, j, i);
                nbTuilleSame = getNbTuilleSame(royaumeJoueur, j, nbTuilleSame, royaumeJoueur.getTuile(i, j), i, j + 1, i);
                nbTuilleSame = getNbTuilleSame(royaumeJoueur, i, j, nbTuilleSame, royaumeJoueur.getTuile(i, j), i - 1);
                nbTuilleSame = getNbTuilleSame(royaumeJoueur, j, j - 1, nbTuilleSame, royaumeJoueur.getTuile(i, j), i);

                score = score * nbTuilleSame;




            }
        }

        joueur.setScore(score);
        return score;
    }

    private int getNbTuilleSame(Royaume royaumeJoueur, int i, int j, int nbTuilleSame, Tuile tuile, int i2) {
        boolean memeTerrain;
        if (i > 0) { //sinon ça fait OutOfBoundsException
                if (royaumeJoueur.isMemeTerrain(tuile, royaumeJoueur.getTuile(i2, j))) {
                    System.out.println("Les tuiles ont le même terrain");
                    memeTerrain = true;
                    nbTuilleSame++;
                }
            }
        return nbTuilleSame;
    }

    private int getNbTuilleSame(Royaume royaumeJoueur, int j, int nbTuilleSame, Tuile tuile, int i2, int i3, int i) {
        boolean memeTerrain;
        if (j < 4) { //sinon ça fait OutOfBoundsException
            if (royaumeJoueur.isMemeTerrain(tuile, royaumeJoueur.getTuile(i2, i3))) {
                System.out.println("Les tuiles ont le même terrain");
                memeTerrain = true;
                nbTuilleSame++;
            }
        }
        return nbTuilleSame;
    }


    public void setNomJoueur(String nom,int i) {
        joueurs[i].setNom(nom);
        System.out.println(nom);
    }

    public int getNbDominoCentre() {
        return nbDominoCentre;
    }

    public void setNbDominoCentre(int nbDominoCentre) {
        this.nbDominoCentre = nbDominoCentre;
    }

    public TuilesAuCentre getTuilleCentreAChoisir() {
        return tuilleCentreAChoisir;
    }

    public void setTuilleCentreAChoisir(TuilesAuCentre tuilleCentreAChoisir) {
        this.tuilleCentreAChoisir = tuilleCentreAChoisir;
    }

    public int getNbTourMax() {
        return nbTourMax;
    }

    public void setNbTourMax(int nbTourMax) {
        this.nbTourMax = nbTourMax;
    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }
}
