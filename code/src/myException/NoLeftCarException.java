package myException;

public class NoLeftCarException extends MyException{
	public NoLeftCarException() {
		super("The car didn't leave or didn't even come in.");
	}
}
