package KingDomino;

public class Tuile {
	private int nbCouronne;
	private Terrain terrain;

	public Tuile(Terrain terrain, int nbCouronne) {
		this.terrain = terrain;
		this.nbCouronne=nbCouronne;
	}

	public Terrain getTerrain() {
		return terrain;
	}


	public int getCouronne() {
		return nbCouronne;
	}

	@Override
	public String toString() {
		return "Tuile{" +
				"nbCouronne=" + nbCouronne +
				", terrain=" + terrain +
				'}';
	}
}
