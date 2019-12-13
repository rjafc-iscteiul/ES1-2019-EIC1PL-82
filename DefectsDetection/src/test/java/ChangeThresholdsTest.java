import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ChangeThresholdsTest {

	GUI gui;
	ChangeThresholds c;
	JTable table;
	
	@BeforeEach
	public void setUp() {
		gui=new GUI();
		gui.addTable();
		table=gui.getCurrentExcelFileData();
		c=new ChangeThresholds(gui.getFrame(), gui);
		c.setVisible(false);
	}

	@Test
	public void testCheckLM() {
		
		assertEquals(0,c.checkLM("",""));
		assertEquals(1,c.checkLM("2",""));
		assertEquals(1,c.checkLM("","3"));
		assertEquals(2,c.checkLM("2","3"));	
	}

	@Test
	public void testCheckFE() {

		assertEquals(0,c.checkFE("", ""));
		assertEquals(1,c.checkFE("2", ""));
		assertEquals(1,c.checkFE("", "3"));
		assertEquals(2,c.checkFE("2", "3"));
	}

	@Test
	public void testCheckValues() {

		assertTrue(c.checkValues("2", "3", "", ""));
		assertTrue(c.checkValues("", "", "2", "3"));
		assertTrue(c.checkValues("2", "3", "2", "0.4"));
		assertFalse(c.checkValues("2", "", "", "3"));
	}
	
	@Test
	public void testCompareLM() {
		
		c.checkValues("9", "15", "", "");

		gui.addTable();
		
		c.compareLM(gui.getCurrentExcelFileData(),9,15);
		c.paintWithErrors(gui.getFrame(), gui);
		
		assertEquals(127,c.getDCI());
		assertEquals(2,c.getDII());
		assertEquals(278,c.getADCI());
		assertEquals(13,c.getADII());
	}

	@Test
	public void testCompareFE() {

		c.checkValues("", "", "4", "0.4");
		
		gui.addTable();
		
		c.compareFE(gui.getCurrentExcelFileData(),4,0.4);
		c.paintWithErrors(gui.getFrame(), gui);
		
		assertEquals(112,c.getDCI_FE());
		assertEquals(19,c.getDII_FE());
		assertEquals(287,c.getADCI_FE());
		assertEquals(2,c.getADII_FE());
	}
	
	@Test
	public void test1() {
		gui = new GUI();
		c = new ChangeThresholds(gui.getFrame(),gui);
		c.setVisible(false);
		c.applyTest(1, 10, 3, 0.33, gui);
		assertTrue(gui.getLOC() == 1 && gui.getCYCLO() == 10 && gui.getATFD() == 3 && gui.getLAA() == 0.33);
		assertTrue(c.checkValues("1","2","3","0.66"));
	}
	
	@Test
	public void test2() {
		gui = new GUI();
		c = new ChangeThresholds(gui.getFrame(),gui);
		c.setVisible(false);
		c.compareTest(65,10,5,0.33,gui);
		assertTrue(gui.getLOC() == 65 && gui.getCYCLO() == 10 && gui.getATFD() == 5 && gui.getLAA() == 0.33);
		c.compareTest(1, 10, 3, 0.33, gui);
		assertTrue(gui.getLOC() == 1 && gui.getCYCLO() == 10 && gui.getATFD() == 3 && gui.getLAA() == 0.33);
		
	}
}