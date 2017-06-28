package ro.dcsi.internship;

import java.util.Iterator;

/**
 * Created by Catalin on 6/19/2017.
 */
public class FileUserManger implements UserSync {
    public static final int bulkDefine = 100;
    private UserManager2 database;

    public FileUserManger() {
    }

    public UserManager2 readUsersAsManager(String Filename) {
        Translator translator = new TranslatorCSV();
        database = new UserManager2(FileUserManger.bulkDefine);

        translator.setInputFile(Filename);
        database.setReader(translator);
        database.readUsers();

        return database;
    }

    public void saveFile(String fileName) {
        Translator writeTranslator, readTranslator;
        UserManager2 localDatabase = new UserManager2(FileUserManger.bulkDefine);
        String writeFileName;
        int location;

        // Read Users
        readTranslator = new TranslatorCSV();
        readTranslator.setInputFile(fileName);
        localDatabase.setReader(readTranslator);
        localDatabase.readUsers();

        // Write Users
        location = fileName.lastIndexOf('.');
        writeFileName = fileName.substring(0, location) + "_backup" + fileName.substring(location);

        writeTranslator = new TranslatorCSV();
        writeTranslator.setOutputFile(writeFileName);

        localDatabase.setWriter(writeTranslator);
        localDatabase.writeUsers();
    }

    public static void main(String[] args) {
        FileUserManger app = new FileUserManger();
        app.saveFile("src/test/CSV/headerslarge.csv");
    }

    @Override
    public Iterator<User> readUsers(String filename) {
        UserManager2 database = readUsersAsManager(filename);
        Iterator<User> users = database.iterator();
        return users;
    }
}
