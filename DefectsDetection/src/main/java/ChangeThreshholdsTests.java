import static org.junit.Assert.*;

import org.junit.Test;

public class ChangeThreshholdsTests {
	
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
