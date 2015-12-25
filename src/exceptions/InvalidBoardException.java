package exceptions;

public class InvalidBoardException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBoardException (){
		super();
	}

	public InvalidBoardException  (String message){
		super(message);
	}

}


