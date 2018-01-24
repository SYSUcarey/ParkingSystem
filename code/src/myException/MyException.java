package myException;

public class MyException extends Exception{
	public MyException() {
		super("An exception caused.");
	}
	public MyException(String message) {
		super(message);
	}
}
