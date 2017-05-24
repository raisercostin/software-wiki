package ro.dcsi.internship;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	new CsvExporter().export("src/main/resources/users.csv", "src/main/resources/usersOut.csv");
    }
}
