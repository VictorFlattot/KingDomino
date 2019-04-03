package Model;

import Control.ControlCaseRoyaume;
import Vues.Bouton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * La classe Main pour les test des différentes fonctionalités sur différents royaumes
 */
public class MainTest {
	public static void main(String[] args) throws IOException, UnconnectedException {
		ModelTest model = new ModelTest();
		model.setNbJoueur(4);
		model.getJoueur(0).setNom("Bleu");
		model.getJoueur(1).setNom("Jaune");
		model.getJoueur(2).setNom("Rouge");
		model.getJoueur(3).setNom("Vert");
		model.test = true;
		//test(model);
        //testRoyaume(model);
		prodK2Test(model); //Renvoie vers la fonction qui marche
		/*
		for (int a = 0; a < 1; a++) {
			for (int i = 0; i < 4; i++) {
				System.out.println("Dom Choisi : " + domChoisi);
				model.setDominoDejaChoisi(domChoisi,true);
				model.changementJoueur();
				domChoisi = model.quelDomPourIA();
			}
		}*/
	}

    private static void testRoyaume(ModelTest model) throws UnconnectedException {
        Royaume royaume  = model.getJoueurActuel().getRoyaume(); //récupère le royaume du joueur actuelle

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
        royaume.addDominoRoyaume(doubleMarais, 3,2,4,2);
        royaume.addDominoRoyaume(lac1Foret,2,3,3,3);
        royaume.addDominoRoyaume(foret1Prairie,4,3,4,4);
        System.out.println(model.calculScore(model.getJoueurActuel()));

        model.setDominoSelect(foret1Champ);
        royaume.showRoyaume();
        for (int i = 0; i < 3; i++) {
            int[] coord = model.ouPlacerDomino();
	        System.out.println(model.getDominoSelect());
	        model.setRotDominoSelect(coord[4]);
            royaume.addDominoRoyaume(model.getDominoSelect(),coord[0],coord[1],coord[2],coord[3]);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            royaume.showRoyaume();
        }
    }

    private static void test(ModelTest model) throws IOException {
	    Bouton jButton = new Bouton();
	    jButton.setActionCommand(""+ 3 +"/"+ 1);
	    ControlCaseRoyaume controlCaseRoyaume = new ControlCaseRoyaume(model);
	    controlCaseRoyaume.actionPerformed(new ActionEvent(jButton, ActionEvent.ACTION_PERFORMED, jButton.getActionCommand()));
    }

	private static void prodK2Test(ModelTest model) throws UnconnectedException {
	    //Création d'un nouveau paquet et mélange
		Paquet paquet = new Paquet();
		paquet.shuffle();
		for (int a = 0; a < 12; a++) {//décompte du nombre de tour
			System.out.println("tour : " + (a+1));
			TuilesAuCentre tuilesAuCentre = new TuilesAuCentre(paquet,4,true); // instanciation des tuiles au centre
			model.setTuilesCentreAPLacer(tuilesAuCentre); //on récupère dans le modèle l'instance de tuilesAuCentre
			for (int i = 0; i < 4; i++) {
				Bouton jButton = new Bouton();
				Button boutontuileSelect = new Button();

				int posDom = model.whoIsBestDomino(); //choix du meilleur domino pour IA
				boutontuileSelect.setActionCommand(String.valueOf(posDom));// On lui insert le meilleur domino
				model.setDominoSelect(tuilesAuCentre.getDominoTab()[posDom]);
				System.out.println(model.getJoueurActuel().getNom());
				System.out.println(model.getDominoSelect());
				model.setDominoDejaChoisi(posDom,true);
				int[] coord = model.ouPlacerDomino();


				if (coord[0] != -1 ){
					ControlCaseRoyaume controlCaseRoyaume = new ControlCaseRoyaume(model);
					model.setRotDominoSelect(coord[4]);
					model.getJoueurActuel().getRoyaume().showRoyaume();
					jButton.setActionCommand(""+ coord[0] +"/"+ coord[1]);
					controlCaseRoyaume.actionPerformed(new ActionEvent(jButton, ActionEvent.ACTION_PERFORMED, jButton.getActionCommand()));

				}else{
					System.out.println("passe tour");
					model.getJoueurActuel().getRoyaume().showRoyaume();
					model.changementJoueur();
				}
				//model.showOrdreNext();
			}
		}
		//model.getJoueur(0).getRoyaume();
	}
}