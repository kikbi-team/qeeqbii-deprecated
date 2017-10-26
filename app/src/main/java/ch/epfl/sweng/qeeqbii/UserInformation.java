package ch.epfl.sweng.qeeqbii;

/**
 * Created by Nicolas on 12/10/2017.
 *
 */

class UserInformation {

    private final String lastname;
    private final String firstname;
    private final String allergies;
    private final String gout;

    public UserInformation(String firstname, String lastname, String allergies, String gout) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.allergies = allergies;
        this.gout = gout;
    }


}