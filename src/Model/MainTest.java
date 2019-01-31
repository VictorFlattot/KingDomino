package Model;

import Control.ControlCaseRoyaume;
import Model.*;
import Vues.Bouton;
import Vues.FenetreTest;

import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
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
		Domino domino = new Domino(new Tuile(Terrain.CHAMPS,0),new Tuile(Terrain.CHAMPS,0));
		/*royaume.showRoyaume();
		try {
			royaume.addDominoRoyaume(domino,3,1,3,2);
		} catch (UnconnectedException e) {
			e.printStackTrace();
		}
		*/
		model.setDominoSelect(domino);
		test(model);
		royaume.showRoyaume();
	}

	private static void test(ModelTest model) throws IOException {
		Bouton jButton = new Bouton();
		jButton.setActionCommand(""+ 3 +"/"+ 1);
		ControlCaseRoyaume controlCaseRoyaume = new ControlCaseRoyaume(model);
		controlCaseRoyaume.actionPerformed(new ActionEvent(jButton, ActionEvent.ACTION_PERFORMED, jButton.getActionCommand()));

	}
}
