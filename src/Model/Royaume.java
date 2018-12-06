package Model;

public class Royaume {
	private int taille;
	private Tuile[][] tuiles;
	private Tuile depart;


	public Royaume(int taille) {
		this.taille=taille;
		initRoyaume();
		showRoyaume();



	}

	private void initRoyaume() {
		tuiles = new Tuile[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				addTuille(new Tuile(null,0),i,j);
			}
		}
		setDepart(2,2);
	}

	void setDepart(int x,int y){
		tuiles[x][y] = new Tuile(Terrain.DEPART,0);
	}


	Tuile[][] matrice(){
		return tuiles;
	}

	void addDominoRoyaume(Domino domino, int x, int y){
		Tuile[] tuilesDomino = domino.getTuiles();
		addTuille(tuilesDomino[0],x,y);
		int rotation = domino.getRotation();
				if (rotation==0 || rotation==180) addTuille(tuilesDomino[1],x+1,y);
				if (rotation==90 || rotation==270) addTuille(tuilesDomino[1],x,y+1);
				showRoyaume();

	}

	void addTuille(Tuile tuile,int x,int y){
		tuiles[x][y] = tuile;
	}

	Tuile getTuile(int x,int y){
		return tuiles[x][y];
	}

	public int[] getDepart() {
		int[] coordDepart = new int[2];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (tuiles[i][j].getTerrain() == Terrain.DEPART){
					coordDepart[0]=i;
					coordDepart[1]=j;
				}
			}
		}
		return null;
	}

	void showRoyaume(){
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.println(tuiles[i][j]);
			}
		}
	}

}
