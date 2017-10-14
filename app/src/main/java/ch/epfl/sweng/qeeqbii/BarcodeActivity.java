package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static ch.epfl.sweng.qeeqbii.MainActivity.BARCODE_READER;

public class BarcodeActivity extends AppCompatActivity {

    protected String last_barcode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result

            TextView reportedMessage = (TextView) findViewById(R.id.barcodeMessage);
            reportedMessage.setText(scanResult.toString());
            last_barcode = scanResult.getContents();
        }
    }

    public void searchProductFromScannedBarcode(View view)
    {
        Intent intent = new Intent(this, BarcodeToProductActivity.class);
        intent.putExtra(BARCODE_READER, last_barcode);
        startActivity(intent);
    }
}
