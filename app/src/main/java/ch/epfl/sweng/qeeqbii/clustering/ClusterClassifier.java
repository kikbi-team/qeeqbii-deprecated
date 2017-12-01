package ch.epfl.sweng.qeeqbii.clustering;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.custom_exceptions.NotOpenFileException;
import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.ClusterTypeSecondLevel;

public class ClusterClassifier {

    private static final Map<String, NutrientVector > mapCenters = new HashMap<>();

    private static final ArrayList<String> nutrientKeys = new ArrayList<>();

    private static boolean openState;

    static ArrayList<String> getNutrientKeys() {
        return (ArrayList<String>) nutrientKeys.clone();
    }


    public static void readClusterNutrientCentersFile() {

        if (!openState) {

            try {
                File file = new File("/app/src/main/res/raw/cluster_nutrient_centers.csv");
                InputStream inStream = new FileInputStream(file);
                //InputStream inStream = resources.openRawResource(R.raw.cluster_nutrient_centers);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, Charset.forName("UTF-8")));

                // Step over the header and get the list of nutrients available
                String line = reader.readLine();
                String[] headerArray = line.split(",");
                headerArray = Arrays.copyOfRange(headerArray, 2, headerArray.length);
                nutrientKeys.addAll(Arrays.asList(headerArray));


                while ((line = reader.readLine()) != null) {
                    String[] elements = line.split(",");

                    List<String> valuesList = Arrays.asList(Arrays.copyOfRange(elements, 2, elements.length));
                    // We get the category name and we take care of only takin the second category name
                    String category = elements[1].split("/")[2];
                    NutrientVector nutrientVector = new NutrientVector();

                    for (int i = 0; i < nutrientKeys.size(); i++) {
                        double nutrientValue = Double.parseDouble(valuesList.get(i));
                        nutrientVector.addComponent(nutrientKeys.get(i), nutrientValue);
                    }

                    mapCenters.put(category, nutrientVector);
                }
                openState = true;

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    public static ClusterType getClusterTypeFromNutrients(NutrientVector queriedVector) throws NotOpenFileException {
        // First we need to find the closest nutrient vector to the queried nutrientVector
        // To do so we will use the euclidean norm

        double smallestDistance = Double.POSITIVE_INFINITY;
        double currentDistance;
        NutrientVector currentClusterCenter;
        String bestClusterName = "";
        for (ClusterTypeSecondLevel cluster : ClusterTypeSecondLevel.values()) {

            // We get the cluster name and we get the corresponding cluster center from mapCenters
            if (mapCenters.containsKey(cluster.name())) {
                currentClusterCenter =  mapCenters.get(cluster.name());

                currentDistance = queriedVector.computeDistance(currentClusterCenter);
                if (currentDistance < smallestDistance) {
                    smallestDistance = currentDistance;
                    bestClusterName = cluster.name();
                }
            }
        }

        return ClusterTypeSecondLevel.getClusterType(bestClusterName);
    }



    public static boolean isRead() {
        return openState;
    }

}
