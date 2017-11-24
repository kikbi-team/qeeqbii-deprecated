package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCartStatistics;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.content.ContentValues.TAG;

/**
 * Created by sergei on 02/11/17.
 * Activity which uses the ZXing library with the me.dm7 wrapper
 * In order to scan barcodes
 * Each scanned barcode is then sent to the BarcodeToProductActivity via an intent
 * If the barcode is invalid or the back button was pressed
 * MainActivity is started via an intent
 */

public class BarcodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    // different actions after barcode result:
    // OPENFOOD -- go to product description
    // SHOPPING_CART -- send barcode to shopping cart
    public static enum ACTION_TYPE {ACTION_OPENFOOD, ACTION_SHOPPING_CART};

    // default action
    public static final ACTION_TYPE ACTION_DEFAULT = ACTION_TYPE.ACTION_OPENFOOD;

    // Extra name for barcode
    public static final String EXTRA_BARCODE = "ch.epfl.sweng.qeeqbii.BarcodeScannerActivity.barcode";

    // Extra name for action
    public static final String EXTRA_ACTION = "ch.epfl.sweng.qeeqbii.BarcodeScannerActivity.action";

    // action obtained on start
    private ACTION_TYPE action;

    // name of the permission
    public static final String CAMERA_PERMISSION = android.Manifest.permission.CAMERA;

    // code for permission call
    private static final int ZXING_CAMERA_PERMISSION = 1;

    // zxing library object
    private ZXingScannerView mScannerView;
    private ActionBarDrawerToggle mToggle;

    // on activity creation
    // ask permissions and launch barcode reader
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // disable autorotate if it was enabled
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        setContentView(R.layout.activity_barcode_scanner);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.barcode_scanner);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // request for camera permission if it is not present
        checkCameraPermissionAndRequest();

        // initialize ZXing scanner
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);

        // obtain action if present
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_ACTION)) {
            action = (ACTION_TYPE) extras.getSerializable(EXTRA_ACTION);
        } else {
            action = ACTION_DEFAULT;
        }

        Log.d("STATE", "EXTRA_ACTION is " + action);
    }

    // check if the camera permission is given
    // request one if it was not
    private void checkCameraPermissionAndRequest() {
        if (ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{CAMERA_PERMISSION}, ZXING_CAMERA_PERMISSION);
        }
    }

    // this method is called when system gives (or not gives) the permission to use camera
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, getString(R.string.camera_perm_ok));
                } else {
                    Toast.makeText(this, R.string.please_allow_camera, Toast.LENGTH_SHORT).show();
                }
        }
    }

    // when the activity is started again (ex. when application is switched into)
    // start the camera again
    @Override
    public void onResume() {
        super.onResume();

        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);

        // Start camera on resume
        mScannerView.startCamera();
    }

    // when the app is in the background, switch the camera off
    @Override
    public void onPause() {
        super.onPause();

        // Stop camera on pause
        mScannerView.stopCamera();
    }

    // process the scanned barcode
    @Override
    public void handleResult(Result rawResult) {
        // Prints scan results
        Log.v(TAG, rawResult.getText());

        // Prints the scan format (qr code, pdf417 etc.)
        Log.v(TAG, rawResult.getBarcodeFormat().toString());

        // storing the barcode
        String barcode = rawResult.getText();

        // do processing
        processBarcode(barcode);
    }

    // go to the main activity
    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // process the barcode given as a string
    public void processBarcode(String barcode) {
        // go back to main activity if the barcode was invalid
        // or the scan was interrupted
        if (barcode == null || barcode.equals("")) {
            Log.d("STATE", "Barcode is invalid, going back to main");
            goToMain();
        } else if(action == ACTION_TYPE.ACTION_OPENFOOD) {
            Log.d("STATE", "Barcode " + barcode + " found, going to OpenFood");
            Intent intent = new Intent(this, BarcodeToProductActivity.class);
            intent.putExtra(EXTRA_BARCODE, barcode);
            startActivity(intent);
        } else if(action == ACTION_TYPE.ACTION_SHOPPING_CART) {
            Log.d("STATE", "Barcode " + barcode + " found, sending data to shopping list");
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            intent.putExtra(EXTRA_BARCODE, barcode);
            startActivity(intent);
        }
        // no other actions now
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

    public void showSavedProducts(MenuItem item) {
        Intent intent = new Intent(this, SavedProductsDatesActivity.class);
        startActivity(intent);
    }

    public void showStatistics(MenuItem item) {
        Intent intent = new Intent(this, ShoppingCartStatistics.class);
        startActivity(intent);
    }

    public void showChat(MenuItem item) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
