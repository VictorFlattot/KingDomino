package Model;

/**
 * The type Domino.
 */
public class Domino {

	/**
     * Valeur entière unique pour chaque domino
     */
	private int id;

	/**
     * Attribut du domino; Tuile Nord
     *
     * @see Tuile
     */
	private Tuile tuileNord;

	/**
     * Attribut du domino; Tuile Sud
     *
     * @see Tuile
     */
	private Tuile tuileSud;

    /**
     * Attribut du domino; Tuile Est
     *
     * @see Tuile
     */
	private Tuile tuileEst;

    /**
     * Attribut du domino; Tuile Ouest
     *
     * @see Tuile
     */
	private Tuile tuileOuest;

    /**
     * Rotation du domino <i>(0, 90, 180 ou 270)</i>
     */
	private int rotation;

	/**
	 * Instantiates a new Domino.
	 *
	 * @param tuile1 the tuile 1
	 * @param tuile2 the tuile 2
	 */
	public Domino(Tuile tuile1,Tuile tuile2){
		this(0,tuile1,tuile2);
	}

	/**
	 * Instantiates a new Domino.
	 *
	 * @param id     the id
	 * @param tuile1 the tuile 1
	 * @param tuile2 the tuile 2
	 */
	public Domino(int id, Tuile tuile1,Tuile tuile2) {
		this.id = id;
		this.tuileNord = null;
		this.tuileSud = null;
		this.tuileOuest = tuile1;
		this.tuileEst = tuile2;
		this.rotation = 0;
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
	 *
	 * @param id the id
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Gets tuile nord.
	 *
	 * @return the tuile nord
	 */
	public Tuile getTuileNord() {
		return tuileNord;
	}

	/**
	 * Sets tuile nord.
	 *
	 * @param tuileNord the tuile nord
	 */
	public void setTuileNord(Tuile tuileNord) {
		this.tuileNord = tuileNord;
	}

	/**
	 * Gets tuile sud.
	 *
	 * @return the tuile sud
	 */
	public Tuile getTuileSud() {
		return tuileSud;
	}

	/**
	 * Sets tuile sud.
	 *
	 * @param tuileSud the tuile sud
	 */
	public void setTuileSud(Tuile tuileSud) {
		this.tuileSud = tuileSud;
	}

	/**
	 * Gets tuile est.
	 *
	 * @return the tuile est
	 */
	public Tuile getTuileEst() {
		return tuileEst;
	}

	/**
	 * Sets tuile est.
	 *
	 * @param tuileEst the tuile est
	 */
	public void setTuileEst(Tuile tuileEst) {
		this.tuileEst = tuileEst;
	}

	/**
	 * Gets tuile ouest.
	 *
	 * @return the tuile ouest
	 */
	public Tuile getTuileOuest() {
		return tuileOuest;
	}

	/**
	 * Sets tuile ouest.
	 *
	 * @param tuileOuest the tuile ouest
	 */
	public void setTuileOuest(Tuile tuileOuest) {
		this.tuileOuest = tuileOuest;
	}

	/**
	 * Gets rotation.
	 *
	 * @return the rotation
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * Sets rotation.
	 *
	 * @param rotation the rotation
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	/**
	 * Get tuiles tuile [ ].
	 *
	 * @return the tuile [ ]
	 */
	public Tuile[] getTuiles(){

        Tuile[] tuiles = new Tuile[2];
        if (rotation==0 || rotation==180){
            tuiles[0] = tuileOuest;
            tuiles[1] = tuileEst;
        }

        if (rotation==90 || rotation==270){
			tuiles[0] = tuileSud;
			tuiles[1] = tuileNord;
		}

        return tuiles;
    }

	@Override
	public String toString() {
		return "Domino{" +
				"id=" + id +
				", tuileNord=" + tuileNord +
				", tuileSud=" + tuileSud +
				", tuileEst=" + tuileEst +
				", tuileOuest=" + tuileOuest +
				", rotation=" + rotation +
				'}';
	}
}

