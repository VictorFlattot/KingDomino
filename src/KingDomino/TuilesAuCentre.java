package KingDomino;

import java.util.*;



public class TuilesAuCentre {
	List<Domino> dominoList;
	Paquet paquet;

	public TuilesAuCentre(Paquet paquet) {
		dominoList = new ArrayList<>();
		this.paquet = paquet;
		for (int i = 0; i < 4; i++) {
			addDomino(paquet.takeFirst());
		}
		triOrdreCroissant();
		showTuilesAuCentre();
	}


	private void triOrdreCroissant() {


		Domino maxId=dominoList.get(0);
		for (int i = 0; i < dominoList.size(); i++) {
			if (dominoList.get(i).getId()>maxId.getId()){

			}
		}
	}

	private void addDomino(Domino domino){
		dominoList.add(domino);
	}


	public void showTuilesAuCentre(){
		for (Domino domino: dominoList) {
			System.out.println(domino);
		}
	}
}
