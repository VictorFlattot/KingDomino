package KingDomino;

import java.util.*;

public class Partie {
	public static Random random = new Random();
	List<String> joueurs;
	Map<Integer, String> idJoueurs;
	Map<Integer,Royaume> royaumeJoueur;
	Paquet pioche;
	TuilesAuCentre tuilesAuCentre;
	int[] ordreJoueur;

	int JoueurActuel;

	Domino dominoChoisiParLeJoueurActuel;

	public Partie() {
		initInstance();
		initNomJoueur();
		initIdJoueurEtRoyaume();
		pioche.shuffle();
		nouveauTour();

		//ordreDépartJoueur();

	}

	private void ordreDépartJoueur() {
		ordreJoueur = new int[4];
		int r;
		for (int i = 4; i > 0; i--) {
			r=random.nextInt(i);
			ordreJoueur[i]= Integer.parseInt(idJoueurs.get(joueurs.get(r)));

		}
		System.out.println(ordreJoueur);
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

	private void nouveauTour() {
		tuilesAuCentre = new TuilesAuCentre(pioche);
	}

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
