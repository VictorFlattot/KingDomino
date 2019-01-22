package Control;

import Vues.FenetreTest;
import Model.ModelTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlTuileCentre implements ActionListener {
	private ModelTest model;
	private FenetreTest fenetre;
	private int idDom;
	private int posDom;
	private BufferedImage image;

	public ControlTuileCentre(ModelTest model, FenetreTest fenetre) {
		this.model = model;
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		//System.out.println("Joueur actuel : " + model.getJoueurActuel().getNom());
		String actionCommandSplit[] = a.getActionCommand().split("/");
		idDom = Integer.valueOf(actionCommandSplit[0]);
		posDom = Integer.valueOf(actionCommandSplit[1]);

		model.setDominoSelect(model.getTuilesCentreAPLacer().getDominoTab()[posDom]);

		try {
			image = ImageIO.read(new File(fenetre.donneCheminDomino(idDom,0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fenetre.boutonTour.setEnabled(true);
		fenetre.afficheTuileSelect(new ImageIcon(image),idDom);
		fenetre.bloquerToutBoutonCentre(true);
		fenetre.bloquerToutBoutonRoyaume(false,model.getJoueurActuel().getId());
		model.setDominoDejaPlacé(posDom,true);



	}
}
