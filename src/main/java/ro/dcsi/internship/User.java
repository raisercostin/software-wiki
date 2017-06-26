package ro.dcsi.internship;

public class User {
    public final String surname;
    public final String firstname;
    public final String age;
    public final String nationality;
    public final String country;

    public User(String surname, String firstname, String age, String nationality, String country) {
        this.surname = surname;
        this.firstname = firstname;
        this.age = age;
        this.nationality = nationality;
        this.country = country;
    }

    @Override
    public String toString() {
        return "User [surname=" + surname + ", firstname=" + firstname + ", age=" + age + ", nationality=" + nationality
                + ", country=" + country + "]";
    }
}
