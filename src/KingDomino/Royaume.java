package KingDomino;

public class Royaume {
	private int taille;
	private Tuile[][] tuiles;
	private Tuile depart;


	public Royaume(int taille) {
		this.taille=taille;
		initRoyaume();
		System.out.println(depart);


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

	void addDominoRoyaume(Domino domino,int x1,int y1,int x2,int y2){
		addTuille(domino.getTuile1(),x1,y1);
		addTuille(domino.getTuile2(),x2,y2);

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

}
