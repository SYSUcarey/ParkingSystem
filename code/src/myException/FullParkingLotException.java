package myException;

public class FullParkingLotException extends MyException{
	public FullParkingLotException() {
		super("Sorry!The ParkingLot is full!");
	}
}
