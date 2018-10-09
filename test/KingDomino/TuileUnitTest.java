package KingDomino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TuileUnitTest {

	Tuile c;

	@Before
	public void init(){
		c = new Tuile(Terrain.LAC,1);
	}

	@Test
	public void testTerrain(){
		Assert.assertEquals(c.getTerrain() , Terrain.LAC);
	}
	@Test
	public void testNbCouronne(){
		Assert.assertEquals(c.getCouronne() , 1);
	}
}
