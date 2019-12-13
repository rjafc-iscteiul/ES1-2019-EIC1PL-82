import java.util.LinkedList;

/**
 * @author rjafc-iscteiul
 * @version 1.0
 * Date: 13 december 2019
 * Creates an object containing information about a specific error such as number of types the error occurred and the methodID's that have the error
 */
public class ComparisonError {

	/**
	 * Represents the error.
	 */
	private String errorType;
	
	
	/**
	 * Number of times the previous error occurred. 
	 */
	private int numberErrors;
	
	
	/**
	 * List containing the methodID from the methods that had the previous error.
	 */
	private LinkedList<Integer> methodIDErrors=new LinkedList<Integer>();
	
	
	
	/**
	 * Constructs a ComparisonError object.
	 * @param errorType - Name of the error occurred.
	 * @param numberErrors - Number of times the error represented in errorType occurred.
	 * @param methodIDErrors - Represents the methodID's from the methods that had the error given in errorType.
	 */
	public ComparisonError(String errorType, int numberErrors, LinkedList<Integer> methodIDErrors) {
		if(errorType.length()==0 || numberErrors<0 || methodIDErrors==null) {
			throw new IllegalArgumentException("Error type not defined or no list given.");
		}else {
			this.errorType=errorType;
			this.numberErrors=numberErrors;
			this.methodIDErrors=methodIDErrors;
		}
	}

	
	/**
	 * @return the error considered
	 */
	public String getErrorType() {
		return errorType;
	}

	
	/**
	 * @return the number of times the current error occurred
	 */
	public int getNumberErrors() {
		return numberErrors;
	}

	
	/**
	 * @return the list containing the methodID's of the methods that have the considered error
	 */
	public LinkedList<Integer> getMethodIDErrors() {
		return methodIDErrors;
	}
	
}
