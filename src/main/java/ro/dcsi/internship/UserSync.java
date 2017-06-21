package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSync {

	public static List<User> readUsers(String string) {

        String csvFile = "SampleCSVFile.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<User> users= new ArrayList<User>();
        User temp=new User();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                
                String[] info = line.split(cvsSplitBy);
                temp=new User(info[1]);
                users.add(temp);

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
        }
        return users;
    }

}


