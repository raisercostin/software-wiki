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

    public UserManager readUsers(String Filename){
        Translator translator = new TranslatorCSV();
        database = new UserManager(Usersync.bulkDefine);

        translator.setInputFile(Filename);
        database.setReader(translator);
        database.readUsers();

        return database;
    }

    public void saveFile(String fileName){
        Translator writeTranslator,readTranslator;
        UserManager localDatabase = new UserManager(Usersync.bulkDefine);
        String writeFileName;
        int location;

        //Read Users
        readTranslator = new TranslatorCSV();
        readTranslator.setInputFile(fileName);
        localDatabase.setReader(readTranslator);
        localDatabase.readUsers();

        //Write Users
        location=fileName.lastIndexOf('.');
        writeFileName = fileName.substring(0,location) + "_backup" + fileName.substring(location);

        writeTranslator=new TranslatorCSV();
        writeTranslator.setOutputFile(writeFileName);

        localDatabase.setWriter(writeTranslator);
        localDatabase.writeUsers();
    }



    public static void main(String [ ] args){
        Usersync app = new Usersync();
        app.saveFile("src/test/CSV/headerslarge.csv");
    }
}
