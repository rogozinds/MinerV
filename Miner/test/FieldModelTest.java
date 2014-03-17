import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.miner.models.FieldModel;


public class FieldModelTest {
	FieldModel f1;
	FieldModel randomField;
	@Before
	public void setUp() throws Exception {
		//	X . . . .
		//	. . . . X
		//	. . . X X
		//	. X . X .
		//  X X X . .
		//
		int[][] cells={{-100,0,0,0,0},
					   {0,0,0,0,-100},
						{0,0,0,-100,-100},
						{0,-100,0,-100,0},
						{-100,-100,-100,0,0}};
		f1=new FieldModel(cells, 9);
		f1.createField();
	}

	@Test
	public void testHasBomb() {
		assertTrue(f1.hasBomb(0, 0));
	}
	@Test
	public void testHasNotBomb() {
		assertFalse(f1.hasBomb(0, 1));
	}
	//Test count
	@Test
	public void testBombCount1() {
		assertEquals(1,f1.nNeighborBombs(0, 1));

	}
	@Test
	public void testBombCount2() {
		assertEquals(1,f1.nNeighborBombs(4, 4));

	}
	@Test
	public void testBombCount3() {
		assertEquals(1,f1.nNeighborBombs(0, 3));

	}
	@Test
	public void testBombCount4() {
		assertEquals(5,f1.nNeighborBombs(3, 2));

	}	
	@Test
	public void testOpenCell1() {
		f1.openCell(4, 4);
		assertTrue(f1.isOpen(4, 4));
	}
	@Test
	public void testOpenCell2() {
		f1.openCell(4, 4);
		assertFalse(f1.isOpen(4, 3));
	}
	@Test
	public void testOpenCell3() {
		f1.openCell(0, 2);
		assertTrue(f1.isOpen(0, 1));
	}
	@Test
	public void testOpenCell4() {
		f1.openCell(0, 2);
		assertTrue(f1.isOpen(1, 2));
	}
	
}
