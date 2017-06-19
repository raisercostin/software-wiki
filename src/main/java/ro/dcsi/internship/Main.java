package ro.dcsi.internship;

/**
 * Created by Catalin on 6/19/2017.
 */
public class Main {


    public static void main(String [ ] args){
        Translator translator = new TranslatorCSV();
        UserManager database = new UserManager(100);

        translator.setInputFile("date.csv");
        database.setUserFile(translator);

        database.readUsers();

        System.out.print(database.toString());
    }
}
