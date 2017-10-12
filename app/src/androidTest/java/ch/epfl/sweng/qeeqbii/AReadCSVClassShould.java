package ch.epfl.sweng.qeeqbii;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AReadCSVClassShould {

    private CancerDataShowActivity cancerData = new CancerDataShowActivity(); //Causes the problem

    private List<String> importedData = new ArrayList<>();

    @Test
    public void readInAFile() {
        //int testFileName = R.raw.test_text;
        int fileName = R.raw.test_text;
        List<String> expected = Arrays.asList("aAgent","Group", "0","Formaldehyde");
        cancerData.readCSVFile(fileName);
        List<String> data = cancerData.getImportedDataString();
        //InputStream inputstream = new FileInputStream("res/raw/test_text.csv");
        //List<String> data = Arrays.asList("aAgent","Group", "0","Formaldehyde";
        assertEquals(expected, data);
    }
}
