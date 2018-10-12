package KingDomino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Royaume {
	List<Tuile> cases;
	List connections;

	public Royaume() {
		cases = new ArrayList();
		connections = new ArrayList<Tuile[]>();
		cases.add(Paquet.tuileList.get(16));
		//showTuiles();
	}

	private void addDomino(Domino domino) {
		cases.add(domino.getTuile1());
		cases.add(domino.getTuile2());
	}

	private void addConnection(Tuile t1, Tuile t2) {
		Tuile[] t =  {t1, t2};
		connections.add(t);
	}

	private void showTuiles(){
		for (Tuile tuile :
				cases) {
			System.out.println(tuile);
		}
		System.out.println("Connection :");
		for (int i = 0; i < connections.size(); i++) {
			System.out.println(Arrays.toString((Object[]) connections.get(i)));
		}

	}

	@Override
	public String toString() {
		return "Royaume{" +
				"cases=" + cases +
				", connections=" + connections +
				'}';
	}
}
