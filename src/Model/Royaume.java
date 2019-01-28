package Model;

/**
 * The type Royaume.
 */
public class Royaume {
	private int taille;
	private Tuile[][] tuiles;
	private Tuile depart;


	/**
	 * Instantiates a new Royaume.
	 *
	 * @param taille the taille
	 */
	public Royaume(int taille) {
		this.taille=taille;
		initRoyaume();
		//showRoyaume();
	}

	private void initRoyaume() {
		tuiles = new Tuile[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				addTuille(new Tuile(null,0),i,j);
			}
		}
		setDepart(taille/2,taille/2);
	}

	/**
	 * Set depart.
	 *
	 * @param x the x
	 * @param y the y
	 */
	void setDepart(int x,int y){
		tuiles[x][y] = new Tuile(Terrain.DEPART,0);
	}

	/**
	 * Matrice tuile [ ] [ ].
	 *
	 * @return the tuile [ ] [ ]
	 */
	Tuile[][] matrice(){
		return tuiles;
	}

	/**
	 * Add domino royaume.
	 *
	 * @param domino the domino
	 * @param x      the x
	 * @param y      the y
	 * @param x2     the x 2
	 * @param y2     the y 2
	 * @throws UnconnectedException the unconnected exception
	 */
	void addDominoRoyaume(Domino domino, int x, int y,int x2,int y2)throws UnconnectedException {
	    if(isTuileDejaPlace(x,y)){
	        throw new UnconnectedException();
        }
        if (isTuileDejaPlace(x2, y2)) {
            throw new UnconnectedException();
        }

		if(isConnectedToTuile(domino, x, y)){
			/*if(x>=matrice().length || x<0 || y>=matrice().length || y<0 || x2>=matrice().length || x<0 || y2>=matrice().length||y2<0) {
				throw new ArrayIndexOutOfBoundsException();
			}else{*/
				Tuile[] tuilesDomino = domino.getTuiles();
				addTuille(tuilesDomino[0], x, y);
				addTuille(tuilesDomino[1], x2, y2);
				//showRoyaume();
			//}
		}else {
			throw new UnconnectedException();
		}
	}

	/**
	 * Is tuile deja place boolean.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the boolean
	 */
	public boolean isTuileDejaPlace(int x, int y){
		return getTuile(x, y).getTerrain() != null;
	}

	/**
	 * Add tuille.
	 *
	 * @param tuile the tuile
	 * @param x     the x
	 * @param y     the y
	 */
	void addTuille(Tuile tuile,int x,int y){
		//if (x == getDepart()[0] && x == getDepart()[1] || 0 < x || 0 < y)
			tuiles[x][y] = tuile;
	}

	/**
	 * Get tuile tuile.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the tuile
	 */
	public Tuile getTuile(int x,int y){
		return tuiles[x][y];
	}

	/**
	 * Get depart int [ ].
	 *
	 * @return the int [ ]
	 */
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

	/**
	 * Show royaume.
	 */
	void showRoyaume(){
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				System.out.println(i +"/" + j +":"+tuiles[i][j]);
			}
		}
	}

	/**
	 * Is connected to tuile boolean.
	 *
	 * @param domino the domino
	 * @param x      the x
	 * @param y      the y
	 * @return the boolean
	 */
	public boolean isConnectedToTuile(Domino domino, int x, int y){
	    //if ((x+1) < taille && (x-1) >= 0 && (y+1) < taille && (y-1) >= 0){
			if (checkConnection(domino, x, y)){
				System.out.println("Fonction OK");
				return true;
			}
        //}
        return false;
    }

	/**
	 * Is meme terrain boolean.
	 *
	 * @param base    the base
	 * @param compare the compare
	 * @return the boolean
	 */
	public boolean isMemeTerrain(Tuile base, Tuile compare){
		System.out.println("Domino : " + base.getTerrain() + " | Royaume : " + compare.getTerrain());
	    return (base.getTerrain()==compare.getTerrain()) || (compare.getTerrain() == Terrain.DEPART);
    }

    private boolean checkConnection(Domino domino, int x, int y){
		System.out.println("Terrain Nord : " + domino.getTuileNord());
		System.out.println("Terrain Ouest : " + domino.getTuileOuest());
		System.out.println("Terrain Est : " + domino.getTuileEst());
		System.out.println("Terrain Sud : " + domino.getTuileSud());
		System.out.println("x: " + x + " | y: " + y);

	    switch (domino.getRotation()){
            case 0:
			case 180:
				System.out.println("=== Rotation  0/180 ===");
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
				System.out.println("=== Rotation 90/270 ===");
				if (y-1 > -1 && isMemeTerrain(domino.getTuileSud(),getTuile(x,y-1)))
					return true;
				if (y+1 < taille && isMemeTerrain(domino.getTuileSud(),getTuile(x,y+1)))
					return true;
				if (x+1 < taille && isMemeTerrain(domino.getTuileSud(),getTuile(x+1,y)))
					return true;
				if (x-2 > -1 && isMemeTerrain(domino.getTuileNord(),getTuile((x-1)-1,y)))
					return true;
				if (x-1 > -1 && y-1 > -1 &&isMemeTerrain(domino.getTuileNord(),getTuile((x-1),y-1)))
					return true;
				return (x-1 > -1 && y-1 > -1 && isMemeTerrain(domino.getTuileNord(),getTuile((x-1),y+1)));

			default:
				return false;
        }
    }

}
