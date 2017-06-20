package ro.dcsi.internship;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import de.siegmar.fastcsv.reader.*;


/**
 * Created by Catalin on 6/19/2017.
 */
public class TranslatorCSV extends Translator {
    private CsvParser parser;
    private CsvRow lastRow;
    private boolean hasNext;

    public TranslatorCSV() {
        this.parser = null;
        this.lastRow= null;
        this.hasNext = false;
    }

    @Override
    public List<List<String>> readBulk(int nMax) {
        Reader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(inputFile));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

        CsvReader reader = new CsvReader();

        List<List<String>> output = new ArrayList<List<String>>();
        List<String> buffer;
        CsvRow row;
        CsvParser csv;
        initDefaultHeaders();
        int numberOfHeaders=0,count=0;

        try {
            csv = reader.parse(fileReader);

            while((row = csv.nextRow()) != null && count <nMax){
                buffer= new ArrayList<String>();
                for(int i=0;i<row.getFieldCount();i++)
                    buffer.add(row.getField(i));
                output.add(buffer);
                count++;

                if(numberOfHeaders==0)
                    numberOfHeaders=row.getFieldCount();
            }

        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }

        resizeHeaders(numberOfHeaders);
        return output;
    }

    public boolean hasNext() {
        return false;
    }

    private void initDefaultHeaders(){
        headers = new ArrayList<String>();
        headers.add("Name");
        headers.add("EMail");
    }

    private void resizeHeaders(int n){
        if(headers.size() > n)
            headers=headers.subList(0,n);

        while(headers.size() < n)
            headers.add("Unknown");
    }

    private void InitHeader(){

    }

    @Override
    public void setInputFile(String inputFile) {
        super.inputFile = inputFile;
        try{
            Reader input = new BufferedReader(new FileReader(inputFile));
            CsvReader csv = new CsvReader();
            this.parser = csv.parse(input);
        }
        catch (FileNotFoundException e){
            System.err.print("File not found!");
            e.printStackTrace();
        }
        catch (IOException e){
            System.err.print("Parser error!");
            e.printStackTrace();
        }

        //Init header

        //Populate hasNext
        if(lastRow != null)
            this.hasNext = true;
        else{
            
            lastRow = this.parser.nextRow();
        }

    }

    @Override
    public boolean writeBulk(List<List<String>> userList) {
        return false;
    }
}
