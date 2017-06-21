package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class UserSync {
	public static List<User> readUsers(String csvFile) {
		try(Scanner scanner = new Scanner(new File(csvFile));){
			while (scanner.hasNext()) {
				List<String> line = CSVUtils.parseLine(scanner.nextLine());
				System.out.println(
						"Cetatean [Prenume: " + line.get(0) + ", Nume: " + line.get(1) + " , Varsta: " + line.get(2)
								+ ", Nationalitate: " + line.get(3) + ", Tara de domiciliu: " + line.get(4) + "]");
			}
			throw new RuntimeException("Not implemented yet!");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
