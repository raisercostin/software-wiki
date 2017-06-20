package ro.dcsi.internship;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/19/2017.
 */
class User{
    private String name;
    private String email;
    private List<String> extraFields;
    private List<String> extraFieldHeaders;

    protected User() {
        extraFields=new ArrayList<String>();
        extraFieldHeaders=new ArrayList<String>();
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void addExtraField(String Value,String Header){
        extraFields.add(Value);
        extraFieldHeaders.add(Header);
    }

    @Override
    public String toString() {
        String rez;
        rez= "User{" + "name='" + name + '\'' + ", email='" + email + '\'';

        StringBuilder builder = new StringBuilder(rez);
        int i;

        for(i=0;i<extraFields.size();i++) {
            builder.append(", " +extraFieldHeaders.get(i)+ "='" + extraFields.get(i)+ '\'');
        }

        builder.append("}");
        return builder.toString();
    }
}