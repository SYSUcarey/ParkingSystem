package man;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import myException.*;
import parking.*;

public class Manager extends Person implements Logable{

    public Manager(String name) {
        super(name);
    }
    @Override
    public boolean Login(String name, String password) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Manager.txt")));
        String data;
        while((data = br.readLine())!= null) {
            String[] manager = data.split("\\s+");
            if (manager[0].equals(name) && manager[1].equals(password))
                return true;
        }
        br.close();
        return false;
    }
    public String[] ListAllSecurity() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Security.txt")));
        String data;
        String[] tresult = new String[1000];
        int count = 0;
        while((data = br.readLine())!= null) {
            tresult[count] = data.substring(0, data.indexOf(' '));
            count++;
        }
        br.close();
        String[] result = new String[count];
        for (int i = 0; i < count; i++)
            result[i] = tresult[i];
        return result;
    }
    public String FindSecurity(String time) throws Exception{
        if (time.length() != 8) return "Not valid time format";
        for (int i = 0; i < time.length(); i++) {
            if (i == 2 || i == 5){
                if(time.charAt(i) != ':') return "Not valid time format";
            }
            else {
                if (time.charAt(i) < '0' || time.charAt(i) > '9')
                    return "Not valid time format";
            }
        }
        String s1 = time.substring(0,2);
        String s2 = time.substring(3,5);
        String s3 = time.substring(6,8);
        if (s1.compareTo("00") < 0 || s1.compareTo("23") > 0 )  return "Not valid time format";
        if (s2.compareTo("00") < 0 || s2.compareTo("59") > 0 )  return "Not valid time format";
        if (s3.compareTo("00") < 0 || s3.compareTo("59") > 0 )return "Not valid time format";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Security.txt")));
        String data;
        String result = "There is no such security!";
        while((data = br.readLine())!= null) {
            String[] security = data.split("\\s+");
            if (time.compareTo(security[2]) >= 0 &&time.compareTo(security[3]) < 0)
                result = security[0];
        }
        br.close();
        return result;
    }

    public String FindSecurityIn(String id) throws Exception {
    	String securityIn;
    	try {
    		int position = ParkingLot.getInstance().getPosition(id);
            securityIn = ParkingLot.getInstance().getCar(position).getDutyIn();
    	}catch (NoSuchCarException e) {
    		securityIn = e.getMessage();
    	}
    	
        return securityIn;
    }

    public String FindSecurityOut(String id) throws Exception{
    	String securityOut;
    	String outTime;
        try {
            securityOut = ParkingLot.getInstance().getLeftCar(id).getDutyOut();
        }catch (MyException e) {
        	securityOut = e.getMessage();
        }
        return securityOut;
    }
    public static void main(String[] args) throws Exception{
        Manager m = new Manager("jmb");
        String[] result = m.ListAllSecurity();
        for (int i = 0; i < result.length; i++)
            System.out.println(result[i]);
    }
}
