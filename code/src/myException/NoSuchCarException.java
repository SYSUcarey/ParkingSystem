package myException;

public class NoSuchCarException extends MyException{
	public NoSuchCarException() {
		super("There is no such Car!");
	}
	
}
