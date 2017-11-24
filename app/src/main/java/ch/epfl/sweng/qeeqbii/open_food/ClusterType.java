package ch.epfl.sweng.qeeqbii.open_food;

/**
 * Created by guillaume on 14/11/17.
 * Interface implementing ClusterType. Methods common for all cluster type levels.
 */

public interface ClusterType {
    ClusterType getParent();

    ClusterType[] getChildren();

    String toString();

    Integer getImageId();
}