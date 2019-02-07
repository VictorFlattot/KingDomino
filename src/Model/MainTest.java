package Model;

import Control.ControlCaseRoyaume;
import Model.*;
import Vues.Bouton;
import Vues.FenetreTest;
//import sun.util.resources.cldr.ee.TimeZoneNames_ee;

import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
import javax.swing.text.TabExpander;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainTest {
	public static void main(String[] args) throws IOException {
		TuilesAuCentre tuilesAuCentre;
		ModelTest model = new ModelTest();
		model = new ModelTest();
		model.setNbJoueur(4);
		System.out.println(model.getJoueurActuel());
		/*for (int i = 0; i < 4; i++) {
			model.changementJoueur();
		}
		for (int i = 0; i < 12; i++) {
			tuilesAuCentre = new TuilesAuCentre(model.getPaquet(),4,true);
		}


		*/
		Royaume royaume  = model.getJoueurActuel().getRoyaume();

        Domino doubleForet = new Domino(new Tuile(Terrain.FORET, 0), new Tuile(Terrain.FORET, 0));
        Domino foretChamp = new Domino(new Tuile(Terrain.FORET, 0), new Tuile(Terrain.CHAMPS, 0));
        Domino lacChamp = new Domino(new Tuile(Terrain.LAC, 0), new Tuile(Terrain.CHAMPS, 1));
        Domino doubleChamp = new Domino(new Tuile(Terrain.CHAMPS, 0), new Tuile(Terrain.CHAMPS, 0));
        Domino prairie1Champ = new Domino(new Tuile(Terrain.PRAIRIE, 1), new Tuile(Terrain.CHAMPS, 0));
        Domino champs1Prairie = new Domino(new Tuile(Terrain.CHAMPS, 1), new Tuile(Terrain.PRAIRIE, 0));
        Domino doubleMarais = new Domino(new Tuile(Terrain.MARAIS, 0), new Tuile(Terrain.MARAIS, 0));
        Domino lac1Foret = new Domino(new Tuile(Terrain.LAC, 1), new Tuile(Terrain.FORET, 0));
        Domino foret1Prairie = new Domino(new Tuile(Terrain.FORET, 1), new Tuile(Terrain.PRAIRIE, 0));

        Domino foret1Champ = new Domino(new Tuile(Terrain.FORET, 1), new Tuile(Terrain.CHAMPS, 0));
        Domino doublePrairie = new Domino(new Tuile(Terrain.PRAIRIE, 0), new Tuile(Terrain.PRAIRIE, 0));


		//royaume.showRoyaume();
        royaume.addDominoRoyaume(foretChamp,1,2,1,3);
        royaume.addDominoRoyaume(doubleForet,1,0,1,1);
        royaume.addDominoRoyaume(lacChamp,0,2,0,3);
        royaume.addDominoRoyaume(doubleChamp,0,4,1,4);
        royaume.addDominoRoyaume(champs1Prairie,3,1,2,1);
        royaume.addDominoRoyaume(prairie1Champ,3,0,2,0);
        royaume.addDominoRoyaume(doubleMarais, 3,2,4,2);
        royaume.addDominoRoyaume(lac1Foret,2,3,3,3);
        royaume.addDominoRoyaume(foret1Prairie,4,3,4,4);
		model.calculScore(model.getJoueurActuel());
		model.setDominoSelect(foret1Champ);
		//test(model);
		royaume.showRoyaume();
	}

	private static void test(ModelTest model) throws IOException {
		Bouton jButton = new Bouton();
		jButton.setActionCommand(""+ 3 +"/"+ 1);
		ControlCaseRoyaume controlCaseRoyaume = new ControlCaseRoyaume(model);
		controlCaseRoyaume.actionPerformed(new ActionEvent(jButton, ActionEvent.ACTION_PERFORMED, jButton.getActionCommand()));

	}
}
