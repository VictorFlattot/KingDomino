package KingDomino;

public class Royaume {
	private int taille;
	private Terrain[][] terrains;


	public Royaume(int taille) {
		this.taille=taille;
		initRoyaume();
		afficherRoyaume();

	}

	private void initRoyaume() {
		terrains = new Terrain[taille][taille];
		setDepart(2,2);
	}

	void setDepart(int x,int y){
		terrains[x][y] = Terrain.DEPART;
	}

	void afficherRoyaume(){
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				System.out.println("x="+i+" y="+j+" " +terrains[i][j]);
			}
		}
	}


}
