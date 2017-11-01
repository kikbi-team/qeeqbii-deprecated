package ch.epfl.sweng.qeeqbii;

/**
 * Created by adrien on 12/10/17.
 *
 */

class CancerSubstance {

    // Attributes
    private int mId;
    private String mAgent;
    private String mGroup;
    // --Commented out by Inspection (27.10.17 14:36):private int mLabel;
    // Constructor that defines an empty substance
    CancerSubstance() {
        mId = -1;
        mAgent = "empty";
        mGroup = "empty";
    }

    // Getters methods
    public int getmId() {
        return mId;
    }


    // setters methods
    public void setmId(int mId) {
        this.mId = mId;
    }

// --Commented out by Inspection START (26.10.17 20:07):
//    public int getmLabel() {
//        return mLabel;
//    }
// --Commented out by Inspection STOP (26.10.17 20:07)

// --Commented out by Inspection START (26.10.17 20:08):
//    public void setmLabel(int mLabel) {
//        this.mLabel = mLabel;
//    }
// --Commented out by Inspection STOP (26.10.17 20:08)

// --Commented out by Inspection START (26.10.17 20:08):
//    public String getmGroup() {
//        return mGroup;
//    }
// --Commented out by Inspection STOP (26.10.17 20:08)

    public void setmGroup(String mGroup) {
        this.mGroup = mGroup;
    }

    public String getmAgent() {
        return mAgent;
    }

    public void setmAgent(String mAgent) {
        this.mAgent = mAgent;
    }

    //METHODS
    public String toString() {
        return "Substance{" +
                "mId = " + Integer.toString(mId) +
                ", mAgent = '" + mAgent + "\'" +
                ", mGroup = '" + mGroup + "\'" +
                '}';
    }
}
