
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//all tested

class ComparisonErrorTest { 

	ComparisonError ce1;
	ComparisonError ce2;
	ComparisonError ce3;
	ComparisonError ce4;


	LinkedList<Integer> errors1=new LinkedList<Integer>();
	
	@BeforeEach
	void setUp() throws Exception {
		for(int i=0;i!=40;i++) {
			errors1.add(i);
		}
		
		ce1=new ComparisonError("DCI",20,errors1);
		ce2=new ComparisonError("DII",15,errors1);
		ce3=new ComparisonError("ADCI",35,errors1);
		ce4=new ComparisonError("ADII",55,errors1);

	}
	
	@Test
	void test() {
		assertEquals(20,ce1.getNumberErrors());
		assertEquals("DCI",ce1.getErrorType());
		assertEquals(errors1,ce1.getMethodIDErrors());
		assertThrows(IllegalArgumentException.class, () -> {
	        ce1=new ComparisonError("DII",10,null);
	    });
		
		assertEquals(15,ce2.getNumberErrors());
		assertEquals("DII",ce2.getErrorType());
		assertEquals(errors1,ce2.getMethodIDErrors());
		assertThrows(IllegalArgumentException.class, () -> {
	        ce2=new ComparisonError("DII",10,null);
	    });
		
		assertEquals(35,ce3.getNumberErrors());
		assertEquals("ADCI",ce3.getErrorType());
		assertEquals(errors1,ce3.getMethodIDErrors());
		assertThrows(IllegalArgumentException.class, () -> {
	        ce3=new ComparisonError("DII",10,null);
	    });
		
		assertEquals(55,ce4.getNumberErrors());
		assertEquals("ADII",ce4.getErrorType());
		assertEquals(errors1,ce4.getMethodIDErrors());
		assertThrows(IllegalArgumentException.class, () -> {
	        ce4=new ComparisonError("DII",10,null);
	    });
		
		
		assertThrows(IllegalArgumentException.class, () -> {
	        ce4=new ComparisonError("DII",-5,errors1);
	    });
		
		assertThrows(IllegalArgumentException.class, () -> {
	        ce4=new ComparisonError("",10,errors1);
	    });
	}

}
