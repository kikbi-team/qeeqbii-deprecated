package ch.epfl.sweng.bootcamp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text             //Bootcamps version
        TextView textView = (TextView) findViewById(R.id.greetingMessage);
        textView.setTextSize(30);
        textView.setTextColor(Color.BLUE);
        textView.setText(getString(R.string.hello) + " " + message + "!");


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message); // Get the Intent that started this activity and extract the string

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(getString(R.string.hello)); //Gets the messages that is fetched

        // Capture the layout's; TextView and set the string as its text
        TextView reportedMessage = (TextView) findViewById(R.id.greetingMessage);
        reportedMessage.setTextSize(20);
        reportedMessage.setTextColor(Color.rgb(0,0,0));
        String[] barcode = new String[1];
        barcode[0] = "7610848337010";
        new OpenFoodQuery()
        {
            @Override public void onPostExecute(String result)
            {
                HTTPRequestResponse response = new HTTPRequestResponse(result);
                TextView txt = (TextView) findViewById(R.id.greetingMessage);
                txt.setText(response.GetProductName("fr") + "\n\nIngredients: " + response.GetProductIngredients("fr") +
                        "\n\nNutrients: (per 100g)\n " + response.GetProductNutrients("fr"));
            }
        }.execute(barcode);
        //reportedMessage.setText(getString(R.string.hello) + message + getString(R.string.exclamation)); //BootCamp version
        //reportedMessage.setText(message); // qeeqbi version
    }
}
