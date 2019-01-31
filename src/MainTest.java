import Model.*;

public class MainTest {
	public static void main(String[] args) {
		ModelTest model = new ModelTest();
		TuilesAuCentre tuilesAuCentre;
		model.setNbJoueur(4);
		for (int i = 0; i < 4; i++) {
			model.changementJoueur();
		}
		for (int i = 0; i < 12; i++) {
			tuilesAuCentre = new TuilesAuCentre(model.getPaquet(),4,true);
		}

	}
}
