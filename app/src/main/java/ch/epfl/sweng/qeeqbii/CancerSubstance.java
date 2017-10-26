package ch.epfl.sweng.qeeqbii;

public class CancerSubstance {

    // Attributes
    private int mId;
    private String mAgent;
    private String mGroup;
    private int mLabel;
    // Constructor that defines an empty substance

    public CancerSubstance() {
        mId = -1;
        mAgent = "empty";
        mGroup = "empty";
    }

    // Getters methods
    public int getmId() {
        return mId;
    }

    // Setters methods
    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmLabel() {
        return mLabel;
    }

    public void setmLabel(int mLabel) {
        this.mLabel = mLabel;
    }

    public String getmGroup() {
        return mGroup;
    }

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
