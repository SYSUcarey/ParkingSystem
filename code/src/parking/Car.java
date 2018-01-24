package parking;
import java.util.*;
import man.Security;

public class Car {
    private String id;
    private Date inTime;
    private Date outTime;
    private Security dutyIn;
    private Security dutyOut;
    
    public Car(String id) {
    	this.id = id;
    	inTime = null;
    	outTime = null;
    }
    public String getCarID() {
    	return id;
    }
    public void setInTime(Date time) {
    	inTime = time;
    }
    public Date getInTime() {
    	return inTime;
    }
    public void setOutTime(Date time) {
    	outTime = time;
    }
    public Date getOutTime() {
    	return outTime;
    }
    public void setDutyIn(String in) {
    	dutyIn = new Security(in);
    }
    public String getDutyIn() {
    	return dutyIn.getName();
    }
    public void setDutyOut(String out) {
    	dutyOut = new Security(out);
    }
    public String getDutyOut() {
    	return dutyOut.getName();
    }
    public double getParkingFee() {
    	return 0.0;
    }
}
