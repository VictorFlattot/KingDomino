package Model;

public class Royaume {
	private int taille;
	private Tuile[][] tuiles;
	private Tuile depart;
	private boolean[][] checkcedTuiles;


	public Royaume(int taille) {
		this.taille=taille;
		initRoyaume();
		//showRoyaume();

	}

	public boolean isTuileChecked(int x, int y){
		return checkcedTuiles[x][y];
	}

	public void setTuileCheckStatut(int x, int y, boolean statut){
		checkcedTuiles[x][y] = statut;
	}

	private void initRoyaume() {
		tuiles = new Tuile[taille][taille];
		checkcedTuiles = new boolean[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				addTuille(new Tuile(null,0),i,j);
				checkcedTuiles[i][j] = false;
			}
		}
		setDepart(taille/2,taille/2);
	}

	void setDepart(int x,int y){
		tuiles[x][y] = new Tuile(Terrain.DEPART,0);
	}

	Tuile[][] matrice(){
		return tuiles;
	}

	void addDominoRoyaume(Domino domino, int x, int y,int x2,int y2)throws UnconnectedException {
		if (verifPlacement(domino, x, y, x2, y2)){
			Tuile[] tuilesDomino = domino.getTuiles();
			addTuille(tuilesDomino[0], x, y);
			addTuille(tuilesDomino[1], x2, y2);
			showRoyaume();
		}else{
			throw new UnconnectedException();
		}


	}

	public boolean verifPlacement(Domino domino, int x, int y, int x2, int y2) throws UnconnectedException {
		if(isTuileDejaPlace(x,y)) return false;

		if (isTuileDejaPlace(x2, y2))return false;


		if(!isConnectedToTuile(domino, x, y)) return false;

		return true;

	}

	public boolean isTuileDejaPlace(int x, int y){
		return getTuile(x, y).getTerrain() != null;
	}

	void addTuille(Tuile tuile,int x,int y){
		//if (x == getDepart()[0] && x == getDepart()[1] || 0 < x || 0 < y)
			tuiles[x][y] = tuile;
	}

	public Tuile getTuile(int x,int y){
		return tuiles[x][y];
	}

	public int[] getDepart() {
		int[] coordDepart = new int[2];
		for (int i = 0; i < taille; i++){
			for (int j = 0; j < taille; j++) {
				if (tuiles[i][j] == depart){
					coordDepart[0]=i;
					coordDepart[1]=j;
				}
			}
		}
		return null;
	}

	void showRoyaume(){
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				System.out.println(i +"/" + j +":"+tuiles[i][j]);
			}
		}
	}

	public boolean isConnectedToTuile(Domino domino, int x, int y){
	    //if ((x+1) < taille && (x-1) >= 0 && (y+1) < taille && (y-1) >= 0){
			if (checkConnection(domino, x, y)){
				System.out.println("Fonction OK");
				return true;
			}
        //}
        return false;
    }

    public boolean isMemeTerrain(Tuile base, Tuile compare){
		//System.out.println("Domino : " + base.getTerrain() + " | Royaume : " + compare.getTerrain());
	    return (base.getTerrain()==compare.getTerrain()) || (compare.getTerrain() == Terrain.DEPART);
    }

    private boolean checkConnection(Domino domino, int x, int y){
		/*System.out.println("Terrain Nord : " + domino.getTuileNord());
		System.out.println("Terrain Ouest : " + domino.getTuileOuest());
		System.out.println("Terrain Est : " + domino.getTuileEst());
		System.out.println("Terrain Sud : " + domino.getTuileSud());
		System.out.println("x: " + x + " | y: " + y);*/

	    switch (domino.getRotation()){
            case 0:
			case 180:
				//System.out.println("=== Rotation  0/180 ===");
            	if (y-1 > -1 && isMemeTerrain(domino.getTuileOuest(),getTuile(x,y-1)))
					return true;
				if (x-1 > -1 && isMemeTerrain(domino.getTuileOuest(),getTuile(x-1,y)))
					return true;
				if (x+1 < taille && isMemeTerrain(domino.getTuileOuest(),getTuile(x+1,y)))
					return true;
				if (y+2 < taille && isMemeTerrain(domino.getTuileEst(),getTuile(x,(y+1)+1)))
					return true;
				if (x-1 > -1 && y+1 < taille && isMemeTerrain(domino.getTuileEst(),getTuile(x-1,(y+1))))
					return true;
				return (x+1 < taille && y+1 < taille && isMemeTerrain(domino.getTuileEst(),getTuile(x+1,(y+1))));

            case 90:
			case 270:
				//System.out.println("=== Rotation 90/270 ===");
				if (y-1 > -1 && isMemeTerrain(domino.getTuileSud(),getTuile(x,y-1)))
					return true;
				if (y+1 < taille && isMemeTerrain(domino.getTuileSud(),getTuile(x,y+1)))
					return true;
				if (x+1 < taille && isMemeTerrain(domino.getTuileSud(),getTuile(x+1,y)))
					return true;
				if (x-2 > -1 && isMemeTerrain(domino.getTuileNord(),getTuile((x-1)-1,y)))
					return true;
				if (x-1 > -1 && y-1 > -1 && isMemeTerrain(domino.getTuileNord(),getTuile((x-1),y-1)))
					return true;
				return (x-1 > -1 && y+1 < taille && isMemeTerrain(domino.getTuileNord(),getTuile((x-1),y+1)));

			default:
				return false;
        }
    }

}
