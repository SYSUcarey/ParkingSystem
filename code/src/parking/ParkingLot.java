package parking;
import java.util.*;

import clear.ClearRecord;
import java.io.*;
import java.text.SimpleDateFormat;
import myException.*;

public class ParkingLot {
	private final int maxSize = 200;
	private Map<Integer, Car> myParkingLot = new HashMap<Integer, Car>();
	private LinkedList<Integer> positionLeft = new LinkedList<Integer>();
	private LinkedList<Car> leftCar = new LinkedList<Car>();
	
	private ParkingLot() {
		for (int i = 0; i < maxSize; ++i)
			positionLeft.add(i + 1);
	}
	private static final ParkingLot parkingLot = new ParkingLot();
	public static ParkingLot getInstance() {
		return parkingLot;
	}
	
	public int numOfCar() {
		return myParkingLot.size();
	}
	
	public String CarIn(String carID, Date inTime, String dutyIn) throws MyException{
		if (numOfCar() == maxSize) throw new FullParkingLotException();
		for (Map.Entry<Integer, Car> temp : myParkingLot.entrySet()) {
			if (temp.getValue().getCarID().equals(carID)) throw new RepeatCarIDException();
		}
		Car car = new Car(carID);
		car.setInTime(inTime);
		car.setDutyIn(dutyIn);
		
		int pos = positionLeft.poll();
		myParkingLot.put(pos, car);
		for (Car left_car : leftCar) {
			if (left_car.getCarID().equals(carID)) leftCar.remove(left_car);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String in_time = sdf.format(inTime);
		
		String record = "CarIn  ID: " + carID + "; InTime: " + in_time + "; DutyInSecurity: " + dutyIn + 
				"; Position: " + String.valueOf(pos) + "\r\n";
		try {
			File writefile = new File("record.txt");
			writefile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writefile, true));
			out.write(record);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String feedback = "Now you can get in.Your Position is " + String.valueOf(pos);
		return feedback;
	}
	public String CarOut(String carID, Date outTime, String dutyOut) throws MyException{
		int pos = getPosition(carID);
		Car car = myParkingLot.get(pos);
		car.setOutTime(outTime);
		car.setDutyOut(dutyOut);
		leftCar.add(car);
		myParkingLot.remove(pos);
		positionLeft.addFirst(pos);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String out_time = sdf.format(outTime);
		
		String record = "CarOut ID: " + carID + "; OutTime: " + out_time + "; DutyOutSecurity: " + dutyOut + "\r\n";
		try {
			File writefile = new File("record.txt");
			writefile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writefile, true));
			out.write(record);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String feedback = "Now you can leave.Thank you!";
		return feedback;
	}
	
	public int getPosition(String id) throws MyException{
		for (Map.Entry<Integer, Car> temp : myParkingLot.entrySet()) {
			if (temp.getValue().getCarID().equals(id)) return temp.getKey();
		}
		throw new NoSuchCarException();
	}
	
	public String getCarID(int pos) throws MyException{
		return getCar(pos).getCarID();
	}
	
	public Car getCar(int pos) throws MyException{
		for (int key:myParkingLot.keySet()) {
			if (key == pos) return myParkingLot.get(key);
		}
		throw new NoSuchCarException();
	}
	
	public Car getLeftCar(String carID) throws MyException{
		for (Car car : leftCar) {
			if (car.getCarID().equals(carID)) return car;
		}
		throw new NoLeftCarException();
	}
	public String listAllCars() {
		String feedback = "ListAllCars: \n";
		if (isEmpty()) feedback = "The parkingLot is empty now.";
		for (Map.Entry<Integer, Car> temp : myParkingLot.entrySet()) {
			feedback += "CarID: " + temp.getValue().getCarID() + "    Position: " + String.valueOf(temp.getKey()) + "\n";
		}
		return feedback;
	}
	public boolean isFull() {
		return myParkingLot.size() == maxSize;
	}
	public boolean isEmpty() {
		return myParkingLot.size() == 0;
	}
	public void clear() {
		ClearRecord instance = new ClearRecord();
		instance.run();
	}
}
