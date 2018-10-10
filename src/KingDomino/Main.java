package KingDomino;

public class Main {
	public static void main(String[] args) throws Exception {
		Paquet paquet = new Paquet(true);
		paquet.shuffle();
		TuilesAuCentre tuilesAuCentre = new TuilesAuCentre(paquet);
	}
}
