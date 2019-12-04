import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author carolina.martins
 *
 */
class ExcelReaderTest {

	private static ExcelReader reader;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		reader = new ExcelReader();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}	

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ExcelReader#readFile(java.lang.String)}.
	 */
	@Test
	final void testReadFile() {
		assertNotNull(reader.readFile("C:/Users/carolina.martins/Desktop/ISCTE/ES/Long-Method.xlsx"));
		assertThrows(NullPointerException.class, () -> {
			reader.readFile("C:/Users/carolina.martins/Desktop/ISCTE/Long-Method.xlsx");
		});
		XSSFWorkbook wbk = null;
		Exception exception = assertThrows(IOException.class, () -> {
			reader.setXSSFWorkbook(wbk);
		});
		assertEquals(exception.getMessage(), "");
	}
	
	@Test
	final void testSetXSSFWorkbook() {
		
	}
}