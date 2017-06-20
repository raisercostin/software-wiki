package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Catalin on 6/19/2017.
 */
public class UserManager implements Iterable<User>{
    private Translator userFile;
    private List<User> users;
    private UserIterator instance=null;
    private int maxRead;

    public UserManager(int maxRead) {
        userFile=null;
        users=null;
        this.maxRead=maxRead;
    }

    public void setUserFile(Translator userFile) {
        this.userFile = userFile;
    }

    public boolean readUsers(){
        if(userFile==null)
            return false;

        //Variables
        List<List<String>> buffer;
        List<String> headers;
        User bufferUser;
        int i;

        //Read fields
        buffer = userFile.readBulk(this.maxRead);

        //Check error
        if(buffer == null)
            return false;

        //Read headers + init
        headers = userFile.getHeaders();
        users = new ArrayList<User>();


        //Convert to users
        for(List<String> l : buffer){
            bufferUser = new User();
            for(i=0;i<l.size();i++){

               //Match header
              if(headers.get(i).toLowerCase().equals("name"))
                  bufferUser.setName(l.get(i));
              else if (headers.get(i).toLowerCase().equals("email"))
                  bufferUser.setEmail(l.get(i));
              else {
                  bufferUser.addExtraField(l.get(i),headers.get(i));
              }
            }
            users.add(bufferUser);
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(User u:users)
            builder.append(u.toString() + '\n');

        if(userFile.hasNext()) {
            this.readUsers();
            builder.append(this.toString());
        }

        return builder.toString();
    }

    public class UserIterator implements Iterator<User>{
        private int currentIndex;


        public UserIterator() {
            currentIndex=0;
        }

        public boolean hasNext() {
            if(currentIndex < users.size())
                return true;
            return false;
        }

        public User next() {
            User result = users.get(currentIndex++);
            if(currentIndex == users.size() && userFile.hasNext()) {
                readUsers();
                currentIndex=0;
            }
            return result;
        }
    }


    public Iterator<User> iterator() {
        if(instance==null)
            instance=new UserIterator();
        return instance;
    }
}
