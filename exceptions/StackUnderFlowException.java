package exceptions;

public class StackUnderFlowException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3943723455901458391L;

	public StackUnderFlowException() {
		super();
	}

	public StackUnderFlowException(String msg) {
		super(msg);
	}
}
