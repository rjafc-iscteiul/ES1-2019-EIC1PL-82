import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GUITest {

	//Dont do more tests here
	
	GUI gui;
	
	@BeforeEach
	void setUp() throws Exception {
		gui=new GUI();
		gui.addTable();
		gui.compareWithDefault();
		gui.assignThreshholds(10, 10, 10, 0.5);
	}

	@Test
	void test() {
		assertNotNull(gui);
		
		assertEquals(10,gui.getLOC());
		assertEquals(10,gui.getCYCLO());
		assertEquals(10,gui.getATFD());
		assertEquals(5,(int)(gui.getLAA()*10));
		assertEquals(80,gui.getDefaultLOC());
		assertEquals(10,gui.getDefaultCYCLO());
		assertEquals(4,gui.getDefaultATFD());
		assertEquals(42,(int)(gui.getDefaultLAA()*100));

		gui.makeValuesDefault();
		assertEquals(80,gui.getLOC());
		assertEquals(10,gui.getCYCLO());
		assertEquals(4,gui.getATFD());
		assertEquals(42,(int)(gui.getLAA()*100));
		
	}

}
