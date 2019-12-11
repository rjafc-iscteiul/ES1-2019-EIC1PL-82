import java.util.LinkedList;

public class ComparisonError {

	private String errorType;
	private int numberErrors;
	private LinkedList<Integer> methodIDErrors=new LinkedList<Integer>();
	
	public ComparisonError(String errorType, int numberErrors, LinkedList<Integer> methodIDErrors) {
		if(errorType.length()==0 || numberErrors<0 || methodIDErrors==null) {
			throw new IllegalArgumentException("Error type not defined or no list given.");
		}else {
			this.errorType=errorType;
			this.numberErrors=numberErrors;
			this.methodIDErrors=methodIDErrors;
		}
	}

	public String getErrorType() {
		return errorType;
	}

	public int getNumberErrors() {
		return numberErrors;
	}

	public LinkedList<Integer> getMethodIDErrors() {
		return methodIDErrors;
	}
	
}
