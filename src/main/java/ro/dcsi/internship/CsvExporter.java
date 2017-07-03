package ro.dcsi.internship;

import de.siegmar.fastcsv.writer.CsvAppender;
import de.siegmar.fastcsv.writer.CsvWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Catalin on 7/3/2017.
 */
public class CsvExporter implements Exporter {
    String filename;

    public CsvExporter(String filename) {
        this.filename = filename;
    }

    @Override
    public void export(Iterator<User> iterator) {
        File file = new File(filename);
        CsvWriter csv = new CsvWriter();

        try (CsvAppender appender = csv.append(new FileWriter(file))){
            if(!iterator.hasNext())
                return ;
            User u=iterator.next();

            //Append headers
            Set<String> headers = u.getAttributeSet();
            for(String s:headers)
                appender.appendField(s);
            appender.endLine();

            //Write users
            do{
                for(String s:headers)
                    appender.appendLine(u.getAttributeValue(s));
                appender.endLine();

                //Iterate
                if(iterator.hasNext())
                    u=iterator.next();
                else
                    u = new User("-10",new HashMap<>());

            }while(!u.getId().equals("-10"));

        }
        catch (IOException e){
            System.err.print("Write CSV ERROR!");
            throw new RuntimeException("Error writting to file",e);
        }

    }
}
