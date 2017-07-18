package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class ReadingUsers {
	public void read() throws FileNotFoundException {
		System.out.println("Reading users in order from a CSV file");
        Scanner sc = new Scanner(new File("src/test/files/users.csv"));
        sc.useDelimiter(",");
        System.out.println("User list: ");
        while (sc.hasNext()) {
        	System.out.println(sc.next());
        }
        sc.close();
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
		System.out.println("Writing done!");
	}
}

//this app deal only with users
//TODO: add pass and other info..
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        new ReadingUsers().read();
        new WritingUsers().write();
    }
}