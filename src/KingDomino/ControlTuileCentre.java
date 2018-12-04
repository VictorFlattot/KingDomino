package KingDomino;

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
	private BufferedImage image
			;

	public ControlTuileCentre(ModelTest model, FenetreTest fenetre) {
		this.model = model;
		this.fenetre = fenetre;
		fenetre.setActionListenerTuileCentre(this);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		String actionCommandSplit[] = a.getActionCommand().split("/");
		idDom = Integer.valueOf(actionCommandSplit[0]);
		posDom = Integer.valueOf(actionCommandSplit[1]);
		try {
			image = ImageIO.read(new File(fenetre.donneCheminDomino(idDom,0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fenetre.afficheTuileSelect(new ImageIcon(image),0);
		fenetre.bloquerBoutonCentre(posDom);
		//fenetre.afficheTuileSelect();
	}
}
