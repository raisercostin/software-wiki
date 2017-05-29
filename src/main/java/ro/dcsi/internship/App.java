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
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

	}
}