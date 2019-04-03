package Model;


import java.util.ArrayList;

/**
 * Modèle de données.
 * Contient toutes les méthodes concernant:
 * -La manipulation des joueurs
 * -Manipulation des dominos/Tuiles.
 */
public class ModelTest {

    /**
     * Le paquet de domino
     *
     * @see Paquet
     */
    private Paquet paquet;

    /**
     * Les tuiles à placer
     *
     * @see TuilesAuCentre
     */
    private TuilesAuCentre tuilesCentreAPLacer;

    /**
     * Les tuiles à choisir
     *
     * @see TuilesAuCentre
     */
    private TuilesAuCentre tuilleCentreAChoisir;

    /**
     * La liste des joueurs
     *
     * @see Joueur
     */
    private Joueur[] joueurs;

    /**
     * Le domino sélectionné
     *
     * @see Domino
     */
    private Domino dominoSelect;

    /**
     * Un tableau pour savoir si un domino est déjà placé
     */
    private boolean[] dominoDejaPlace;

    /**
     * Le nombre de joueur
     */
    private int nbJoueur;

    /**
     * Le nombre de joueur qui ne sont pas des IA
     */
    private int nbJoueurReel;

    /**
     * Un tableau pour connaitre l'ordre des joueurs pour ce tour
     */
    private Joueur[] ordreJoueurTourActuel;

    /**
     * Un tableau pour connaitre l'ordre des joueurs au tour suivant
     */
    private Joueur[] ordreJoueurTourSuivant;

    /**
     * Vrai si on fait un nouveau tour
     */
    private boolean faireUnNouveauTour;

    /**
     * Le nombre de domino présent au centre
     */
    private int nbDominoCentre;

    /**
     * La taille du royaume
     */
    private int tailleRoyaume;

    /**
     * Le nombre de tour actuel
     */
    private int nbTour;

    /**
     * Le nombre de tour maximum
     */
    private int nbTourMax;

    /**
     * tableau pour savoir si un domino est déja choisi
     */
    private boolean[] dominoDejaChoisi;

    /**
     * Le centre
     *
     * @see TuilesAuCentre
     */
    private TuilesAuCentre centre;

    /**
     * tableau pour les couleurs utilisées
     *
     * @see Couleur
     */
    private Couleur[] couleursUtilisé;

    /**
     * Vrai si on est en phase de test
     */
    public boolean test;

    /**
     * Le score maximum
     */
    private int maxScore;

    /**
     * Tableau contenant les meilleurs coordonnées pour l'IA
     */
    private int[] bestCoord;

