package ch.epfl.sweng.qeeqbii;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.gatech.gtri.bktree.BkTreeSearcher;
import edu.gatech.gtri.bktree.Metric;
import edu.gatech.gtri.bktree.MutableBkTree;
import info.debatty.java.stringsimilarity.Levenshtein;

import static java.lang.Integer.MAX_VALUE;


class CancerDataBase {

    // ATTRIBUTES
    static private List<CancerSubstance> msubstance_list = new ArrayList<>();
    static private HashMap<String, CancerSubstance> msubstance_map = new HashMap<>();
    // mopen_state takes value 0 if no file have been read and takes value 1 if readCSVfile() have
    // been called and succeeded in reading a CSV file
    static private int mopen_state;

    // Definition of the hamming distance that will be used to query the database
    // The Levenshtein distance is chosen here
    static private Metric<String> mhammingLevenshtein = new Metric<String>() {
        @Override
        public int distance(String x, String y) {
            Levenshtein levenshtein = new Levenshtein();
            return (int) Math.round(levenshtein.distance(x, y));
        }
    };
    static private MutableBkTree<String> mbkTree = new MutableBkTree<>(mhammingLevenshtein);

    // CONSTRUCTOR
    CancerDataBase() {
        // mopen_state defined to 0 at instantiation because no CSV files have been read
        mopen_state = 0;
    }


    // Method that reads a CSVFile
    static void readCSVFile(Context context) throws Exception {
        if (mopen_state == 1) {
            throw new Exception("Tried to open carcinogenic database whereas it's already done.\n");
        }

        Resources resources = context.getResources();
        InputStream inStream = resources.openRawResource(R.raw.original_classification_iarc_english);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, Charset.forName("UTF-8")));

        String line = null;
        // Step over the headers
        reader.readLine();
        try {
            while ((line = reader.readLine()) != null) {
                //split by ','
                String[] tokens = line.split(",");

                //read the data
                CancerSubstance new_substance = new CancerSubstance();

                // Filling of new_substance's attributes
                new_substance.setmId(Integer.parseInt(tokens[0]));
                new_substance.setmAgent(tokens[1]); //Here we can add the fact that the dataBase might have leaks
                new_substance.setmGroup(tokens[2]);

                // Filling of BK-Tree with the Agent name
                mbkTree.add(tokens[1]);

                //Note if we want to import an int or a double we have to write:
                //sample.setmGroup(Double.parseDouble(tokens[x]));
                //sample.setmGroup(Integer.parseInt(tokens[x]));
                msubstance_list.add(new_substance); //adds the sample to the table with all the data
                msubstance_map.put(new_substance.getmAgent(), new_substance);
            }
        }
        catch (IOException error) {
            Log.wtf("MainActivity", "CustomExeptions reading the data file in the .csv file" + line, error);
            error.printStackTrace();
        }

        // Change the mopen_state to value 1 because the file is read
        mopen_state = 1;
    }

    // Method that outputs a string containing a summary of all the substances and their categorization
    static String sendOutputReadyToPrint() throws Exception {
        if (mopen_state == 1) {
            String output = " id\tAgent\t\tGroup\n";
            for (int i = 0; i < msubstance_list.size(); i++) {
                output += msubstance_list.get(i).toString();
                output += "\n";
            }
            return output;
        } else {
            throw new Exception("Read the carcinogenic database before trying to print it.\n");
        }
    }

    // Method that queries a substance of the DataBase and outputs
    // the substance and its group classification if the substance is found
    // or an empty CancerSubstance object if the queried substance didn't match perfectly
    // with a substance of the database
    static CancerSubstance perfectMatchQuery(String queried_substance) throws NotOpenFileException, NullInputException {
        if (mopen_state == 0) {
            throw new NotOpenFileException("Read the carcinogenic database before trying to query it.\n");
        }
        if (queried_substance == null) {
            throw new NullInputException("Provided null string to perfectMatchQuery CancerDataBase's method.\n");
        }

        CancerSubstance output_substance = msubstance_map.get(queried_substance);

        if (output_substance == null) {
            return new CancerSubstance();
        }

        return output_substance;
    }


    static CancerSubstance levenshteinMatchQuery(String queried_substance, int max_distance)
            throws NotOpenFileException, NullInputException {
        if (mopen_state == 0) {
            throw new NotOpenFileException("Read the carcinogenic database before trying to query it.\n");
        }
        if (queried_substance == null) {
            throw new NullInputException("Provided null string to perfectMatchQuery CancerDataBase's method.\n");
        }

        BkTreeSearcher<String> searcher = new BkTreeSearcher<>(mbkTree);
        Set<BkTreeSearcher.Match<? extends String>> matches = searcher.search(queried_substance, max_distance);

        // We go through all the matches with distance less or equal than max_distance and select the match
        // that involves the least distance
        String kept_match = null;
        int min_dist = MAX_VALUE;
        for (BkTreeSearcher.Match<? extends String> match : matches) {
            if (match.getDistance() < min_dist) {
                kept_match = match.getMatch();
                min_dist = match.getDistance();
            }
        }

        // We check that there is indeed a match with distance less than max_distance and if not we output
        // an empty CancerSubstance
        CancerSubstance output_substance = new CancerSubstance();
        if (kept_match == null) {
            // Output the substance as an empty CancerSubstance (Query failed)
            return output_substance;
        }
        output_substance = msubstance_map.get(kept_match);
        return output_substance;
    }
}




