package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/19/2017.
 */
public class UserManager {
    private Translator userFile;
    private List<User> users;
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
              if(headers.get(i).equals("Name"))
                  bufferUser.setName(l.get(i));
              else if (headers.get(i).equals("EMail"))
                  bufferUser.setEmail(l.get(i));
              else
                  bufferUser.addExtraField(l.get(i));
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
        return builder.toString();
    }
}
