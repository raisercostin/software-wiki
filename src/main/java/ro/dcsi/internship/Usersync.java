package ro.dcsi.internship;

/**
 * Created by Catalin on 6/19/2017.
 */
public class Usersync {
    public static final int bulkDefine = 100;

    public Usersync() {
    }

    public void readUsers(String Filename){
        Translator translator = new TranslatorCSV();
        UserManager database = new UserManager(Usersync.bulkDefine);

        translator.setInputFile(Filename);
        database.setUserFile(translator);

        database.readUsers();
    }



    public static void main(String [ ] args){
        Translator translator = new TranslatorCSV();
        UserManager database = new UserManager(1);

        translator.setInputFile("date.csv");
        database.setUserFile(translator);

        database.readUsers();
        System.out.print(database.toString());

        database.readUsers();
        System.out.print(database.toString());

    }
}
