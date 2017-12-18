package ch.epfl.sweng.qeeqbii;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataQueryActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataShowActivity;
import ch.epfl.sweng.qeeqbii.activities.GraphsActivity;
import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.activities.ProductComparisonActivity;
import ch.epfl.sweng.qeeqbii.activities.RecentlyScannedProductsActivity;
import ch.epfl.sweng.qeeqbii.activities.SavedProductsDatesActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;
import ch.epfl.sweng.qeeqbii.activities.StatisticsActivity;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;

/**
 * Created by adrien on 07.12.17.
 */

public class Slider  {


    public void goToActivity(MenuItem item, Context context) {
        if (item.getItemId() == R.id.nav_main) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_chat) {
            Intent intent = new Intent(context, MainActivityChat.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_graphs) {
            Intent intent = new Intent(context, GraphsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_cancerdb) {
            Intent intent = new Intent(context, CancerDataShowActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_scan) {
            Intent intent = new Intent(context, BarcodeScannerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_shopping_cart) {
            Intent intent = new Intent(context, ShoppingListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_dataquery) {
            Intent intent = new Intent(context, CancerDataQueryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_scannedProducts) {
            Intent intent = new Intent(context, RecentlyScannedProductsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_scanning_history) {
            Intent intent = new Intent(context, SavedProductsDatesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_stats) {
            Intent intent = new Intent(context, StatisticsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_comparison) {
            Intent intent = new Intent(context, ProductComparisonActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            context.startActivity(intent);
        }
    }
}
