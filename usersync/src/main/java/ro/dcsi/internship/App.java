package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class ReadingUsers {
	public String read() throws FileNotFoundException {
		System.out.println("Reading users in order from a CSV file");
        String last = "";
		Scanner sc = new Scanner(new File("src/test/files/users.csv"));
        sc.useDelimiter(",");
        System.out.println("User list: ");
        while (sc.hasNext()) {
        	last = sc.next();
        	System.out.println(last);
        }
        sc.close();
        return last;
	}
}

class WritingUsers {
	public void write() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("src/test/files/users2.csv"));
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		while (true) {
			String x = sc.next();
			if(x.equals("end")) {
				break;
			} else {
				sb.append(x);
				sb.append(',');
			}
		}
		sc.close();
		pw.write(sb.toString());
		pw.close();
	}
	
	@SuppressWarnings("unused")
	public String verification() {
		if (new File("src/test/files/users2.csv") != null) {
			return "Writing done!";
		}
		return "no";
	}
}

//this app deals only with users
//TODO: add pass and other info besides users
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        new ReadingUsers().read();
        new WritingUsers().write();
    }
}