    /**
     * Constructeur de la classe.
     */
    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        faireUnNouveauTour = false;
        nbTour=1;
        nbTourMax=13;
    }

    /**
     * Permet de définir le nombre de joueur..
     *
     * @param nbJoueurReel Nombre de joueur présents.
     * @see ModelTest#initCouleurUtilise() ModelTest#initCouleurUtilise()
     * @see ModelTest#showCouleurUtilise() ModelTest#showCouleurUtilise()
     * @see ModelTest#initTuileCentre() ModelTest#initTuileCentre()
     * @see ModelTest#initOrdre(int) ModelTest#initOrdre(int)
     */
    public void setNbJoueur(int nbJoueurReel){
        this.nbJoueurReel = nbJoueurReel;
        this.nbJoueur = 4;
        joueurs = new Joueur[nbJoueur];
        nbDominoCentre = 4;
        tailleRoyaume = 5;
        for (int i = 0; i < nbJoueur; i++) {
            joueurs[i]=new Joueur("",i,tailleRoyaume, null,false);
        }
        int nbIa = 4 - nbJoueurReel;
        for (int i = 0; i < nbIa;i++){
            joueurs[i].setIA(true);
        }
        couleursUtilisé = new Couleur[nbJoueur];
        dominoDejaPlace = new boolean[nbDominoCentre];
        dominoDejaChoisi = new boolean[nbDominoCentre];
        for (int i = 0; i < nbDominoCentre; i++) {
            dominoDejaChoisi[i]=false;
        }

		initCouleurUtilise();
		showCouleurUtilise();
        initTuileCentre();
        initOrdre(nbJoueur);
    }

    /**
     * Recence les couleurs utilisées
     *
     * @see Couleur
     */
    private void initCouleurUtilise(){
    	if (nbJoueur>=2){
    		couleursUtilisé[0] = Couleur.Bleu;
    		couleursUtilisé[1] = Couleur.Jaune;
	    }
	    if (nbJoueur>=3){
	    	couleursUtilisé[2] = Couleur.Rouge;
	    }
	    if (nbJoueur<=4) {
		    couleursUtilisé[3] = Couleur.Vert;
	    }

    }

    /**
     * Affiche les couleurs utilisées
     */
    private void showCouleurUtilise(){
	    for (Couleur couleur:
	         couleursUtilisé) {
		    System.out.println(couleur);
	    }
    }

    /**
     * Initialise les tuiles présentes au centre du plateau
     */
    private void initTuileCentre() {
        tuilleCentreAChoisir = new TuilesAuCentre(paquet,nbDominoCentre,true);
        tuilesCentreAPLacer = new TuilesAuCentre(paquet,nbDominoCentre,false);
    }

    /**
     * Initialise l'ordre des joueurs
     *
     * @see Joueur
     * @see ModelTest#setJoueurActuel(int)
     */
    private void initOrdre(int nbJoueur){
        ordreJoueurTourActuel = new Joueur[nbJoueur];
        ordreJoueurTourSuivant = new Joueur[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i] = joueurs[i];
        }
        setJoueurActuel(0);
    }

    /**
     * Change le joueur actuelle.
     *
     * @see ModelTest#setJoueurActuel(int) ModelTest#setJoueurActuel(int)
     * @see ModelTest#nouveauTour() ModelTest#nouveauTour()
     */
    public void changementJoueur(){
        if (getPosJoueurActuel() == nbJoueur-1){
            setJoueurActuel(0);
            faireUnNouveauTour = true;
        } else {
            setJoueurActuel(getPosJoueurActuel()+1);
            faireUnNouveauTour = false;
        }
        if (test && faireUnNouveauTour){
        	nouveauTour();
        }
    }

    /**
     * Permet de créer le tableau pour avoir l'ordre au tour suivant.
     *
     * @param posDom position du domino.(0,1,2,3)
     */
    public void nouveauIndextourSuivant(int posDom){
        System.out.println("Le joueur ACTUELLE EST : "+getJoueurActuel().getNom());
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
        tuilleCentreAChoisir = new TuilesAuCentre(paquet,nbDominoCentre,true);
        //calculScore(joueurs[0]);
        //calculScore(joueurs[1]);
        //calculScore(joueurs[2]);
        calculScore(joueurs[3]);
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
     * Gets tuiles centre a placer.
     *
     * @return the tuiles centre ap lacer
     */
    public TuilesAuCentre getTuilesCentreAPLacer() {
        return tuilesCentreAPLacer;
    }

    /**
     * Sets tuiles centre a placer.
     *
     * @param tuilesCentreAPLacer the tuiles centre ap lacer
     * @See TuilesAuCentre
     */
    public void setTuilesCentreAPLacer(TuilesAuCentre tuilesCentreAPLacer) {
        this.tuilesCentreAPLacer = tuilesCentreAPLacer;
    }

    /**
     * Fonction pour obtenir le tableau de joueur complet.
     *
     * @return the joueur [ ]
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * Permet de donner un tableau de joueur au modèle.
     *
     * @param joueurs the joueurs
     */
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Permet de connaitre la rotation du domino actuelle.
     *
     * @return the rot domino select
     */
    public int getRotDominoSelect() {
        return dominoSelect.getRotation();
    }

    /**
     * Permet de donner une rotation au domino séléctionner.
     *
     * @param rotDominoSelect the rot domino select
     */
    public void setRotDominoSelect(int rotDominoSelect) {
        dominoSelect.setRotation(rotDominoSelect);
        switch (getRotDominoSelect()){
            case 0:
                dominoSelect.setTuileNord(dominoSelect.getTuileOuest());
                dominoSelect.setTuileSud(dominoSelect.getTuileEst());
                break;
            case 90:
                dominoSelect.setTuileEst(dominoSelect.getTuileNord());
                dominoSelect.setTuileOuest(dominoSelect.getTuileSud());
                break;
            case 180:
                dominoSelect.setTuileNord(dominoSelect.getTuileOuest());
                dominoSelect.setTuileSud(dominoSelect.getTuileEst());
                break;
            case 270:
                dominoSelect.setTuileEst(dominoSelect.getTuileNord());
                dominoSelect.setTuileOuest(dominoSelect.getTuileSud());
                break;
        }
    }

    /**
     * Retourne le domino séléctionner.
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
     * Ajoute le domino au royaume du joueur au position données.
     *
     * @param domino   Le Domino choisis
     * @param idJoueur id joueur
     * @param x        coordonées x de la première tuiles à placer
     * @param y        coordonées y de la première tuiles à placer
     * @param x2       coordonées x de la deuxième tuiles à placer
     * @param y2       coordonées y de la deuxième tuiles à placer
     * @return the boolean. Si il y a une erreur retourne false.
     */
    public boolean addDominoRoyaume(Domino domino,int idJoueur,int x,int y,int x2,int y2){
        try {
            joueurs[idJoueur].getRoyaume().addDominoRoyaume(domino, x, y, x2, y2);
        } catch (ArrayIndexOutOfBoundsException | UnconnectedException e1){
            return false;
        }
        return  true;
    }

    /**
     * Retire un domino du royaume d'un joueur
     *
     * @param idJoueur  L'id du joueur auquel un domino sera retiré
     * @param x         La position x du domino
     * @param y         La position y du domino
     * @param x2        La position x2 du domino
     * @param y2        La position y2 du domino
     */
    private void removeDominoRoyaume(int idJoueur,int x,int y,int x2,int y2){
        joueurs[idJoueur].getRoyaume().removeDominoRoyaume(x,y,x2,y2);
    }

    /**
     * Permet d'actualiser le joueur actuelle.
     *
     * @param index Index du joueur dans le tableau des joueurs du tour.
     */
    public void setJoueurActuel(int index){
        for (Joueur joueur :
                ordreJoueurTourActuel) {
            joueur.setEstJoueurActuel(false);
        }
        ordreJoueurTourActuel[index].setEstJoueurActuel(true);
    }

    /**
     * Permet de connaitre l'index du joueur actuelle dans le tableau du tour.
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
     * Permet de donner le joueur présent dans le tableau à l'index.
     *
     * @param id Index du joueur voulu.
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
     * Gets nb joueur reel.
     *
     * @return the nb joueur reel
     */
    public int getNbJoueurReel() {
        return nbJoueurReel;
    }

    /**
     * Sets nb joueur reel.
     *
     * @param nbJoueurReel the nb joueur reel
     */
    public void setNbJoueurReel(int nbJoueurReel) {
        this.nbJoueurReel = nbJoueurReel;
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
     * Gets centre.
     *
     * @return the centre
     */
    public TuilesAuCentre getCentre() {
        return centre;
    }

    /**
     * Sets centre.
     *
     * @param centre the centre
     */
    public void setCentre(TuilesAuCentre centre) {
        this.centre = centre;
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

    /**
     * Affiche l'ordre actuel des joueurs
     */
    public void showOrdreActuel(){
        for (Joueur joueur:
                ordreJoueurTourActuel) {
            if (joueur !=null){
                System.out.println(joueur.getNom());
            }else
                System.out.println("\"");
        }
    }

    /**
     * Show ordre next.
     */
    public void showOrdreNext(){
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
     * @return vrai si la partie est terminée
     */
    public boolean isPartieFinie(){
        return nbTour >=
                nbTourMax;
    }

    /**
     * La focntion de calcul des scores
     *
     * @param joueur the joueur
     * @return the int
     * @see ModelTest#voisin(Royaume, int, int) ModelTest#voisin(Royaume, int, int)
     */
    public int calculScore(Joueur joueur){
        int score = 0;
        Royaume rJoueur = joueur.getRoyaume();
        for (int i = 0; i < tailleRoyaume; i++){
            for (int j = 0; j < tailleRoyaume; j++){
                rJoueur.setTuileCheckStatut(i, j, false);
            }
        }
        for (int i = 0; i < tailleRoyaume; i++){
            for (int j = 0; j < tailleRoyaume; j++){
                if (!rJoueur.isTuileChecked(i, j) && rJoueur.getTuile(i, j).getTerrain() != null){
                    int[] preScore;
                    preScore = voisin(rJoueur, i, j);
                    score += preScore[0]*preScore[1];
                }
            }
        }
        //System.out.println(joueur.getNom() + ", score : " + score);
        return score;
    }

    /**
     * Fonction récurssive pour obtenir les voisins d'une tuile donnée
     * Complète la fonction calculScore
     *
     * @param rJoueur   Le royaume du joueur courant
     * @param x         Les coordonnées
     * @param y         Les coordonnées
     *
     * @see ModelTest#calculScore(Joueur)
     *
     * @return Le score des voisins de cette tuile
     */
    private int[] voisin(Royaume rJoueur, int x, int y){
        int[] preScore = new int[2];
        preScore[0] = 0; preScore[1] = 0;
        if (!rJoueur.isTuileChecked(x, y)){
            rJoueur.setTuileCheckStatut(x, y, true);
            preScore[0] = rJoueur.getTuile(x, y).getCouronne();
            preScore[1] = 1;
            int[] voisinScore;
            if (y+1 < tailleRoyaume && rJoueur.getTuile(x, y).getTerrain() == rJoueur.getTuile(x, y+1).getTerrain()){
                voisinScore = voisin(rJoueur, x, y+1);
                preScore[0] += voisinScore[0];  preScore[1] += voisinScore[1];
            }
            if (x+1 < tailleRoyaume && rJoueur.getTuile(x, y).getTerrain() == rJoueur.getTuile(x+1, y).getTerrain()){
                voisinScore = voisin(rJoueur, x+1, y);
                preScore[0] += voisinScore[0];  preScore[1] += voisinScore[1];
            }
            if (x-1 >= 0  && rJoueur.getTuile(x, y).getTerrain() == rJoueur.getTuile(x-1, y).getTerrain()){
                voisinScore = voisin(rJoueur, x-1, y);
                preScore[0] += voisinScore[0];  preScore[1] += voisinScore[1];
            }
            if (y-1 >= 0 && rJoueur.getTuile(x, y).getTerrain() == rJoueur.getTuile(x, y-1).getTerrain()){
                voisinScore = voisin(rJoueur, x, y-1);
                preScore[0] += voisinScore[0];  preScore[1] += voisinScore[1];
            }
        }
        return preScore;
    }

    /**
     * Sets nom joueur.
     *
     * @param nom the nom
     * @param i   the
     */
    public void setNomJoueur(String nom,int i) {
        joueurs[i].setNom(nom);
    }

    /**
     * Set couleur joueur.
     *
     * @param couleurJoueur the couleur joueur
     * @param index         the index
     */
    public void setCouleurJoueur(Couleur couleurJoueur,int index){
    	joueurs[index].setCouleur(couleurJoueur);
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

    /**
     * Get couleurs utilisé couleur [ ].
     *
     * @return the couleur [ ]
     */
    public Couleur[] getCouleursUtilisé() {
		return couleursUtilisé;
	}

    /**
     * Set couleurs utilisé.
     *
     * @param couleursUtilisé the couleurs utilisé
     */
    public void setCouleursUtilisé(Couleur[] couleursUtilisé) {
		this.couleursUtilisé = couleursUtilisé;
	}

    //IA


    /**
     * Quel dom pour ia int.
     *
     * @return the int
     */
    public int quelDomPourIA(){
        for (int i = 3; i >= 0 ; i--) {
            //showDomDejaChoisi();
            if (!dominoDejaChoisi[i])
                return i;
        }
        return -1;
    }

    /**
     * Who is best domino int.
     *
     * @return the int
     */
    public int whoIsBestDomino(){//retourne le numéro du domino au centre
        ArrayList<int[]> listPositionValide = new ArrayList<>();
        int meilleurScore=0;
        int meilleurIndex=0;
        showDomDejaChoisi();
        for(int i = 3; i>=0;i--){
            if(!dominoDejaChoisi[i]){
                dominoSelect = tuilesCentreAPLacer.getDominoTab()[i];
                System.out.println(dominoSelect);
                System.out.println("i : " + i);
                int maxScore = ouPlacerDomino()[5];
                    if(meilleurScore<=maxScore){
                        meilleurScore = maxScore;
                        meilleurIndex = i;
                    }
                    return meilleurIndex;
                }
        }

        return -1;
    }


    /**
     * Ou placer domino int [ ].
     *
     * @return the int [ ]
     */
    public int[] ouPlacerDomino(){
        ArrayList<int[]> positionPosible = new ArrayList<>();
        int[] allRotation = new int[4];
        for (int i = 0; i < 4; i++) {
            allRotation[i] = i*90;
        }
        bestCoord = new int[6];
        int[] coord;
        int i2;
        int j2;
        for (int rot : allRotation) {
            rotateTo(rot/90);//Conversion degrées position 1,2,3,4
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    i2 = i; j2 = j;
                    if (rot==0 || rot==180) j2 = j+1;
                    if (rot==90|| rot==270) i2 = i-1;
                    try {
                        if (j2 <tailleRoyaume && i2>=0 ){
                            if(getDominoSelect().getId() == 18 && joueurs[getPosJoueurActuel()].getRoyaume().verifPlacement(dominoSelect, i, j, i2, j2)) {

                            }
                            try{
                                if (addDominoRoyaume(dominoSelect,getJoueurActuel().getId(), i, j, i2, j2)){
                                    coord = new int[5];
                                    coord[0] = i;
                                    coord[1] = j;
                                    coord[2] = i2;
                                    coord[3] = j2;
                                    coord[4] = rot;
                                    positionPosible.add(coord);
                                    removeDominoRoyaume(getJoueurActuel().getId(), i, j, i2, j2);
                                }

                            }catch (Exception e){
                                System.out.println("aie");
                            }



                        }
                    } catch (UnconnectedException e) {}
                }
            }

            getBestCoord(positionPosible);


        }


       /*for (int coordone:bestCoord){
            System.out.println(coordone);
        }

        for (int[] ints: positionPosible
             ) {
            System.out.println("");
            for (int i = 0; i < 5; i++) {
                System.out.print(ints[i] + " / ");
            }

        }*/

        if (positionPosible.size() == 0){
            bestCoord[0] = -1;
        }

        return bestCoord;
    }

    /**
     * Avec une liste de position où placer le domino, retourne la meilleur position
     *
     * @param list  Une liste de coordonées
     *
     * @return Un tableau contenant les meilleurs coordonnées pour le placement d'un domino
     */
    private int[] getBestCoord(ArrayList<int []> list){
        ArrayList<int[]> bestPositionPosible = new ArrayList<>(); //liste de positions
        if (getRotDominoSelect() == 0) maxScore = 0;
        int scoreCalcule;
        for (int[] coord :
                list) {
            addDominoRoyaume(dominoSelect,getJoueurActuel().getId(),coord[0],coord[1],coord[2],coord[3]);
            scoreCalcule = calculScore(getJoueurActuel());

            if (maxScore <= scoreCalcule){
                //System.out.println(coord[0]+"∕"+coord[1]+"∕"+coord[2]+"∕"+coord[3]+"∕"+coord[4]);
                bestPositionPosible.add(coord);
                maxScore = scoreCalcule;
                System.arraycopy(coord, 0, bestCoord, 0, 5);
                bestCoord[5]=maxScore;
            }
            removeDominoRoyaume(getJoueurActuel().getId(),coord[0],coord[1],coord[2],coord[3]);
        }

        return bestCoord;// attribut de la classe
    }

    /**
     * Rotate int.
     *
     * @return the int
     */
    public int rotate(){
        int rot = getRotDominoSelect();
        switch (getRotDominoSelect()){
            case 0:
                rot=90;
                setRotDominoSelect(90);
                break;
            case 90:
                rot=180;
                setRotDominoSelect(180);
                break;
            case 180:
                rot=270;
                setRotDominoSelect(270);
                break;
            case 270:
                rot=0;
                setRotDominoSelect(0);
                break;
        }
        return rot;
    }

    /**
     * Rotate to.
     *
     * @param pos the pos
     */
    public void rotateTo(int pos){
        setRotDominoSelect(0);
        for (int i = 0; i < pos ; i++) {
            rotate();
        }
    }
}
