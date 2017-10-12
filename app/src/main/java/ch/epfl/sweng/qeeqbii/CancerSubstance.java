package ch.epfl.sweng.qeeqbii;

/**
 * Created by adrien on 12/10/17.
 */

public class CancerSubstance {
    // Attributes
    private String mAgent;
    private String mGroup;

    // Set methods
    public void setmGroup(String mGroup) {
        this.mGroup = mGroup;
    }
    public void setmAgent(String mAgent) {
        this.mAgent = mAgent;
    }

    // Get methods
    public String getmGroup() {
        return mGroup;
    }
    public String getmAgent() {
        return mAgent;
    }

    //@Override
    public String toString() {
        return "Substance{" +
                "mAgent='" + mAgent + '\'' +
                ", mGroup='" + mGroup + '\'' +
                '}';
    }
}
