package KingDomino;

public class ControlGroup {
	private ModelTest model;
	private FenetreTest fenetreTest;

	public ControlGroup(ModelTest model) {
		this.model = model;
		this.fenetreTest = new FenetreTest(model);

	}
}
