package Model;


import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.ArrayList;

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
    private TuilesAuCentre centre;
    private Couleur[] couleursUtilisé;
    /**
     * The Test.
     */
    public boolean test;
    private int maxScore;
    private int[] bestCoord;

    /**
     * Instantiates a new Model test.
     */
    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        faireUnNouveauTour = false;
        nbTour=1;
        nbTourMax=13;
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
            joueurs[i]=new Joueur("",i,tailleRoyaume, null,false);
        }
        joueurs[0].setIA(true);
        joueurs[1].setIA(true);
        joueurs[2].setIA(true);
        joueurs[3].setIA(true);
        couleursUtilisé = new Couleur[nbJoueur];
        dominoDejaPlace = new boolean[nbDominoCentre];
        dominoDejaChoisi = new boolean[nbDominoCentre];
        for (int i = 0; i < nbDominoCentre; i++) {
            dominoDejaChoisi[i]=false;
        }

		initCouleurUtilisé();
		showCouleurUtilisé();
        initTuileCentre();
        initOrdre(nbJoueur);
    }

    private void initCouleurUtilisé(){
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

    private void showCouleurUtilisé(){
	    for (Couleur couleur:
	         couleursUtilisé) {
		    System.out.println(couleur);
	    }
    }

    private void initTuileCentre() {
        tuilleCentreAChoisir = new TuilesAuCentre(paquet,nbDominoCentre,true);
        tuilesCentreAPLacer = new TuilesAuCentre(paquet,nbDominoCentre,false);
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

        if (test && faireUnNouveauTour){
        	nouveauTour();
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

    private void removeDominoRoyaume(int idJoueur,int x,int y,int x2,int y2){
        joueurs[idJoueur].getRoyaume().removeDominoRoyaume(x,y,x2,y2);
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

    private void showOrdreActuel(){
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
     * @return the boolean
     */
    public boolean isPartieFinie(){
        return nbTour >=
                nbTourMax;
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
     * Calcul score int.
     *
     * @param joueur the joueur
     * @return the int
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
