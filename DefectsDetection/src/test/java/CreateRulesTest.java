import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.swing.JFrame;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Tests all done here

class CreateRulesTest {

	GUI gui;
	JFrame frame;
	CreateRules cr1;
	
	@BeforeEach
	void setUp() throws Exception {
		gui=new GUI();
		gui.addTable();
		frame=gui.getFrame();
		cr1=new CreateRules(frame,gui);
		cr1.setVisible(false);
	}


	@Test
	void test() {
		assertEquals("11.0>70",cr1.replaceValues("LOC>70", gui.getCurrentExcelFileData(),3));
		assertEquals("2.0>15",cr1.replaceValues("CYCLO>15", gui.getCurrentExcelFileData(),3));
		assertEquals("2.0>4",cr1.replaceValues("ATFD>4", gui.getCurrentExcelFileData(),3));
		assertEquals("0.0>0.5",cr1.replaceValues("LAA>0.5", gui.getCurrentExcelFileData(),3));
		
		cr1.paintingErrors("LOC>60", "");
		 
		cr1.paintingErrors("", "ATFD>2");
		cr1.paintingErrors("LOC>60", "ATFD>2");
		
		cr1.paintingErrors("LOC>60&&LAA>1", "ATFD>2&&CYCLO<3");
		cr1.paintingErrors("LOC>60 || ATFD<6", "ATFD>2 && LAA>3");
		cr1.paintingErrors("LOC>60&&LAA<3", "ATFD>2 || CYCLO<3");
		cr1.paintingErrors("LOC>60&&CYCLO>10", "ATFD>2&&LAA>10");

		assertThrows(IllegalArgumentException.class, () -> {
	        cr1.paintingErrors("", "");
	    });
		
	}

}
