package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			br = new BufferedReader(new FileReader("HundredUsers.csv"));
			sb = new StringBuilder();
			String line = br.readLine();
			String fileName = "HundredUsers.csv";
			File file = new File(fileName);
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String text = sb.toString();
			System.out.println(text);
			
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String data = scan.next();
				String[] values = data.split(",");
				for (String word : values) {
					System.out.println(word);
				}
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

	}
}