package ch.epfl.sweng.qeeqbii;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nicol on 26.11.2017.
 */

public class ChatTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ch.epfl.sweng.qeeqbii", appContext.getPackageName());
    }



}
