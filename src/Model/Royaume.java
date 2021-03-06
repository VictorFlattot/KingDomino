package Model;

public class Royaume {
	private int taille;
	private Tuile[][] tuiles;
	private Tuile depart;
	private boolean[][] checkcedTuiles;
	private int[][] rotation;


	public Royaume(int taille) {
		this.taille=taille;
		initRoyaume();
		//showRoyaume();

	}

    public int getRotation(int x, int y) {
        return rotation[x][y];
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
		rotation = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
                rotation[i][j] = 0;
				addTuille(new Tuile(null,0),i,j, 0);
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
			addTuille(tuilesDomino[0], x, y, domino.getRotation());
			addTuille(tuilesDomino[1], x2, y2, (domino.getRotation()+180)%360);
			//showRoyaume();
		}else{
			throw new UnconnectedException();
		}


	}
	void removeDominoRoyaume(int x, int y,int x2,int y2){
		tuiles[x][y] = new Tuile(null,0);
		tuiles[x2][y2] = new Tuile(null,0);
	}

	public boolean verifPlacement(Domino domino, int x, int y, int x2, int y2) throws UnconnectedException {
		if(isTuileDejaPlace(x,y)) return false;

		if (isTuileDejaPlace(x2, y2)) return false;


		if(!isConnectedToTuile(domino, x, y)) return false;

		return true;

	}


	public boolean isTuileDejaPlace(int x, int y){
		return getTuile(x, y).getTerrain() != null;
	}


	void addTuille(Tuile tuile,int x,int y, int rot){
		//if (x == getDepart()[0] && x == getDepart()[1] || 0 < x || 0 < y)
			tuiles[x][y] = tuile;
			rotation[x][y] = rot;
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

	public void showRoyaume(){
	    for (int i = 0; i < 3*taille; i++){
			switch (i%3){
				case 0:
					line();
					break;
				case 1:
					for (int j = 0; j < taille; j++)
					    if (tuiles[i/3][j].getTerrain() != null)
						    System.out.print("| " + tuiles[i/3][j].getCouronne());
					    else
                            System.out.print("|  ");
					System.out.println("|");
					break;
				case 2:
					for (int j = 0; j < taille; j++){
						if (tuiles[i/3][j].getTerrain() != null) {
							switch (tuiles[i/3][j].getTerrain()) {
								case DEPART:
									System.out.print("|De");
									break;
								case LAC:
									System.out.print("|La");
									break;
								case FORET:
									System.out.print("|Fo");
									break;
								case CHAMPS:
									System.out.print("|Ch");
									break;
								case MARAIS:
									System.out.print("|Ma");
									break;
								case PRAIRIE:
									System.out.print("|Pr");
									break;
								case MONTAGNES:
									System.out.print("|Mo");
									break;
								default:
									System.out.print("|  ");
									break;
							}
						} else System.out.print("|  ");
					}
					System.out.println("|");
					break;
			}
        }
	    line();
    }

    public boolean isEntourerParTerrain(int x, int y){
	    int caseBloquee = 0;
	    if (tuiles[x][y].getTerrain() != null) return false;
	    if (x-1 >= 0){
	        if (tuiles[x-1][y].getTerrain() != null) caseBloquee++;
        } else caseBloquee++;
        if (x+1 < taille){
            if (tuiles[x+1][y].getTerrain() != null) caseBloquee++;
        }else caseBloquee++;
        if (y-1 >= 0){
            if (tuiles[x][y-1].getTerrain() != null) caseBloquee++;
        } else  caseBloquee++;
        if (y-1 >= 0){
            if (tuiles[x][y-1].getTerrain() != null) caseBloquee++;
        } else caseBloquee++;

		return (caseBloquee > 3);
    }

    private void line(){
        for (int i = 0; i <= 3*taille; i++){
            if (i%3 == 0)   System.out.print('+');
            else            System.out.print('-');
        }
        System.out.println();
    }

	public boolean isConnectedToTuile(Domino domino, int x, int y){
	    //if ((x+1) < taille && (x-1) >= 0 && (y+1) < taille && (y-1) >= 0){
			if (checkConnection(domino, x, y)){
				//System.out.println("Fonction OK");
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

				if (x+1 < taille && isMemeTerrain(domino.getTuileOuest(),getTuile(x+1,y))) {
					return true;
				}
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
