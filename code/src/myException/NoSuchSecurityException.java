package myException;

public class NoSuchSecurityException extends MyException{
	public NoSuchSecurityException() {
		super("There is no such security!");
	}
}
