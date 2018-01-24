package clear;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ClearRecord extends Thread{
	public void run() {
		try {
			File writefile = new File("record.txt");
			writefile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writefile, false));
			out.write("");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
