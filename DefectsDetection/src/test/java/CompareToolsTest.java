import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompareToolsTest {

	//started testing
	
	GUI gui;
	CompareTools ct1;
	
	@BeforeEach
	void setUp() throws Exception {
		gui=new GUI();
		gui.addTable();
		ct1=new CompareTools(gui);
		ct1.setVisible(false);
	}

	
	
	@Test
	void test() {
		assertThrows(IllegalArgumentException.class, () -> {
	        ct1=new CompareTools(null);
	    });
	}

}
