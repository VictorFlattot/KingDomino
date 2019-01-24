package Model;

/**
 * The type Model test.
 */
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
    private int tailleRoyaume;
    private int nbTour;
    private int nbTourMax;
    private boolean[] dominoDejaChoisi;


    /**
     * Instantiates a new Model test.
     */
    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        faireUnNouveauTour = false;
        nbTour=1;
        nbTourMax=12;




    }

    /**
     * Set nb joueur.
     *
     * @param nbJoueur the nb joueur
     */
    public void setNbJoueur(int nbJoueur){
        this.nbJoueur = nbJoueur;
        joueurs = new Joueur[nbJoueur];
        if (nbJoueur == 3) nbDominoCentre = 3;
        else nbDominoCentre = 4;
         if (nbJoueur > 2) tailleRoyaume = 5;
        else tailleRoyaume = 7;
        for (int i = 0; i < nbJoueur; i++) {
            joueurs[i]=new Joueur("",i,tailleRoyaume);
        }
        dominoDejaPlace = new boolean[nbDominoCentre];
        dominoDejaChoisi = new boolean[nbDominoCentre];


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

    /**
     * Changement joueur.
     */
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

    /**
     * Nouveau indextour suivant.
     *
     * @param posDom the pos dom
     */
    public void nouveauIndextourSuivant(int posDom){
        ordreJoueurTourSuivant[posDom]=getJoueurActuel();
    }

    /**
     * Nouveau tour.
     */
    public void nouveauTour(){
        nbTour++;
	    setFaireUnNouveauTour(false);
	    dominoDejaPlace = new boolean[nbDominoCentre];
	    dominoDejaChoisi = new boolean[nbDominoCentre];
        for (int i = 0; i < nbJoueur; i++) {

            ordreJoueurTourActuel[i] = ordreJoueurTourSuivant[i];
        }
        tuilesCentreAPLacer = tuilleCentreAChoisir;
        tuilleCentreAChoisir = new TuilesAuCentre(paquet,nbDominoCentre);
        setJoueurActuel(0);
    }


    /**
     * Gets paquet.
     *
     * @return the paquet
     */
    public Paquet getPaquet() {
        return paquet;
    }

    /**
     * Sets paquet.
     *
     * @param paquet the paquet
     */
    public void setPaquet(Paquet paquet) {
        this.paquet = paquet;
    }

    /**
     * Gets tuiles centre ap lacer.
     *
     * @return the tuiles centre ap lacer
     */
    public TuilesAuCentre getTuilesCentreAPLacer() {
        return tuilesCentreAPLacer;
    }

    /**
     * Sets tuiles centre ap lacer.
     *
     * @param tuilesCentreAPLacer the tuiles centre ap lacer
     */
    public void setTuilesCentreAPLacer(TuilesAuCentre tuilesCentreAPLacer) {
        this.tuilesCentreAPLacer = tuilesCentreAPLacer;
    }

    /**
     * Get joueurs joueur [ ].
     *
     * @return the joueur [ ]
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * Sets joueurs.
     *
     * @param joueurs the joueurs
     */
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Gets rot domino select.
     *
     * @return the rot domino select
     */
    public int getRotDominoSelect() {
        return dominoSelect.getRotation();
    }

    /**
     * Sets rot domino select.
     *
     * @param rotDominoSelect the rot domino select
     */
    public void setRotDominoSelect(int rotDominoSelect) {
        dominoSelect.setRotation(rotDominoSelect);
    }

    /**
     * Gets domino select.
     *
     * @return the domino select
     */
    public Domino getDominoSelect() {
        return dominoSelect;
    }

    /**
     * Sets domino select.
     *
     * @param dominoSelect the domino select
     */
    public void setDominoSelect(Domino dominoSelect) {
        this.dominoSelect = dominoSelect;
    }

    /**
     * Add domino royaume boolean.
     *
     * @param domino   the domino
     * @param idJoueur the id joueur
     * @param x        the x
     * @param y        the y
     * @param x2       the x 2
     * @param y2       the y 2
     * @return the boolean
     */
    public boolean addDominoRoyaume(Domino domino,int idJoueur,int x,int y,int x2,int y2){
        try {
            joueurs[idJoueur].getRoyaume().addDominoRoyaume(domino, x, y, x2, y2);
        }catch (ArrayIndexOutOfBoundsException | UnconnectedException e1){
            return false;
        }
        return  true;
    }

    /**
     * Set joueur actuel.
     *
     * @param index the index
     */
    public void setJoueurActuel(int index){
        for (Joueur joueur :
                ordreJoueurTourActuel) {
            joueur.setEstJoueurActuel(false);
        }
        ordreJoueurTourActuel[index].setEstJoueurActuel(true);
    }

    /**
     * Get pos joueur actuel int.
     *
     * @return the int
     */
    public int getPosJoueurActuel(){
        for (int i = 0; i < ordreJoueurTourActuel.length; i++) {
            if (ordreJoueurTourActuel[i].isEstJoueurActuel())
                return i;
        }
        return -1;
    }

    /**
     * Gets joueur.
     *
     * @param id the id
     * @return the joueur
     */
    public Joueur getJoueur(int id) {
        for (int i = 0; i < ordreJoueurTourActuel.length; i++) {
            if (ordreJoueurTourActuel[i].getId() == id)
                return ordreJoueurTourActuel[i];
        }
        return null;
    }


    /**
     * Get joueur actuel joueur.
     *
     * @return the joueur
     */
    public Joueur getJoueurActuel(){
        for (int i = 0; i < ordreJoueurTourActuel.length; i++) {
            if (ordreJoueurTourActuel[i].isEstJoueurActuel())
                return ordreJoueurTourActuel[i];
        }
        return null;
    }

    /**
     * Set domino deja placé.
     *
     * @param index the index
     * @param b     the b
     */
    public void setDominoDejaPlacé(int index,boolean b){
        dominoDejaPlace[index]=b;
    }

    /**
     * Set domino deja choisi.
     *
     * @param index the index
     * @param b     the b
     */
    public void setDominoDejaChoisi(int index,boolean b){
        nouveauIndextourSuivant(index);
        dominoDejaChoisi[index]=b;


    }

    /**
     * Get domino deja place boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getDominoDejaPlace() {
        return dominoDejaPlace;
    }

    /**
     * Gets nb joueur.
     *
     * @return the nb joueur
     */
    public int getNbJoueur() {
        return nbJoueur;
    }

    /**
     * Faire un nouveau tour boolean.
     *
     * @return the boolean
     */
    public boolean faireUnNouveauTour() {
        return faireUnNouveauTour;
    }

    /**
     * Sets faire un nouveau tour.
     *
     * @param faireUnNouveauTour the faire un nouveau tour
     */
    public void setFaireUnNouveauTour(boolean faireUnNouveauTour) {
        this.faireUnNouveauTour = faireUnNouveauTour;

    }

    /**
     * Is deja choisit boolean.
     *
     * @param index the index
     * @return the boolean
     */
    public boolean isDejaChoisit(int index){
           return dominoDejaChoisi[index];
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

    /**
     * Show dom deja choisi.
     */
    public void showDomDejaChoisi(){
        for (Boolean b:
                dominoDejaChoisi) {
            if (b !=null){
                System.out.println(b);
            }else
                System.out.println("\"");

        }
    }

    /**
     * Is partie finie boolean.
     *
     * @return the boolean
     */
    public boolean isPartieFinie(){
        return nbTour > nbTourMax;
    }

    /**
     * Calcul score int.
     *
     * @param joueur the joueur
     * @return the int
     */
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


    /**
     * Sets nom joueur.
     *
     * @param nom the nom
     * @param i   the
     */
    public void setNomJoueur(String nom,int i) {
        joueurs[i].setNom(nom);
        System.out.println(nom);
    }

    /**
     * Gets nb domino centre.
     *
     * @return the nb domino centre
     */
    public int getNbDominoCentre() {
        return nbDominoCentre;
    }

    /**
     * Sets nb domino centre.
     *
     * @param nbDominoCentre the nb domino centre
     */
    public void setNbDominoCentre(int nbDominoCentre) {
        this.nbDominoCentre = nbDominoCentre;
    }

    /**
     * Gets taille royaume.
     *
     * @return the taille royaume
     */
    public int getTailleRoyaume() {
        return tailleRoyaume;
    }

    /**
     * Sets taille royaume.
     *
     * @param tailleRoyaume the taille royaume
     */
    public void setTailleRoyaume(int tailleRoyaume) {
        this.tailleRoyaume = tailleRoyaume;
    }

    /**
     * Gets tuille centre a choisir.
     *
     * @return the tuille centre a choisir
     */
    public TuilesAuCentre getTuilleCentreAChoisir() {
        return tuilleCentreAChoisir;
    }

    /**
     * Sets tuille centre a choisir.
     *
     * @param tuilleCentreAChoisir the tuille centre a choisir
     */
    public void setTuilleCentreAChoisir(TuilesAuCentre tuilleCentreAChoisir) {
        this.tuilleCentreAChoisir = tuilleCentreAChoisir;
    }

    /**
     * Gets nb tour max.
     *
     * @return the nb tour max
     */
    public int getNbTourMax() {
        return nbTourMax;
    }

    /**
     * Sets nb tour max.
     *
     * @param nbTourMax the nb tour max
     */
    public void setNbTourMax(int nbTourMax) {
        this.nbTourMax = nbTourMax;
    }

    /**
     * Gets nb tour.
     *
     * @return the nb tour
     */
    public int getNbTour() {
        return nbTour;
    }

    /**
     * Sets nb tour.
     *
     * @param nbTour the nb tour
     */
    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }
}
