package exceptions;

public class StackOverFlowException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6502978732622858421L;

	public StackOverFlowException() {
		super();
	}

	public StackOverFlowException(String msg) {
		super(msg);
	}
}
