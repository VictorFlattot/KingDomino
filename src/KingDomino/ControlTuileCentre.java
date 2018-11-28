package KingDomino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlTuileCentre implements ActionListener {
	private ModelTest model;
	private FenetreTest fenetre;

	public ControlTuileCentre(ModelTest model, FenetreTest fenetre) {
		this.model = model;
		this.fenetre = fenetre;
		fenetre.setActionListenerTuileCentre(this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println(actionEvent.getActionCommand());
	}
}
