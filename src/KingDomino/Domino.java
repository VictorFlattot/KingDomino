package KingDomino;

public class Domino {
	private int id;
	private Tuile tuileNord;
	private Tuile tuileSud;
	private Tuile tuileEst;
	private Tuile tuileOuest;
	private int rotation;

	public Domino(Tuile tuile1,Tuile tuile2){
		this(0,tuile1,tuile2);
	}

	public Domino(int id, Tuile tuile1,Tuile tuile2) {
		this.id = id;
		this.tuileNord = null;
		this.tuileSud = null;
		this.tuileOuest = tuile1;
		this.tuileEst = tuile2;
		this.rotation = 0;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Tuile getTuileNord() {
		return tuileNord;
	}

	public void setTuileNord(Tuile tuileNord) {
		this.tuileNord = tuileNord;
	}

	public Tuile getTuileSud() {
		return tuileSud;
	}

	public void setTuileSud(Tuile tuileSud) {
		this.tuileSud = tuileSud;
	}

	public Tuile getTuileEst() {
		return tuileEst;
	}

	public void setTuileEst(Tuile tuileEst) {
		this.tuileEst = tuileEst;
	}

	public Tuile getTuileOuest() {
		return tuileOuest;
	}

	public void setTuileOuest(Tuile tuileOuest) {
		this.tuileOuest = tuileOuest;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Tuile[] getTuiles(){
        System.out.println(rotation);
        Tuile[] tuiles = new Tuile[2];
        if (rotation==0){
            tuiles[0] = tuileOuest;
            tuiles[1] = tuileEst;
        }
        if (rotation==180){
            tuiles[0] = tuileEst;
            tuiles[1] = tuileOuest;
        }
        if (rotation==90 || rotation==270){
            tuiles[0] = tuileNord;
            tuiles[1] = tuileSud;
        }

        return tuiles;
    }
}

