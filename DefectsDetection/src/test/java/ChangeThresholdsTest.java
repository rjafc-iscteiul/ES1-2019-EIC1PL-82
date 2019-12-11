

import static org.junit.Assert.*;

import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class ChangeThresholdsTest {
private GUI gui=new GUI();
private ChangeThresholds c=new ChangeThresholds(gui.getFrame(), gui);

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
//		assertEquals(c.checkValues("2", "", "", "3"),false);
	}

//	@Test
//	public void testCheckTextBoxes() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testCompareLM() {
		c.setLOC(9);
		c.setCYCLO(15);
		c.checkValues("9", "15", "", "");

		gui.addTable();
		
		c.compareLM(gui.getCurrentExcelFileData());
		c.paintWithErrors(gui.getFrame(), gui);
		
		assertEquals(c.getDCI(),127);
		assertEquals(c.getDII(),2);
		assertEquals(c.getADCI(),278);
		assertEquals(c.getADII(),13);
	}

	@Test
	public void testCompareFE() {
		c.setATFD(4);
		c.setLAA(0.4);
		c.checkValues("", "", "4", "0.4");
		
		gui.addTable();
		
		c.compareFE(gui.getCurrentExcelFileData());
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
		c.applyTest(1, 10, 3, 0.33, gui);
		assertTrue(gui.getLOC() == 1 && gui.getCYCLO() == 10 && gui.getATFD() == 3 && gui.getLAA() == 0.33);
		assertTrue(c.checkValues("1","2","3","0.66"));
	}
	
	@Test
	public void test2() {
		GUI gui = new GUI();
		ChangeThresholds c = new ChangeThresholds(gui.getframe(),gui);
		c.compareTest(65,10,5,0.33,gui);
		assertTrue(gui.getLOC() == 65 && gui.getCYCLO() == 10 && gui.getATFD() == 5 && gui.getLAA() == 0.33);
		c.compareTest(1, 10, 3, 0.33, gui);
		assertTrue(gui.getLOC() == 1 && gui.getCYCLO() == 10 && gui.getATFD() == 3 && gui.getLAA() == 0.33);
		
	}
}
