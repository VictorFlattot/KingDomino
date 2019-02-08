package Model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.ArrayList;

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
    private Couleur[] couleursUtilisé;

    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        faireUnNouveauTour = false;
        nbTour=1;
        nbTourMax=13;
    }

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
        couleursUtilisé = new Couleur[nbJoueur];
        dominoDejaPlace = new boolean[nbDominoCentre];
        dominoDejaChoisi = new boolean[nbDominoCentre];

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
        nbTour++;
        System.out.println(nbTour);
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

    private void removeDominoRoyaume(int idJoueur,int x,int y,int x2,int y2){
        joueurs[idJoueur].getRoyaume().removeDominoRoyaume(x,y,x2,y2);
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
        dominoDejaPlace[index]=b;
    }

    public void setDominoDejaChoisi(int index,boolean b){
        nouveauIndextourSuivant(index);
        dominoDejaChoisi[index]=b;


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

    public void showDomDejaChoisi(){
        for (Boolean b:
                dominoDejaChoisi) {
            if (b !=null){
                System.out.println(b);
            }else
                System.out.println("\"");

        }
    }

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
    public void setNomJoueur(String nom,int i) {
        joueurs[i].setNom(nom);
    }

    public void setCouleurJoueur(Couleur couleurJoueur,int index){
    	joueurs[index].setCouleur(couleurJoueur);
    }

    public int getNbDominoCentre() {
        return nbDominoCentre;
    }

    public void setNbDominoCentre(int nbDominoCentre) {
        this.nbDominoCentre = nbDominoCentre;
    }

    public int getTailleRoyaume() {
        return tailleRoyaume;
    }

    public void setTailleRoyaume(int tailleRoyaume) {
        this.tailleRoyaume = tailleRoyaume;
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

	public Couleur[] getCouleursUtilisé() {
		return couleursUtilisé;
	}

	public void setCouleursUtilisé(Couleur[] couleursUtilisé) {
		this.couleursUtilisé = couleursUtilisé;
	}

    //IA



    public int quelDomPourIA(){
        return getPosJoueurActuel();
    }

    public int[] ouPlacerDomino(){
        ArrayList<int[]> positionPosible = new ArrayList<>();
        System.out.println(getRotDominoSelect());
        int bestRot = 0;
        int[] allRotation = new int[4];
        for (int i = 0; i < 4; i++) {
            allRotation[i] = i*90;
        }
        int[] bestCoord = new int[5];
        int[] coord;
        int i2;
        int j2;
        for (int rot : allRotation) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    i2 = i; j2 = j;
                    if (rot==0 || rot==180) j2 = j+1;
                    if (rot==90|| rot==270) i2 = i-1;
                    try {
                        if (j2 <tailleRoyaume && i2>=0 ){
                            if (joueurs[getPosJoueurActuel()].getRoyaume().verifPlacement(dominoSelect,i,j,i2,j2)){
                                coord = new int[4];
                                coord[0]=i;coord[1]=j;coord[2]=i2;coord[3]=j2;
                                positionPosible.add(coord);

                            }
                        }
                    } catch (UnconnectedException e) {}
                }
            }

            bestCoord = getBestCoord(positionPosible,rot);
            if (bestCoord[4]>=bestRot){
                    bestRot = bestCoord[4];
            }

        }


        System.out.println("bestCoord");
        for (int coordone:bestCoord){
            System.out.println(coordone);
        }

        return bestCoord;
    }

    private int[] getBestCoord(ArrayList<int []> list,int rot){
        int maxScore = 0;
        int scoreCalculé;
        int[] bestCoord = new int[5];
        for (int[] coord :
                list) {
            addDominoRoyaume(dominoSelect,getJoueurActuel().getId(),coord[0],coord[1],coord[2],coord[3]);
            scoreCalculé = calculScore(getJoueurActuel());
            if (maxScore <= scoreCalculé){
                maxScore = scoreCalculé;
                System.out.println("rot :" + rot);
                System.arraycopy(coord, 0, bestCoord, 0, 4);
                bestCoord[4]=rot;
            }
            removeDominoRoyaume(getJoueurActuel().getId(),coord[0],coord[1],coord[2],coord[3]);
        }
        System.out.println(maxScore);

        return bestCoord;
    }

}
