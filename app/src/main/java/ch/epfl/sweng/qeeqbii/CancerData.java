package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 03.10.17.
 */

public class CancerData {
    private String mAgent;
    private String mGroup;
    private int mLabel;

    public int getmLabel() {return mLabel;}
    public void setmLabel(int mLabel) {this.mLabel = mLabel;}
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

    @Override
    public String toString() {
        return "CancerData{" +
                "mAgent='" + mAgent + '\'' +
                ", mGroup='" + mGroup + '\'' +
                '}';
    }
}
