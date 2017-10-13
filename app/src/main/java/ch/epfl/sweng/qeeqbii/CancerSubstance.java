package ch.epfl.sweng.qeeqbii;

/**
 * Created by adrien on 12/10/17.
 */

public class CancerSubstance {

    // Constructor that defines an empty substance
    public CancerSubstance() {
        mId = -1;
        mAgent = "empty";
        mGroup = "empty";
    }

    // Attributes
    private int mId;
    private String mAgent;
    private String mGroup;

    // Set methods
    public void setmId(int mId) {
        this.mId = mId;
    }
    public void setmGroup(String mGroup) {
        this.mGroup = mGroup;
    }
    public void setmAgent(String mAgent) {
        this.mAgent = mAgent;
    }

    // Get methods
    public int getmId() { return mId; }
    public String getmGroup() {
        return mGroup;
    }
    public String getmAgent() {
        return mAgent;
    }

    //@Override
    public String toString() {
        return "Substance{" +
                "mId = " + Integer.toString(mId) +
                ", mAgent = '" + mAgent + "\'" +
                ", mGroup = '" + mGroup + "\'" +
                '}';
    }
}
