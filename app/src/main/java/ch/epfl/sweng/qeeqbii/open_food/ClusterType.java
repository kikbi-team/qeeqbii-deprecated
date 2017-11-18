package ch.epfl.sweng.qeeqbii.open_food;

/**
 * Created by guillaume on 14/11/17.
 */

public enum ClusterType {
    CHEESE,
    WINE,
    MEAT,
    CHIPS,
    VEGETABLES,
    APPLE,
    BOULANGERIE,
    UNDETERMINED;

    static ClusterType getClusterType(String name)
    {
        if (name.toLowerCase().matches("cheese"))
        {
            return CHEESE;
        }
        if (name.toLowerCase().matches("wine"))
        {
            return WINE;
        }
        if (name.toLowerCase().matches("meat"))
        {
            return MEAT;
        }
        if (name.toLowerCase().matches("vegetables"))
        {
            return VEGETABLES;
        }
        if (name.toLowerCase().matches("apple"))
        {
            return APPLE;
        }
        if (name.toLowerCase().matches("chips"))
        {
            return CHIPS;
        }
        if (name.toLowerCase().matches("boulangerie"))
        {
            return BOULANGERIE;
        }

        return UNDETERMINED;
    }

}
