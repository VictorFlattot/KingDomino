package KingDomino;


import java.util.*;

public class Paquet {
	List<Domino> dominos;
	List<Tuile> tuiles;

	public Paquet(boolean initDomino) throws Exception {
		if (initDomino){
			initTuiles();
			initDominos();
		}
		//showTuiles();
		//showDominos();
	}

	private void initDominos() throws Exception {
		dominos = new ArrayList<>();
		initDominoEntre1et12();
		initDominoEntre13et34();
		initDominoEntre34et48();

		setIdDominos();




	}
	private void initDominoEntre1et12() throws PasDominoDepartException {
		int nbDominoDouble = 0;
		for (int i = 0; i <= 8; i+=2) {
			switch (i){
				case 0: nbDominoDouble = 2;break;
				case 2: nbDominoDouble = 4;break;
				case 4: nbDominoDouble = 3;break;
				case 6: nbDominoDouble = 2;break;
				case 8: i++;nbDominoDouble = 1;break;
			}
			for (int j = 0; j < nbDominoDouble; j++) {
				dominos.add(new Domino(tuiles.get(i),tuiles.get(i)));
			}
		}
	}

	private void initDominoEntre13et34() throws PasDominoDepartException {

		for (int i = 2; i <= 6; i+=2) {	dominos.add(new Domino(tuiles.get(i),tuiles.get(0)));}
		dominos.add(new Domino(tuiles.get(9),tuiles.get(6)));
		for (int i = 4; i <= 6; i+=2) {dominos.add(new Domino(tuiles.get(i),tuiles.get(2)));}

		for (int i = 2; i <= 12; i+=2) {
			switch (i){
				case 8: i++;break;
				case 11: i++;break;
			}
			dominos.add(new Domino(tuiles.get(i),tuiles.get(1)));
		}
		for (int i = 0; i < 4; i++) {dominos.add(new Domino(tuiles.get(0),tuiles.get(3)));}
		for (int i = 4; i <= 6; i+=2) {dominos.add(new Domino(tuiles.get(i),tuiles.get(3)));}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < i+2; j++) {
				dominos.add(new Domino(tuiles.get(i*2),tuiles.get(5)));
			}
		}
	}


	private void initDominoEntre34et48() throws PasDominoDepartException {
		dominos.add(new Domino(tuiles.get(4),tuiles.get(3)));
		for (int i = 7; i <=10 ; i+=3) {
			dominos.add(new Domino(tuiles.get(i),tuiles.get(0)));
			dominos.add(new Domino(tuiles.get(i),tuiles.get(i/2+1)));
		}
		dominos.add(new Domino(tuiles.get(0),tuiles.get(13)));
		for (int i = 0; i <=4 ; i+=4) {	dominos.add(new Domino(tuiles.get(8),tuiles.get(i)));}
		for (int i = 0; i <=6 ; i+=6) { dominos.add(new Domino(tuiles.get(11),tuiles.get(i)));}
		dominos.add(new Domino(tuiles.get(0),tuiles.get(14)));
		for (int i = 0; i < 2; i++) { dominos.add(new Domino(tuiles.get(14),tuiles.get(9)));}
		dominos.add(new Domino(tuiles.get(15),tuiles.get(0)));
	}


	private void setIdDominos() throws PasDominoDepartException {
		for (int i = 0; i < dominos.size(); i++) {
			dominos.get(i).setId(i+1);
		}
	}



	private void initTuiles() {
		tuiles = new ArrayList<>();
		for (Terrain terrain : Terrain.values()){
			for (int i = 0; i < 4; i++)
				switch (i){
					case 0:	tuiles.add(new Tuile(terrain, i)); break;
					case 1:	if (terrain != Terrain.DEPART) tuiles.add(new Tuile(terrain, i)); break;
					case 2:	if (terrain == Terrain.PRAIRIE || terrain == Terrain.MONTAGNES
						|| terrain == Terrain.MARAIS) tuiles.add(new Tuile(terrain, i));break;
					case 3:	if (terrain == Terrain.MONTAGNES) tuiles.add(new Tuile(terrain, i)); break;
				}
		}
	}

	public boolean isEmpty(){
		return dominos.isEmpty();
	}

	public int size(){
		return dominos.size();
	}

	public Domino takeFirst() {
		Domino domino = dominos.get(0);
		dominos.remove(domino);
		return domino;
	}

	public void shuffle() {
		Collections.shuffle(dominos);

	}



	private void showTuiles() {
		for (Tuile tuile :
				tuiles) {
			System.out.println(tuile);
		}
	}
	private void showDominos() {
			for (Domino domino :
					dominos) {
				System.out.println(domino);
			}
		}

}
