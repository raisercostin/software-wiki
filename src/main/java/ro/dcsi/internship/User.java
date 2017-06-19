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

    protected User() {
        extraFields=new ArrayList<String>();
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void addExtraField(String Value){
        extraFields.add(Value);
    }

    @Override
    public String toString() {
        String rez;
        rez= "User{" + "name='" + name + '\'' + ", email='" + email + '\'';
        StringBuilder builder = new StringBuilder(rez);

        for(String e:extraFields)
            builder.append(' ' + e + ' ');

        builder.append("}");
        return builder.toString();
    }
}