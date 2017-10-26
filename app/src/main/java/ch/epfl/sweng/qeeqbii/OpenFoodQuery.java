package ch.epfl.sweng.qeeqbii;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import java.net.URL;

/**
 * Created by guillaume on 06/10/17.
 * 
 */

class OpenFoodQuery extends AsyncTask<String, Void, String> {


    private static Map<String,HTTPRequestResponse> resp_cache = new HashMap<>();

    private static Map<String,String> error_cache = new HashMap<>();

    private static class OpenFoodQueryException extends Exception {
        OpenFoodQueryException(String message) {
            super(message);
        }
    }

    @Override
    protected String doInBackground(String params[])
    {
        String barcode = params[0];
        try {
            URL url = new URL("https://www.openfood.ch/api/v3/products?excludes=name_translations2Cimages&barcodes=" + barcode);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/vnd.api+json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Authorization", "Token token=\"da68d406381c30aae6796d39d474265a\"");
            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            String str = "";
            int data = isw.read();
            for (int i = 0; i < 100; ++i) {
                char current = (char) data;
                data = isw.read();
                str += current;
            }
            int barcode_begin = str.indexOf("barcode") + 10;
            if (barcode_begin == 9) {
                throw new OpenFoodQueryException("Unusable data registered for this product.");
            }
            int barcode_end = str.indexOf('\"',barcode_begin);
            String barcode_found = str.substring(barcode_begin,barcode_end);
            if(!(barcode_found.equals(barcode)))
            {
                throw new OpenFoodQueryException("Barcode not found in the database.");
            }
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                str += current;
            }
            urlConnection.disconnect();

            resp_cache.put(barcode, new HTTPRequestResponse(str));
            return str;
        }
        catch(OpenFoodQueryException e)
        {
            error_cache.put(barcode, "ERROR: (openfood) " + e.getMessage());
            return "ERROR: (openfood) " + e.getMessage();
        }
        catch(java.io.IOException e)
        {
            error_cache.put(barcode, "ERROR: " + e.getMessage());
            return "ERROR: " + e.getMessage();
        }


    }

    // This GetOrCreate can freeze the main thread if the barcode isn't in the cache.
    static HTTPRequestResponse GetOrCreateHTTPRequestResponse(String barcode) throws Exception
    {
        if(resp_cache.containsKey(barcode))
        {
            return resp_cache.get(barcode);
        }

        final CountDownLatch get_or_create_signal = new CountDownLatch(1);

        new OpenFoodQuery() {
            @Override
            protected void onPostExecute(String barcode) {


            }
        }.execute(barcode);

        get_or_create_signal.countDown();

        if(resp_cache.containsKey(barcode))
        {
            return resp_cache.get(barcode);
        } else {
            throw new Exception(error_cache.get(barcode));
        }
    }

    static boolean isCached(String barcode)
    {
        return (resp_cache.containsKey(barcode));
    }

    static HTTPRequestResponse get(String barcode) throws OpenFoodQueryException
    {
        if (resp_cache.containsKey(barcode))
        {
            return resp_cache.get(barcode);
        } else {
            throw new OpenFoodQueryException("ERROR: (OpenFoodQuery) : this barcode \"" + barcode + "\" is not cached.");
        }
    }
}
