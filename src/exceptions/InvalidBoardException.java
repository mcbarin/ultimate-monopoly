package exceptions;

public class InvalidBoardException extends RuntimeException {
	public InvalidBoardException (){
		super();
	}

	public InvalidBoardException  (String message){
		super(message);
	}

}


