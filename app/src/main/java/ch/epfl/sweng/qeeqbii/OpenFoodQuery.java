package ch.epfl.sweng.qeeqbii;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Created by guillaume on 06/10/17.
 */

public class OpenFoodQuery extends AsyncTask<String, Void, String> {

    @Override
    public String doInBackground(String params[])
    {
        try {
            URL url = new URL("https://www.openfood.ch/api/v3/products?excludes=name_translations&barcodes=" + params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type","application/vnd.api+json");
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("Authorization","Token token=\"da68d406381c30aae6796d39d474265a\"");
            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            String str = "";
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                str += current;
            }
            urlConnection.disconnect();

            return str;
        } catch(Exception e) {
            return e.toString();
        }
    }
}
