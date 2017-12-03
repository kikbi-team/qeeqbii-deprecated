package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.clustering.ClusterClassifier;
import ch.epfl.sweng.qeeqbii.clustering.NutrientNameConverter;
import ch.epfl.sweng.qeeqbii.clustering.NutrientVector;
import ch.epfl.sweng.qeeqbii.custom_exceptions.BadlyFormatedFile;
import ch.epfl.sweng.qeeqbii.custom_exceptions.IllegalNutrientKeyException;
import ch.epfl.sweng.qeeqbii.custom_exceptions.NotOpenFileException;

import static junit.framework.Assert.assertEquals;


public class NutrientVectorTest {

    @Rule
    public final ActivityTestRule<BarcodeScannerActivity> mActivityRule =
            new ActivityTestRule<>(BarcodeScannerActivity.class);


    private NutrientVector nutrientVectorTest1;
    private NutrientVector nutrientVectorTest2;

    @Before
    public void initialize() {
        NutrientNameConverter.readCSVFile(mActivityRule.getActivity().getApplicationContext());

        try {
            ClusterClassifier.readClusterNutrientCentersFile(mActivityRule.getActivity().getApplicationContext());

            HashMap<String, Double> nutrientMap1 = new HashMap<>();
            nutrientMap1.put("Sel (g)", 3.0);
            nutrientMap1.put("Acides gras saturés (g)", 4.0);
            nutrientMap1.put("lourd", 7.0);

            HashMap<String, Double> nutrientMap2 = new HashMap<>();
            nutrientMap2.put("Sel (g)", 2.0);
            nutrientMap2.put("Glucides (g)", 1.0);
            nutrientMap2.put("caca", 5.0);

            nutrientVectorTest1 = new NutrientVector(nutrientMap1);
            nutrientVectorTest2 = new NutrientVector(nutrientMap2);
        }
        catch (NotOpenFileException|BadlyFormatedFile e) {
            System.err.println(e.getMessage());
            nutrientVectorTest1 = null;
            nutrientVectorTest2 = null;
        }


    }


    @Test
    public void canConvertNutrientName() {

        String toConvert = "Acides gras saturées (g)";
        String converted = "";
        try {
            converted = NutrientNameConverter.convertToStandardName(toConvert);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        assertEquals(converted, "Acides gras saturés (g)");
    }


    @Test
    public void canCreateNutrientVectorFromNutrientMap() {

        int numberOfStandardKeys = 0;
        try {
            numberOfStandardKeys = NutrientNameConverter.getStandardNutrientNames().size();;
        }
        catch (NotOpenFileException e) {
            System.err.println(e.getMessage());
        }

        try {
            assertEquals(nutrientVectorTest1.getDim(), numberOfStandardKeys);
            assertEquals(nutrientVectorTest1.getComponent("Sel (g)"), 3.0);
            assertEquals(nutrientVectorTest1.getComponent("Glucides (g)"), 0.0);
        }
        catch (IllegalNutrientKeyException e) {
            System.err.println(e.getMessage());
        }

        // Test if an exception is thrown when asking for an invalid key
        boolean wentInException = false;
        try {
            nutrientVectorTest1.getComponent("lourd");
        }
        catch (IllegalNutrientKeyException e) {
            wentInException = true;
        }
        assertEquals(wentInException, true);
    }

    @Test
    public void canComputeDiff() {
        try {
            NutrientVector diffTest = nutrientVectorTest1.diff(nutrientVectorTest2);

            assertEquals(diffTest.getComponent("Sel (g)"), 1.0);
            assertEquals(diffTest.getComponent("Glucides (g)"), -1.0);
            assertEquals(diffTest.getComponent("Acides gras saturés (g)"), 4.0);
            assertEquals(diffTest.getDim(), NutrientNameConverter.getStandardNutrientNames().size());
        }
        catch (NotOpenFileException|IllegalNutrientKeyException e) {
            System.err.println(e.getMessage());
            assertEquals(1.0, 2.0);
        }
    }


    @Test
    public void canComputeDistance() {
        try {
            NutrientVector zeroVector = new NutrientVector(new HashMap<String, Double>());
            double normZero = nutrientVectorTest1.computeDistance(zeroVector);
            double normSecond = nutrientVectorTest1.computeDistance(nutrientVectorTest2);
            assertEquals(normZero, 5.0);
            assertEquals(normSecond, Math.sqrt(18.0));
        }
        catch (NotOpenFileException e) {
            e.getMessage();
            assertEquals(1.0, 2.0);
        }
    }
}
