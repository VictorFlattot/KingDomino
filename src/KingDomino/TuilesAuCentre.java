package KingDomino;

public class TuilesAuCentre {
	Domino[] dominoTab;
	Paquet paquet;

	public TuilesAuCentre(Paquet paquet) {
		dominoTab = new Domino[4];
		this.paquet = paquet;
		for (int i = 0; i < 4; i++) {
			dominoTab[i]=paquet.takeFirst();
		}

		triOrdreCroissant();
		//showTuilesAuCentre();
	}


	private void triOrdreCroissant() {
		int i;
		int j;
		for (j = dominoTab.length-1; j > 0 ; j--) {
			for ( i = 0; i<j+1 ; i++) {
				if(dominoTab[j].getId()<dominoTab[i].getId()) permut(i,j);
			}
		}

	}

	private void permut(int i,int j) {
		Domino tmp;
		tmp = dominoTab[i];
		dominoTab[i]=dominoTab[j];
		dominoTab[j]=tmp;
	}

	public int choisirDomino(int id){

		if (isOnCenter(id)>0){
			Domino domino = dominoTab[isOnCenter(id)];
			domino.setEstChoisi(true);
			return isOnCenter(id);

		}return -1;
	}

	private int isOnCenter(int id) {
		for (int i = 0; i < 4; i++) {
			if (id == dominoTab[i].getId()) return i;
		}
		return -1;
	}

	public Domino[] getDominoTab() {
		return dominoTab;
	}

	public void showTuilesAuCentre(){
		for (Domino domino: dominoTab) {
			System.out.println(domino);
		}
	}

}
