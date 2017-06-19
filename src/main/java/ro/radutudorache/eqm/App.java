package ro.radutudorache.eqm;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        String csvFile = "test.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Cetatean [Prenume: " + line[0] + ", Nume: " + line[1] + " , Varsta: " + line[2] + 
                		", Nationalitate: " + line[3] + ", Tara de domiciliu: " + line[4] + "]" );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}