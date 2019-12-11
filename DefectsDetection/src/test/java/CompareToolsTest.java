import static org.junit.jupiter.api.Assertions.assertThrows;

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
	}

	@Test
	void test() {
		assertThrows(IllegalArgumentException.class, () -> {
	        ct1=new CompareTools(null);
	    });
	}

}
