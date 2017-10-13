package ch.epfl.sweng.qeeqbii;

/**
 * Created by adrien on 12/10/17.
 */

public class CancerSubstance {
    // Attributes
    private String mAgent;
    private String mGroup;
    private int mLabel;

    public int getmLabel() {return mLabel;}
    public void setmLabel(int mLabel) {this.mLabel = mLabel;}
    public String getmGroup() {
        return mGroup;
    }
    public void setmGroup(String mGroup) { this.mGroup = mGroup;}
    public String getmAgent() {
        return mAgent;
    }
    public void setmAgent(String mAgent) { this.mAgent = mAgent;}
    public String toString() {
        return "Substance{" +
                "mAgent='" + mAgent + '\'' +
                ", mGroup='" + mGroup + '\'' +
                '}';
    }
}
