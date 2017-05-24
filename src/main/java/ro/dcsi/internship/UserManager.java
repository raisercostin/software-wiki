package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserManager {
	public static void main(String[] args) throws IOException {
		String readCsv = "c:\\Users\\Andrei\\maven workspace\\ship1\\src\\main\\resources\\HundredUsers.csv";
		String writeCsv = "c:\\Users\\Andrei\\maven workspace\\ship1\\src\\test\\resources\\WriteHundredUsers.csv";

		BufferedReader br = null;
		FileWriter fr = null;
		String line = "";
		String wordSeparator = ",";
		try {
			br = new BufferedReader(new FileReader(readCsv));
			fr = new FileWriter(writeCsv);
			while ((line = br.readLine()) != null) {
				String[] usersFromCsv = line.split(wordSeparator);
				for (String user : usersFromCsv) {
						System.out.println(user);
						fr.append(user + ",").append("\n");
					}

					
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				fr.flush();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
}