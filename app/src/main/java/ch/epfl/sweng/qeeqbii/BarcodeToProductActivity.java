package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by guillaume on 10/10/17.
 *
 */

public class BarcodeToProductActivity extends AppCompatActivity {

    private String barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_details);

        Intent intent = getIntent();
        barcode = intent.getStringExtra(MainActivity.BARCODE_READER);

        // Capture the layout's; TextView and set the string as its text
        final TextView txt = (TextView) findViewById(R.id.product_details);
        txt.setTextSize(20);
        txt.setTextColor(Color.rgb(0, 0, 0));
        /*new OpenFoodQuery() {
            @Override
            public void onPostExecute(String result) {
                //TextView txt = (TextView) findViewById(R.id.product_details);
                try {
                    if (result.startsWith("ERROR:"))
                        throw new Exception(result);

                    HTTPRequestResponse response = new HTTPRequestResponse(result);
                    String s = response.GetProductName("fr");
                    s += "\n\nIngredients: " + response.GetProductIngredients("fr");
                    s += "\n\nQuantity: " + response.GetProductQuantity();
                    s += "\n\nNutrients: (per 100g)\n" + response.GetProductNutrients("fr");
                    Log.d("STATE", "Product found: " + s);
                    product_resp = response;
                    txt.setText(s);

                } catch (Exception e) {
                    txt.setText(e.getMessage());
                }

            }
        }.execute(barcode);*/
        OpenFoodQuery.ShowProduct(barcode, txt);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // go back to main activity after BACK button was pressed
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void searchHarmfulIngredients(View view) {

        if (!RecentlyScannedProducts.contains(barcode))
        {
            return;
        }

        TextView txt_harmfull_ing = (TextView) findViewById(R.id.harmful_ingredients);

        String parsed_ingredients[];

        try {
            parsed_ingredients = RecentlyScannedProducts.GetProduct(barcode).GetParsedIngredients();
        } catch (ProductException e)
        {
            txt_harmfull_ing.setText(e.getMessage());
            return;
        }


        String str = "";
        try {
            CancerDataBase.readCSVFile(getApplicationContext());
            for (String ingredient : parsed_ingredients) {
                str += CancerDataBase.levenshteinMatchQuery(ingredient).toString() + "\n";

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        txt_harmfull_ing.setText(str);

    }

    public void showNutrientsGraphs(View view) {
        Intent intent = new Intent(this, Graphs.class);
        intent.putExtra("barcode",barcode);
        startActivity(intent);
    }


}
