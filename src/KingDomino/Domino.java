package KingDomino;

import java.util.Collections;
import java.util.Comparator;

public class Domino {
	private int id;
	private Tuile tuile1;
	private Tuile tuile2;
	private boolean isDepart;

	public Domino(Tuile tuile1,Tuile tuile2) throws PasDominoDepartException {
		this(0,tuile1,tuile2);
	}

	public Domino(int id, Tuile tuile1,Tuile tuile2) throws PasDominoDepartException {
		isDepart = false;
		this.id = id;
		this.tuile1 = tuile1;
		this.tuile2 = tuile2;
		if (id>48) {
			isDepart = true;
			if (!verifDepart()) throw new PasDominoDepartException();
		}

	}

	private boolean verifDepart() {

		if ((tuile1.getTerrain() == Terrain.DEPART )
			&&(tuile2.getTerrain() == Terrain.DEPART )
			&&(tuile1.getCouronne() == 0 )
			&&(tuile1.getCouronne() == 0 )
		) return true;
		return false;
}

	public int getId() {
		return id;
	}

	public Tuile getTuile1() {
		return tuile1;
	}

	public Tuile getTuile2() {
		return tuile2;
	}

	public boolean isDepart() {
		return isDepart;
	}

	public void setId(int id) throws PasDominoDepartException {
		this.id = id;
		if (id>48) {
			isDepart = true;
			if (!verifDepart()) throw new PasDominoDepartException();
		}
	}


	@Override
	public String toString() {
		return "Domino{" +
				"id=" + id +
				", tuile1=" + tuile1 +
				", tuile2=" + tuile2 +
				", isDepart=" + isDepart +
				'}';
	}
}
