package man;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.text.*;
import parking.*;
import myException.*;


public class Security extends Person implements Logable{
    public Security(String name) {
        super(name);
    }
    @Override
    public boolean Login(String name, String password) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Security.txt")));
        String data;
        while((data = br.readLine())!= null) {
            String[] security = data.split("\\s+");
            if (security[0].equals(name) && security[1].equals(password)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }
    public String getCarID(String pos) {
        int i = Integer.parseInt(pos);
        String ID;
        try {
        ID = ParkingLot.getInstance().getCarID(i);
        }catch (MyException e) {
        	ID = e.getMessage();
        }
        return ID;
    }
    public String getCarPosition(String id) {
    	String pos;
    	try {
    		pos = String.valueOf(ParkingLot.getInstance().getPosition(id));
    	}catch (MyException e) {
    		pos = e.getMessage();
    	}
        return pos;
    }
    public String getInTime(String id) {
    	String inTime;
    	try {
    		int position = ParkingLot.getInstance().getPosition(id);
    		Date time = ParkingLot.getInstance().getCar(position).getInTime();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String str = sdf.format(time);
    		inTime = str;
    	}catch (MyException e) {
    		inTime = e.getMessage();
    	}
        return inTime;
    }
    public String getOutTime(String id) {
    	String outTime;
    	try {
        	Date time = ParkingLot.getInstance().getLeftCar(id).getOutTime();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String str = sdf.format(time);
    		outTime = str;
    	}catch (MyException e) {
    		outTime = e.getMessage();
    	}
        return outTime;
    }
    public String getStartTime() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Security.txt")));
        String data;
        String starttime = "";
        while((data = br.readLine())!= null) {
            String[] security = data.split("\\s+");
            if (security[0].equals(super.getName())) {
                br.close();
                starttime = security[2];
            }
        }
        br.close();
        return starttime;
    }
    public  String getEndTime() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Security.txt")));
        String data;
        String endtime = "";
        while((data = br.readLine())!= null) {
            String[] security = data.split("\\s+");
            if (security[0].equals(super.getName())) {
                br.close();
                endtime = security[3];
            }
        }
        br.close();
        return endtime;
    }
    public String CarIn(String carID, Date inTime, String dutyInName) {
    	String feedback;
    	try {
    		feedback = ParkingLot.getInstance().CarIn(carID, inTime, dutyInName);
    	}catch (MyException e) {
    		feedback = e.getMessage();
    	}
        return feedback;
    }
    public String CarOut(String carID, Date outTime, String dutyOutName){
    	String feedback;
    	try {
    		feedback = ParkingLot.getInstance().CarOut(carID, outTime, dutyOutName);
    	}catch (MyException e) {
    		feedback = e.getMessage();
    	}
        return feedback;
    }
}
