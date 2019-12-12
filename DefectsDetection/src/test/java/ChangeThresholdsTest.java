

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JTable;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ChangeThresholdsTest {

	private GUI gui;
	private ChangeThresholds c;

JTable table=gui.getCurrentExcelFileData();

	@BeforeEach
	public void getTable() {
		gui=new GUI();
		gui.addTable();
		c=new ChangeThresholds(gui.getFrame(), gui);
		c.setVisible(false);
	}

	@Test
	public void testCheckLM() {
		assertEquals(c.checkLM("", ""),0);
		assertEquals(c.checkLM("2", ""),1);
		assertEquals(c.checkLM("", "3"),1);
		assertEquals(c.checkLM("2", "3"),2);	
	}

	@Test
	public void testCheckFE() {
		assertEquals(c.checkFE("", ""),0);
		assertEquals(c.checkFE("2", ""),1);
		assertEquals(c.checkFE("", "3"),1);
		assertEquals(c.checkFE("2", "3"),2);
	}

	@Test
	public void testCheckValues() {
		assertEquals(c.checkValues("2", "3", "", ""),true);
		assertEquals(c.checkValues("", "", "2", "3"),true);
		assertEquals(c.checkValues("2", "3", "2", "0.4"),true);

		assertEquals(c.checkValues("2", "", "", "3"),false);
	}

	
	@Test
	public void testCompareLM() {
		c.checkValues("9", "15", "", "");

		gui.addTable();
		
		c.compareLM(gui.getCurrentExcelFileData(),9,15);
		c.paintWithErrors(gui.getFrame(), gui);
		
		assertEquals(c.getDCI(),127);
		assertEquals(c.getDII(),2);
		assertEquals(c.getADCI(),278);
		assertEquals(c.getADII(),13);
	}

	@Test
	public void testCompareFE() {
		c.checkValues("", "", "4", "0.4");
		
		gui.addTable();
		
		c.compareFE(gui.getCurrentExcelFileData(),4,0.4);
		c.paintWithErrors(gui.getFrame(), gui);
		
		assertEquals(c.getDCI_FE(),112);
		assertEquals(c.getDII_FE(),19);
		assertEquals(c.getADCI_FE(),287);
		assertEquals(c.getADII_FE(),2);
	}
	
	@Test
	public void test1() {
		GUI gui = new GUI();
		ChangeThresholds c = new ChangeThresholds(gui.getframe(),gui);
		c.setVisible(false);
		c.applyTest(1, 10, 3, 0.33, gui);
		assertTrue(gui.getLOC() == 1 && gui.getCYCLO() == 10 && gui.getATFD() == 3 && gui.getLAA() == 0.33);
		assertTrue(c.checkValues("1","2","3","0.66"));
	}
	
	@Test
	public void test2() {
		GUI gui = new GUI();
		ChangeThresholds c = new ChangeThresholds(gui.getframe(),gui);
		c.setVisible(false);
		c.compareTest(65,10,5,0.33,gui);
		assertTrue(gui.getLOC() == 65 && gui.getCYCLO() == 10 && gui.getATFD() == 5 && gui.getLAA() == 0.33);
		c.compareTest(1, 10, 3, 0.33, gui);
		assertTrue(gui.getLOC() == 1 && gui.getCYCLO() == 10 && gui.getATFD() == 3 && gui.getLAA() == 0.33);
		
	}
}
