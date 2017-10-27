package ch.epfl.sweng.qeeqbii;

/**
 * Created by Nicolas on 12/10/2017.
 *
 */

public class UserInformation {

    public String lastname;
    public String firstname;
    public String allergies;
    public String gout;

    public UserInformation(String firstname, String lastname, String allergies, String gout) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.allergies = allergies;
        this.gout = gout;
    }


}