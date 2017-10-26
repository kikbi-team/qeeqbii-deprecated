package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static ch.epfl.sweng.qeeqbii.MainActivity.BARCODE_READER;

public class BarcodeActivity extends AppCompatActivity {

    protected String last_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        last_barcode = "";

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

            // handle the scan result
            TextView reportedMessage = (TextView) findViewById(R.id.barcodeMessage);
            reportedMessage.setText(scanResult.toString());
            String barcode = scanResult.getContents();

            processBarcode(barcode);
        }
        else goToMain();
    }

    public void processBarcode(String barcode) {
        // go back to main activity if the barcode was invalid
        // or the scan was interrupted
        if(barcode == null || barcode == "") {
            Log.d("STATE", "Barcode is invalid, going back to main");
            goToMain();
        }
        else {
            Log.d("STATE", "Barcode " + barcode + " found, going to OpenFood");
            Intent intent = new Intent(this, BarcodeToProductActivity.class);
            intent.putExtra(BARCODE_READER, barcode);
            startActivity(intent);
        }
    }

    public void searchProductFromScannedBarcode(View view) {
        processBarcode(last_barcode);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            // go back to main activity after BACK button was pressed
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
