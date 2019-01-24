package Model;

/**
 * The type Tuiles au centre.
 */
public class TuilesAuCentre {
	/**
	 * The Domino tab.
	 */
	Domino[] dominoTab;
	/**
	 * The Paquet.
	 */
	Paquet paquet;

	/**
	 * Instantiates a new Tuiles au centre.
	 *
	 * @param paquet   the paquet
	 * @param nbDomino the nb domino
	 */
	public TuilesAuCentre(Paquet paquet,int nbDomino) {
		dominoTab = new Domino[nbDomino];
		this.paquet = paquet;
		for (int i = 0; i <nbDomino; i++) {
			dominoTab[i]= paquet.takeFirst();
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

	/**
	 * Get domino tab domino [ ].
	 *
	 * @return the domino [ ]
	 */
	public Domino[] getDominoTab() {
		return dominoTab;
	}

	/**
	 * Show tuiles au centre.
	 */
	public void showTuilesAuCentre(){
		for (Domino domino: dominoTab) {
			System.out.println(domino);
		}
	}

}
