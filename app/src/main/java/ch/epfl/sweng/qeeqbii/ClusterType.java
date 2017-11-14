package ch.epfl.sweng.qeeqbii;

import java.io.IOException;

/**
 * Created by guillaume on 14/11/17.
 */

enum ClusterType {
    CHEESE,
    WINE,
    MEAT,
    CHIPS,
    VEGETABLES,
    APPLE,
    BOULANGERIE,
    UNDETERMINED;

    static ClusterType getClusterType(String name) throws IOException
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

        throw new IOException("String passed in argument does not correspond to any cluster type");
    }

}
