package Model;

public class Tuile {
	private int id;
	private int nbCouronne;
	private Terrain terrain;
	private boolean checked = false;

	public Tuile(Terrain terrain, int nbCouronne) {
		this.terrain = terrain;
		this.nbCouronne=nbCouronne;
		if (terrain != null) setId();
		else id=-1;
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
				"id=" + id +
				", nbCouronne=" + nbCouronne +
				", terrain=" + terrain +
				'}';
	}

	public int getId() {
		return id;
	}

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

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
