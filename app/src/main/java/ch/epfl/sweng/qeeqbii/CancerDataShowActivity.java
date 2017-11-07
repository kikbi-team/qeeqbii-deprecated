package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class CancerDataShowActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.cancerDataShow);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Instantiation of a CancerDataBase object followed by:
        // reading of the CSV file using the readCSVFile method
        // sending of a message
        String message;
        try {
            message = CancerDataBase.sendOutputReadyToPrint();
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            message = "Failed to get the CancerDataBase in CancerDataShowActivity.";
        }

        // Capture the layout's; TextView and set the string as its text
        TextView reportedMessage = (TextView) findViewById(R.id.cancerDataBaseMessage);
        reportedMessage.setTextSize(12);
        reportedMessage.setTextColor(Color.rgb(200, 0, 0));
        reportedMessage.setText(message);
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
        Intent intent = new Intent(this, ShoppingCartActivity.class);
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
}