package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

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
        reportedMessage.setTextSize(12);
        reportedMessage.setTextColor(Color.rgb(200,0,0));
        reportedMessage.setText(message);
    }
}