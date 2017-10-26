package ch.epfl.sweng.qeeqbii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CancerDataQueryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_data_query);
    }

    public void processPerfectMatchQueryResult(View view) {

        // Getting the text entered by the user in the text area provided for the query
        EditText query_field = (EditText) findViewById(R.id.cancerDataQueryTextField);
        String string_queried_substance = query_field.getText().toString();

        // Carrying out the query
        CancerSubstance queried_substance = new CancerSubstance();
        try {
            queried_substance = CancerDataBase.perfectMatchQuery(string_queried_substance);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        // Print answer in TextView
        String query_answer = queried_substance.toString();
        TextView answer_field = (TextView) findViewById(R.id.queryCancerDataAnswerArea);
        answer_field.setText(query_answer);
    }


    public void processLevenshteinQueryResult(View view) {

        // Getting the text entered by the user in the text area provided for the query
        EditText query_field = (EditText) findViewById(R.id.cancerDataQueryTextField);
        String string_queried_substance = query_field.getText().toString();

        // Carrying out the query
        CancerSubstance queried_substance = new CancerSubstance();
        try {
            queried_substance = CancerDataBase.levenshteinMatchQuery(string_queried_substance);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        // Print answer in TextView
        String query_answer = queried_substance.toString();
        TextView answer_field = (TextView) findViewById(R.id.queryCancerDataAnswerArea);
        answer_field.setText(query_answer);
    }


}
