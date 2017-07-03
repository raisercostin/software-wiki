package ro.dcsi.internship;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Created by Catalin on 7/3/2017.
 */
public class CsvDB implements UserDB,UserSync {
    List<User> database;
    CsvReader reader;

    public CsvDB(String inputFile) {
        reader=new CsvReader(inputFile);
    }

    @Override
    public boolean userExists(String id) {
        for(User u:database)
            if(u.getAttributeValue("id")!=null && u.getAttributeValue("id")== id)
                return true;
        return false;
    }

    @Override
    public Optional<User> getUser(String id) {
        for(User u:database)
            if(u.getAttributeValue("id")!=null && u.getAttributeValue("id")== id)
                return Optional.of(u);
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(String id) {
        Optional<User> user = getUser(id);
        if(user.isPresent()){
            database.remove(user.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        boolean remove = deleteUser(user.getId());
        if(remove){
            database.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        database.add(user);
        return true;
    }

    @Override
    public Iterator<User> iterator() {
        return new CsvDBIterator();
    }

    @Override
    public Iterator<User> readUsers() {
        database=reader.readUsers();
        return iterator();
    }

    class CsvDBIterator implements Iterator<User>{
        int currentIndex;

        public CsvDBIterator() {
            currentIndex=0;
        }

        @Override
        public boolean hasNext() {
            if(currentIndex < database.size())
                return true;
            return false;
        }

        @Override
        public User next() {
            if(hasNext())
                return database.get(currentIndex++);
            return new User("-1",new HashMap<>());
        }
    }
}
