package Model;

public class ModelTest {
    private Paquet paquet;
    private TuilesAuCentre tuilesAuCentre;
    private Joueur[] joueurs;
    private Domino dominoSelect;
    private boolean[] dominoDejaPlace;
    private int nbJoueur;
    private Joueur[] ordreJoueurTourActuel;
    private Joueur[] ordreJoueurTourSuivant;
    private boolean faireUnNouveauTour;
    private int nbChangementJoueur;
    private int nbDominoCentre;


    public ModelTest() {
        paquet = new Paquet();
        paquet.shuffle();
        dominoDejaPlace = new boolean[4];
        faireUnNouveauTour = false;



    }

    public void setNbJoueur(int nbJoueur){
        this.nbJoueur = nbJoueur;
        joueurs = new Joueur[nbJoueur];
        if (nbJoueur == 3) nbDominoCentre = 3;
        else nbDominoCentre = 4;
        tuilesAuCentre = new TuilesAuCentre(paquet,nbDominoCentre);
        for (int i = 0; i < nbJoueur; i++) {
            joueurs[i]=new Joueur("",i);
        }
        ordreJoueurTourActuel = new Joueur[nbJoueur];
        ordreJoueurTourSuivant = new Joueur[nbJoueur];
        initOrdre();
    }


    private void initOrdre(){
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i] = joueurs[i];
        }
        setJoueurActuel(0);

    }

    public void changementJoueur(){
        if (getPosJoueurActuel() == nbJoueur-1){
            setJoueurActuel(0);
            nbChangementJoueur=0;
            faireUnNouveauTour = true;
        }
        else{
            setJoueurActuel(getPosJoueurActuel()+1);
            faireUnNouveauTour = false;
            nbChangementJoueur ++;
        }

    }

    public void nouveauIndextourSuivant(int posDom){
        ordreJoueurTourSuivant[posDom]=getJoueurActuel();
    }

    public void nouveauTour(){
        System.out.println("nex turn");
	    setFaireUnNouveauTour(false);
	    dominoDejaPlace = new boolean[4];
        for (int i = 0; i < nbJoueur; i++) {
            ordreJoueurTourActuel[i] = ordreJoueurTourSuivant[i];
        }
        tuilesAuCentre = new TuilesAuCentre(paquet,nbDominoCentre);
        setJoueurActuel(0);
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
        boolean fin =false;
        if (paquet.isEmpty()){
            fin = true;
            for (int i = 0; i>dominoDejaPlace.length; i++){
                if(dominoDejaPlace[i]){
                    fin = true;
                }else{
                    return false;
                }
            }
        }
        return fin;
    }

    public int calculScore(Joueur joueur){ //renvoie le score d'dun joueur
        int score=0;
        boolean memeTerrain;
        Royaume royaumeJoueur = joueur.getRoyaume();
        Royaume test = new Royaume(5);
        for (int i =0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                test.addTuille(new Tuile(Terrain.FORET,1),i,j);
                if(royaumeJoueur.getTuile(i,j).getCouronne()==1) {
                    score = 1;

                    if (i < 4) { //sinon ça fait OutOfBoundsException
                        if (royaumeJoueur.isMemeTerrain(royaumeJoueur.getTuile(i, j), royaumeJoueur.getTuile(i + 1, j))) {
                            System.out.println("Les tuiles ont le même terrain");
                            memeTerrain = true;
                        }
                    }
                    if (j < 4) { //sinon ça fait OutOfBoundsException
                        if (royaumeJoueur.isMemeTerrain(royaumeJoueur.getTuile(i, j), royaumeJoueur.getTuile(i, j + 1))) {
                            System.out.println("Les tuiles ont le même terrain");
                            memeTerrain = true;
                        }
                    }
                    if (i > 0) { //sinon ça fait OutOfBoundsException
                        if (royaumeJoueur.isMemeTerrain(royaumeJoueur.getTuile(i, j), royaumeJoueur.getTuile(i - 1, j))) {
                            System.out.println("Les tuiles ont le même terrain");
                            memeTerrain = true;
                        }
                    }
                    if (j > 0) { //sinon ça fait OutOfBoundsException
                        if (royaumeJoueur.isMemeTerrain(royaumeJoueur.getTuile(i, j), royaumeJoueur.getTuile(i, j - 1))) {
                            System.out.println("Les tuiles ont le même terrain");
                            memeTerrain = true;
                        }
                    }




                }

            }
        }

        joueur.setScore(score);
        return score;
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
}
