package ro.dcsi.internship;

import java.util.Iterator;

/**
 * Created by Catalin on 6/19/2017.
 */
public class Usersync {
    public static final int bulkDefine = 100;
    private UserManager database;

    public Usersync() {
    }

    public void readUsers(String Filename){
        Translator translator = new TranslatorCSV();
        database = new UserManager(Usersync.bulkDefine);

        translator.setInputFile(Filename);
        database.setUserFile(translator);

        database.readUsers();
    }



    public static void main(String [ ] args){
        Translator translator = new TranslatorCSV();
        UserManager database = new UserManager(1);
        boolean ok;

        translator.setInputFile("date.csv");
        database.setUserFile(translator);

        ok = database.readUsers();
        if(!ok)
            return ;

        Iterator<User> userIterator = database.iterator();
        while(userIterator.hasNext())
            System.out.print(userIterator.next().toString() + '\n');

    }
}
