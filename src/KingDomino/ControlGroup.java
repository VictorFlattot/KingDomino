package KingDomino;

import java.io.IOException;

public class ControlGroup {
	private ModelTest model;
	private FenetreTest fenetreTest;
	private ControlTuileCentre controlTuileCentre;

	public ControlGroup(ModelTest model) throws IOException {
		this.model = model;
		this.fenetreTest = new FenetreTest(model);
		this.controlTuileCentre = new ControlTuileCentre(model,fenetreTest);

	}
}
