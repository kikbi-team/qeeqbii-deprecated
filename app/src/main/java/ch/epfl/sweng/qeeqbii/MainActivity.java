package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String BARCODE_READER = "ch.epfl.sweng.qeeqbii.mainBarcode";


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            CancerDataBase.readCSVFile(getApplicationContext());
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("UnusedParameters")
    public void cancerDataBaseShow(MenuItem item)
    {
        Intent intent = new Intent(this, CancerDataShowActivity.class);
        startActivity(intent);
    }

    public void readBarcode(MenuItem item) {
        Intent intent = new Intent(this, BarcodeActivity.class);
        startActivity(intent);
    }

    public void searchProductFromBarcode(View view) {
        Intent intent = new Intent(this, BarcodeToProductActivity.class);
        EditText editText = (EditText) findViewById(R.id.mainName);
        String barcode = editText.getText().toString();
        intent.putExtra(BARCODE_READER, barcode);
        startActivity(intent);
    }


    public void showGraphs(MenuItem item)
    {
        Intent intent = new Intent(this, Graphs.class);
        startActivity(intent);
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, MainApp.class);
        startActivity(intent);
    }

    public void cancerDataQuery(View view) {
        Intent intent = new Intent(this, CancerDataQueryActivity.class);

        startActivity(intent);
    }
}