package Model;

public class TuilesAuCentre {
	Domino[] dominoTab;
	Paquet paquet;


	public TuilesAuCentre(Paquet paquet, int nbDomino, boolean distrib) {
		dominoTab = new Domino[nbDomino];
		this.paquet = paquet;
		if (distrib && paquet.size()>0) distribuer();
		//showTuilesAuCentre();


	}

	private void distribuer(){
		for (int i = 0; i <dominoTab.length; i++) {
			dominoTab[i]= paquet.takeFirst();
		}
		//System.out.println(paquet.size());
		triOrdreCroissant();
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

	public Domino[] getDominoTab() {
		return dominoTab;
	}

	public void showTuilesAuCentre(){
		for (Domino domino: dominoTab) {
			System.out.println(domino);
		}
	}

}
