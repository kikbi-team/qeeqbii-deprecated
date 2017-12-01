package ch.epfl.sweng.qeeqbii.clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.epfl.sweng.qeeqbii.custom_exceptions.NotOpenFileException;


public class NutrientVector {

    private Map<String,Double> nutrientMap;

    public NutrientVector() {
        nutrientMap = new HashMap<>();
    }

    // Create a NUtrient vector from getParsedNutrient vector of the Product class
    public NutrientVector(Map<String, Double> inputNutrientMap) throws NotOpenFileException {
        if (!ClusterClassifier.isRead()) {
            throw new NotOpenFileException("Trying to consruct a NutrientVector instance from getParsedNutrients" +
                    " but the cluster_nutrient_centers.csv file is not read");
        }
        nutrientMap = new HashMap<>();
        // Check if inputMap's keys are contained in ClusterClassifier.getNutrientKeys()
        Set<String> inputKeys = inputNutrientMap.keySet();
        for (String inputKeyElem : inputKeys){
            if (!ClusterClassifier.getNutrientKeys().contains(inputKeyElem)) {
                throw new IllegalArgumentException("Key " + inputKeyElem +
                        " from getParsedNutrients is contained ClusterClassifier");
            }
        }

        // Conversion of the getParsedNutrients Map format in the NutrientVector format
        for (String nutrientName : ClusterClassifier.getNutrientKeys()) {
            if (inputNutrientMap.containsKey(nutrientName)) {
                nutrientMap.put(nutrientName, inputNutrientMap.get(nutrientName));
            }
            else {
                nutrientMap.put(nutrientName, 0.0);
            }
        }

    }

    public void addComponent(String nutrientName, double newValue) {
        nutrientMap.put(nutrientName, newValue);
    }

    public int getDim() {
        return nutrientMap.size();
    }


    public double getComponent(String key) {
        return nutrientMap.get(key);
    }


    private NutrientVector diff(NutrientVector substractVector) throws NotOpenFileException {

        if (this.getDim() != substractVector.getDim()) {
            throw new IllegalArgumentException("Substracting NutrientVector does not have the right dimension.\n");
        }
        if (!ClusterClassifier.isRead()) {
            throw new NotOpenFileException("Trying to consruct a NutrientVector instance from getParsedNutrients" +
                    " but the cluster_nutrient_centers.csv file is not read");
        }

        NutrientVector resultVector = new NutrientVector();
        double newValue;
        for (String nutrientName : ClusterClassifier.getNutrientKeys()) {
            newValue = this.getComponent(nutrientName) - substractVector.getComponent(nutrientName);
            resultVector.addComponent(nutrientName, newValue);
        }
        return resultVector;
    }

    private double computeNorm() {
        double sum = 0;
        for (double nutrientValue : nutrientMap.values()) {
            sum += Math.pow(nutrientValue, 2);
        }
        return Math.sqrt(sum);
    }

    public double computeDistance(NutrientVector queriedVector) throws NotOpenFileException{
        return this.diff(queriedVector).computeNorm();
    }

}
