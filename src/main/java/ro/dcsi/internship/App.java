package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			br = new BufferedReader(new FileReader("src/main/resources/HundredUsers.csv"));
			sb = new StringBuilder();
			String line = br.readLine();
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