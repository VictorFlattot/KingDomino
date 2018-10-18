package KingDomino;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Partie {
	public static Random random = new Random();
	List<String> joueurs;
	Map<Integer, String> idJoueurs;
	Map<Integer,Royaume> royaumeJoueur;
	Paquet pioche;
	TuilesAuCentre tuilesAuCentre;
	String[] ordreJoueurTourActuel;
	String[] ordreJoueurToursuivant;

	int joueurActuel;

	Domino dominoChoisiParLeJoueurActuel;
	BufferedReader consoleIn; // to read from stdin

	public Partie() {
		initInstance();
		initNomJoueur();
		initIdJoueurEtRoyaume();
		pioche.shuffle();
		ordreDépartJoueur();
		//nouveauTour();



	}

	private void ordreDépartJoueur() {
		ordreJoueurTourActuel = new String[4];
		int r=random.nextInt(4)+1;

		for (int i = 1; i < 4; i++) {
			ordreJoueurTourActuel[i] = idJoueurs.get((r+i)%4);
			System.out.println();
		}

	}

	private void initInstance() {
		joueurs = new ArrayList<>();
		idJoueurs = new HashMap<>();
		royaumeJoueur = new HashMap<>();
		pioche = new Paquet();
	}

	private void initNomJoueur() {
		for (int i = 1; i < 5; i++) {
			addJoueur("J"+i);
		}
	}

	/*private void nouveauTour() {
		tuilesAuCentre = new TuilesAuCentre(pioche);
		for (int i = 0; i < 4; i++) {
			try {
			String line = consoleIn.readLine();
		}
		catch(IOException e) {
			System.out.println(e);
		}

			DominoChoisiParLeJoueurActuel = line;
			tuilesAuCentre.choisirDomino(line);
		}

	}*/

	public void addJoueur(String nom){
		joueurs.add(nom);
	}

	public void initIdJoueurEtRoyaume(){
		for (int i = 1; i <= joueurs.size(); i++) {
			idJoueurs.put(i,joueurs.get(i-1));
			royaumeJoueur.put(i,new Royaume());
		}
	}


}
