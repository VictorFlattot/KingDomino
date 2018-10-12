package KingDomino;

public class Domino {
	private int id;
	private Tuile tuile1;
	private Tuile tuile2;
	private boolean estChoisi;

	public Domino(Tuile tuile1,Tuile tuile2){
		this(0,tuile1,tuile2);
	}

	public Domino(int id, Tuile tuile1,Tuile tuile2) {
		this.id = id;
		this.tuile1 = tuile1;
		this.tuile2 = tuile2;
		this.id = id;
		estChoisi=false;


	}

	private boolean verifDepart() {

		return (tuile1.getTerrain() == Terrain.DEPART)
				&& (tuile2.getTerrain() == Terrain.DEPART)
				&& (tuile1.getCouronne() == 0)
				&& (tuile1.getCouronne() == 0);
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


	public void setId(int id) {
		this.id = id;
	}

	public boolean isEstChoisi() {
		return estChoisi;
	}

	public void setEstChoisi(boolean estChoisi) {
		this.estChoisi = estChoisi;
	}

	@Override
	public String toString() {
		return "Domino{" +
				"id=" + id +
				", tuile1=" + tuile1 +
				", tuile2=" + tuile2 +
				", estChoisi=" + estChoisi +
				'}';
	}
}
