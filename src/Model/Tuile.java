package Model;

/**
 * The type Tuile.
 */
public class Tuile {
	private int id;
	private int nbCouronne;
	private Terrain terrain;

	/**
	 * Instantiates a new Tuile.
	 *
	 * @param terrain    the terrain
	 * @param nbCouronne the nb couronne
	 */
	public Tuile(Terrain terrain, int nbCouronne) {
		this.terrain = terrain;
		this.nbCouronne=nbCouronne;
		if (terrain != null) setId();
		else id=-1;
	}

	/**
	 * Gets terrain.
	 *
	 * @return the terrain
	 */
	public Terrain getTerrain() {
		return terrain;
	}


	/**
	 * Gets couronne.
	 *
	 * @return the couronne
	 */
	public int getCouronne() {
		return nbCouronne;
	}

	@Override
	public String toString() {
		return "Tuile{" +
				"id=" + id +
				", nbCouronne=" + nbCouronne +
				", terrain=" + terrain +
				'}';
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 */
	public void setId() {
		switch (terrain){
			case DEPART: id = 16; break;
			case CHAMPS: id=nbCouronne; break;
			case FORET: id=nbCouronne+2; break;
			case LAC: id=nbCouronne+4; break;
			case PRAIRIE: id=nbCouronne+6;break;
			case MARAIS: id=nbCouronne+9; break;
			case MONTAGNES: id=nbCouronne+12;break;
		}
	}
}
