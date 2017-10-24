package ch.epfl.sweng.qeeqbii;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Created by guillaume on 06/10/17.
 */

public class OpenFoodQuery extends AsyncTask<String, Void, String> {

    public class OpenFoodQueryException extends Exception {
        public OpenFoodQueryException(String message) {
            super(message);
        }
    }

    @Override
    public String doInBackground(String params[])
    {
        try {
            URL url = new URL("https://www.openfood.ch/api/v3/products?excludes=name_translations2Cimages&barcodes=" + params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/vnd.api+json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Authorization", "Token token=\"da68d406381c30aae6796d39d474265a\"");
            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            String str = "";
            int data = isw.read();
            for(int i = 0; i<100; ++i) {
                char current = (char) data;
                data = isw.read();
                str += current;
            }
            int barcode_begin = str.indexOf("barcode")+10;
            if (barcode_begin==9)
            {
                throw new OpenFoodQueryException("Unusable data registered for this product.");
            }
            int barcode_end = str.indexOf('\"',barcode_begin);
            String barcode = str.substring(barcode_begin,barcode_end);
            if(!(barcode.equals(params[0])))
            {
                throw new OpenFoodQueryException("Barcode not found in the database.");
            }
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                str += current;
            }
            urlConnection.disconnect();

            return str;
        }
        catch(OpenFoodQueryException e)
        {
            return "ERROR: (openfood) " + e.getMessage();
        }
        catch(java.io.IOException e)
        {
            return "ERROR: " + e.getMessage();
        }
    }
}
