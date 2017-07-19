package ro.dcsi.internship;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.util.List;
import java.io.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<User> beans = new CsvToBeanBuilder(new FileReader("src/test/resources/users.csv"))
                .withType(User.class).build().parse();

        for (User user : beans) {
            System.out.println("Username: " + user.getUsername());
        }

        Writer writer = new FileWriter("src/test/export/userExport.csv");
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(beans);
        writer.close();

    }

}
