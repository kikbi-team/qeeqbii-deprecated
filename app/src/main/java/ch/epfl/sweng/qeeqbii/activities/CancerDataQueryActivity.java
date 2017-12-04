package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ch.epfl.sweng.qeeqbii.cancer.CancerDataBase;
import ch.epfl.sweng.qeeqbii.cancer.CancerSubstance;
import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.cancer.LevenshteinQueryCancerDB;
import ch.epfl.sweng.qeeqbii.cancer.RatcliffQueryCancerDB;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCartStatistics;

public class CancerDataQueryActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_data_query);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.cancer_query_design);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void processPerfectMatchQueryResult(View view) {

        // Getting the text entered by the user in the text area provided for the query
        EditText query_field = (EditText) findViewById(R.id.cancerDataQueryTextField);
        String string_queried_substance = query_field.getText().toString();

        // Carrying out the query
        CancerSubstance queried_substance = new CancerSubstance();
        try {
            queried_substance = CancerDataBase.getSubstanceByName(string_queried_substance);
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
        LevenshteinQueryCancerDB levQuery = new LevenshteinQueryCancerDB();
        try {
            queried_substance = levQuery.query(string_queried_substance);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        // Print answer in TextView
        String query_answer = queried_substance.toString();
        TextView answer_field = (TextView) findViewById(R.id.queryCancerDataAnswerArea);
        answer_field.setText(query_answer);
    }

    public void processRatcliffQueryResult(View view) {
        // Getting the text entered by the user in the text area provided for the query
        EditText query_field = (EditText) findViewById(R.id.cancerDataQueryTextField);
        String string_queried_substance = query_field.getText().toString();

        EditText threshold = (EditText) findViewById(R.id.ratcliff_threshold_text);
        double threshold_value = Double.parseDouble(threshold.getText().toString());
        // Carrying out the query
        CancerSubstance queried_substance = new CancerSubstance();
        RatcliffQueryCancerDB ratcliffQuery = new RatcliffQueryCancerDB(threshold_value);
        try {
            queried_substance = ratcliffQuery.query(string_queried_substance);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        // Print answer in TextView
        String query_answer = queried_substance.toString();
        TextView answer_field = (TextView) findViewById(R.id.queryCancerDataAnswerArea);
        answer_field.setText(query_answer);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    public void cancerDataBaseShow(MenuItem item) {
        Intent intent = new Intent(this, CancerDataShowActivity.class);
        startActivity(intent);
    }


    public void readBarcode(MenuItem item) {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivity(intent);
    }


    public void showShoppingList(MenuItem view) {
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    public void showGraphs(MenuItem item) {
        Intent intent = new Intent(this, GraphsActivity.class);
        startActivity(intent);
    }

    public void cancerDataQuery(MenuItem item) {
        Intent intent = new Intent(this, CancerDataQueryActivity.class);
        startActivity(intent);
    }

    public void showRecentlyScannedProductsActivity(MenuItem item) {
        Intent intent = new Intent(this, RecentlyScannedProductsActivity.class);
        startActivity(intent);
    }

    public void backToMain(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showSavedProducts(MenuItem item) {
        Intent intent = new Intent(this, SavedProductsDatesActivity.class);
        startActivity(intent);
    }

    public void showStatistics(MenuItem item) {
        Intent intent = new Intent(this, ShoppingCartStatistics.class);
        startActivity(intent);
    }

    public void showChat(MenuItem item) {
        Intent intent = new Intent(this, MainActivityChat.class);
        startActivity(intent);
    }
}
