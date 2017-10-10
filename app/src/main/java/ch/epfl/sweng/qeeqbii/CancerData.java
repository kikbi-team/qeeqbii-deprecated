package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 03.10.17.
 */

public class CancerData {
    private String mAgent;

    public String getmGroup() {
        return mGroup;
    }

    public void setmGroup(String mGroup) {
        this.mGroup = mGroup;
    }

    private String mGroup;

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
