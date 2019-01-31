package Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paquet {
	List<Domino> dominos;
	List<Tuile> tuileList;

	public Paquet() {
		initTuiles();
		initDominos();
		showTuile();
	}

	private void initDominos() {
		dominos = new ArrayList<>();
		initDominoEntre1et12();
		initDominoEntre13et34();
		initDominoEntre34et48();

		setIdDominos();





	}
	private void initDominoEntre1et12() {
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
				dominos.add(new Domino(tuileList.get(i), tuileList.get(i)));
			}
		}
	}

	private void initDominoEntre13et34() {
        //13 a 15
		for (int i = 2; i <= 6; i+=2) {	dominos.add(new Domino(tuileList.get(0), tuileList.get(i)));}

        //16
		dominos.add(new Domino(tuileList.get(0), tuileList.get(9)));
        //17 et 18
		for (int i = 4; i <= 6; i+=2) {dominos.add(new Domino(tuileList.get(2), tuileList.get(i)));}
        // 19 a 23
		for (int i = 2; i <= 12; i+=2) {
			switch (i){
				case 8: i++;break;
				case 11: i++;break;
			}
			dominos.add(new Domino(tuileList.get(1), tuileList.get(i)));
		}
		//24 a 27
		for (int i = 0; i < 4; i++) {dominos.add(new Domino(tuileList.get(3), tuileList.get(0)));}
		//28 et 29
		for (int i = 4; i <= 6; i+=2) {dominos.add(new Domino(tuileList.get(3), tuileList.get(i)));}
		//30 a 35
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < i+2; j++) {
				dominos.add(new Domino(tuileList.get(5), tuileList.get(i*2)));
			}
		}
	}


	private void initDominoEntre34et48() {
	    //36
		dominos.add(new Domino(tuileList.get(5), tuileList.get(2)));
        dominos.add(new Domino(tuileList.get(7), tuileList.get(0)));
        dominos.add(new Domino(tuileList.get(7), tuileList.get(4)));
		dominos.add(new Domino(tuileList.get(7), tuileList.get(0)));
		dominos.add(new Domino(tuileList.get(10), tuileList.get(6)));
		dominos.add(new Domino(tuileList.get(13), tuileList.get(0)));
		dominos.add(new Domino(tuileList.get(8), tuileList.get(0)));
		dominos.add(new Domino(tuileList.get(8),tuileList.get(4)));
        dominos.add(new Domino(tuileList.get(8), tuileList.get(0)));
        dominos.add(new Domino(tuileList.get(11), tuileList.get(6)));
		dominos.add(new Domino(tuileList.get(14), tuileList.get(0)));
		for (int i = 0; i < 2; i++) { dominos.add(new Domino(tuileList.get(14), tuileList.get(9)));}
		dominos.add(new Domino(tuileList.get(15), tuileList.get(0)));
	}


	private void setIdDominos() {
		for (int i = 0; i < dominos.size(); i++) {
			dominos.get(i).setId(i+1);

		}

	}



	private void initTuiles() {
		tuileList = new ArrayList<>();
		for (Terrain terrain : Terrain.values()){
			for (int i = 0; i < 4; i++)
				switch (i){
					case 0:	tuileList.add(new Tuile(terrain, i)); break;
					case 1:	if (terrain != Terrain.DEPART) tuileList.add(new Tuile(terrain, i)); break;
					case 2:	if (terrain == Terrain.PRAIRIE || terrain == Terrain.MONTAGNES
							|| terrain == Terrain.MARAIS) tuileList.add(new Tuile(terrain, i));break;
					case 3:	if (terrain == Terrain.MONTAGNES) tuileList.add(new Tuile(terrain, i)); break;
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

	private void showTuile(){
		for (Tuile tuile: tuileList
		     ) {
			System.out.println(tuile);
		}
	}



}
