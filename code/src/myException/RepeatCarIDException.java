package myException;

public class RepeatCarIDException extends MyException{
	public RepeatCarIDException() {
		super("Please check your carID input!");
	}
}
