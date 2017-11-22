package ch.epfl.sweng.qeeqbii.open_food;

/**
 * Created by guillaume on 14/11/17.
 */

public abstract class ClusterType {
    private enum values {}

    abstract ClusterType getParent();

    abstract  ClusterType getChildren();

}

enum ClusterTypeFirstLevel {
    CHOCOLAT,
    BOISSONS_CHAUDES_FROIDES,
    GARNITURES_INGREDIENTS,
    FRUITS_ET_LEGUMES,
    VIANDE,
    BOULANGERIE,
    PRODUITS_SURGELES,
    MUEESLI_CEREALES,
    PRODUITS_LAITIERS_OEUFS,
    POISSON_FRUITS_DE_MER,
    APERITIF,
    UNDETERMINED


}

enum ClusterTypeSecondLevel {
    BONBONS_CHEWING_GUM,
    CHOCOLAT,
    SAIN_BIO,
    BISCUITS_GAUFRES,
    DIVERS_CHOCOLAT,
    SPECIALITES,
    BLEVITA,
    JOURS_DE_FETES_EVENEMENTS,
    BISCUITS_POUR_ENFANTS,
    BISCUITS_POUR_ALLERGIQUES

    public ClusterTypeFirstLevel getParent()
    {
        switch(this) {
            case BONBONS_CHEWING_GUM:
            case CHOCOLAT:
            case SAIN_BIO:
            case BISCUITS_GAUFRES:
            case DIVERS_CHOCOLAT:
            case SPECIALITES:
            case BLEVITA:
            case JOURS_DE_FETES_EVENEMENTS:
            case BISCUITS_POUR_ENFANTS:
            case BISCUITS_POUR_ALLERGIQUES:
                return ClusterTypeFirstLevel.CHOCOLAT;


        }
    }

}


/*
class ClusterTypeFirstLevel extends ClusterType {
    private enum values {
        CHOCOLAT,
        BOISSONS_CHAUDES_FROIDES,
        GARNITURES_INGREDIENTS,
        FRUITS_ET_LEGUMES,
        VIANDE,
        BOULANGERIE,
        PRODUITS_SURGELES,
        MUEESLI_CEREALES,
        PRODUITS_LAITIERS_OEUFS,
        POISSON_FRUITS_DE_MER,
        APERITIF,
        UNDETERMINED;
    }

    public values getValues()
    {
        return values.BOISSONS_CHAUDES_FROIDES;
    }*/

    /*public enum CHOCOLAT {
        BONBONS_CHEWING_GUM,
        CHOCOLAT,
        SAIN_BIO,
        BISCUITS_GAUFRES,
        DIVERS,
        SPECIALITES,
        BLEVITA,
        JOURS_DE_FETES_EVENEMENTS,
        BISCUITS_POUR_ENFANTS,
        BISCUITS_POUR_ALLERGIQUES
    }*/



    /*static ClusterType getClusterType(String name)
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
    }*/