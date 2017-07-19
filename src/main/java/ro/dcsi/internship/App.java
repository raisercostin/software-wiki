package ro.dcsi.internship;

import java.io.*;
import java.util.Scanner;

public class App {
    private static final String FILENAME = System.getProperty("user.home") + "/data.csv";
    private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) {
        createFile();

        System.out.println("Insert data:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!(input.equals("DONE"))) {
            writeDataInFile(input);
            input = scanner.nextLine();
        }
        scanner.close();
        readDataFromFile();
    }

    private static void createFile() {
        try {

            File file = new File(FILENAME);

            if (file.createNewFile() || file.exists()) {
                System.out.println("File created/exists!");
            } else {
                System.out.println("Cannot create file!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataInFile(String input) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILENAME, true);
            fileWriter.append(input);
            fileWriter.append(COMMA_DELIMITER);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void readDataFromFile() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(FILENAME);
            bufferedReader = new BufferedReader(fileReader);

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

