package monga;
import static org.junit.Assert.*;

import org.junit.*;

public class GridTest {

	Grid testGrid = new Grid();
	@Before
	public void gridSetUp() throws Exception
	{
		System.out.println("test begins");
	}
	
	@After
	public void gridTearDown () throws Exception
	{
		System.out.println("Test is successfully done!");
	}

	@Test
	public void testInitializeMonga1()
	{
		/* If the array value == 1
		 * Monga would be placed on the position.
		 */		
		testGrid.initializeMonga(5, 5);	
		assertEquals(testGrid.displayCells[5][5], 1);
		testGrid.initializeMonga(1, 1);
		assertTrue(testGrid.displayCells[1][1] == 1);
	}
	@Test
	public void testMoveMongaLeft()
	{
		testGrid.initializeMonga(5,5);
		testGrid.moveMongaLeft(testGrid.displayCells);
		assertEquals(testGrid.displayCells[4][5], 1);
		assertFalse(testGrid.displayCells[4][5] == 0);
	}
	@Test
	public void testMoveMongaRight()
	{
		testGrid.initializeMonga(1,1);
		testGrid.moveMongaRight(testGrid.displayCells);
		assertEquals(testGrid.displayCells[2][1], 1);
		assertTrue(testGrid.displayCells[2][1] == 1);
	}
	@Test
	public void testMongaUp()
	{
		testGrid.initializeMonga(4,5);
		testGrid.moveMongaUp(testGrid.displayCells);
		assertEquals(testGrid.displayCells[4][4], 1);
		assertTrue(testGrid.displayCells[4][4] != 0);
	}
	@Test
	public void testMongaDown()
	{
		testGrid.initializeMonga(4,5);
		testGrid.moveMongaDown(testGrid.displayCells);
		assertEquals(testGrid.displayCells[4][6], 1);
		assertFalse(testGrid.displayCells[4][6] != 1);
	}
}
