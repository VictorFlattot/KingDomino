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
		showTuilesAuCentre();
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


	public void showTuilesAuCentre(){
		for (Domino domino: dominoTab) {
			System.out.println(domino);
		}
	}
}
