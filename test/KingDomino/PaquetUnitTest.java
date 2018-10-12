package KingDomino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaquetUnitTest {

	private Paquet paquet;

	@Before
	public void init() throws Exception {
		paquet = new Paquet();
	}

	@Test
	public void testIsEmpty() throws Exception {
		Assert.assertFalse(paquet.isEmpty());
	}

	@Test
	public void testSize(){
		Assert.assertEquals(52,paquet.size());
	}

	@Test
	public void testTakeFirst(){
		Domino domino = paquet.dominos.get(0);
		Domino domino1 = paquet.dominos.get(1);
		paquet.takeFirst();
		Assert.assertEquals(domino1,paquet.dominos.get(0));
	}

	@Test
	public void testShuffle(){
		paquet.shuffle();
	}



}
