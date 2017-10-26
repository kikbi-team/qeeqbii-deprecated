package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static ch.epfl.sweng.qeeqbii.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    public static final String BARCODE_READER = "ch.epfl.sweng.qeeqbii.mainBarcode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
    }

    @SuppressWarnings("UnusedParameters")
    public void cancerDataBaseShow(View view) {
        Intent intent = new Intent(this, CancerDataShowActivity.class);
        startActivity(intent);
    }

    public void readBarcode(View view) {
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

    public void showGraphs(View view) {
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