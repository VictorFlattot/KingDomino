package KingDomino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DominoUnitTest {

	private Domino d;
	private Tuile tuile1;
	private Tuile tuile2;

	@Before
	public void init() throws Exception {
		tuile1 = new Tuile(Terrain.DEPART,0);
		tuile2 = new Tuile(Terrain.DEPART,0);
		d = new Domino(49, tuile1,tuile2);
	}

	@Test
	public void testIdentifiant(){
		Assert.assertEquals(d.getId(),49);
	}

	@Test
	public void testCase1(){
		Assert.assertEquals(d.getTuile1(), tuile1);
	}

	@Test
	public void testCase2(){
		Assert.assertEquals(d.getTuile2(), tuile2);
	}


}
