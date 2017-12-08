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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.Slider;
import ch.epfl.sweng.qeeqbii.cancer.CancerDataBase;
import ch.epfl.sweng.qeeqbii.clustering.ClusterClassifier;
import ch.epfl.sweng.qeeqbii.clustering.NutrientNameConverter;
import ch.epfl.sweng.qeeqbii.custom_exceptions.BadlyFormatedFile;
import ch.epfl.sweng.qeeqbii.custom_exceptions.NotOpenFileException;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
import ch.epfl.sweng.qeeqbii.chat.StartActivity;
import ch.epfl.sweng.qeeqbii.open_food.SavedProductsDatabase;
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
    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;

    private static boolean active = false;

    // on activity creation
    // ask permissions and launch barcode reader
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make the user stayed login between using
        mAuth = FirebaseAuth.getInstance();
       // if (mAuth.getCurrentUser() != null) {
       //     mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
       // }
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            Intent startIntent = new Intent(BarcodeScannerActivity.this, StartActivity.class);
            startActivity(startIntent);
            finish();
        }


        // disable autorotate if it was enabled
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        setContentView(R.layout.activity_barcode_scanner);

        try {
            SavedProductsDatabase.load(getApplicationContext());
            SavedProductsDatabase.getDates();
        } catch(IOException|JSONException|ParseException e){
            System.err.println(e.getMessage());
        }

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


        // Reading files that are needed in the rest of the app
        this.readCSVFiles();



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
    public void goToMain() {
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
            Intent intent = new Intent(this, ShoppingListActivity.class);
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



    // slider actions below
    public void readCSVFiles() {
        if (!NutrientNameConverter.isRead()) {
            NutrientNameConverter.readCSVFile(getApplicationContext());
        }


        if (!ClusterClassifier.isRead()) {
            try {
                ClusterClassifier.readClusterNutrientCentersFile(getApplicationContext());
            }
            catch (NotOpenFileException|BadlyFormatedFile e) {
                System.err.println(e.getMessage());
            }
        }

        if (!CancerDataBase.isRead()) {
            CancerDataBase.readCSVFile(getApplicationContext());
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }


    public static boolean isRunning() {
        return active;
    }




    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void sliderGoToActivity(MenuItem item) {
        Slider slider = new Slider();
        slider.goToActivity(item, this);
    }

}
