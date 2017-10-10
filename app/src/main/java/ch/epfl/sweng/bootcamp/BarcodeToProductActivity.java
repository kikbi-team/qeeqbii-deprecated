package ch.epfl.sweng.bootcamp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by guillaume on 10/10/17.
 */

public class BarcodeToProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_details);

        Intent intent = getIntent();
        String barcode = intent.getStringExtra(MainActivity.BARCODE_READER);
        String[] barcode_array= new String[1];
        barcode_array[0] = barcode;

        // Capture the layout's; TextView and set the string as its text
        final TextView txt = (TextView) findViewById(R.id.product_details);
        txt.setTextSize(20);
        txt.setTextColor(Color.rgb(0,0,0));
        new OpenFoodQuery()
        {
            @Override public void onPostExecute(String result)
            {
                HTTPRequestResponse response = new HTTPRequestResponse(result);
                TextView txt = (TextView) findViewById(R.id.product_details);
                txt.setText(response.GetProductName("fr") + "\n\nIngredients: " + response.GetProductIngredients("fr") +
                        "\n\nNutrients: (per 100g)\n " + response.GetProductNutrients("fr"));
            }
        }.execute(barcode);
    }
}
