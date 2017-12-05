package ch.epfl.sweng.qeeqbii;

import android.content.Context;

import org.junit.Test;

import static ch.epfl.sweng.qeeqbii.chat.GetTimeAgo.getTimeAgo;
import static com.facebook.FacebookSdk.getApplicationContext;
import static junit.framework.Assert.assertEquals;

/**
 * Created by nicol on 26.11.2017.
 */

public class GetTimeAgoTest {
    @Test
    public void test_time() {
        long time = 100;
        Context cxt= getApplicationContext();

        String test = getTimeAgo(time,cxt);
        long now = System.currentTimeMillis();
        assertEquals(test,"17504 days ago");
    }

}
