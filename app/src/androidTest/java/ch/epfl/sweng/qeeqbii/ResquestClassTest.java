package ch.epfl.sweng.qeeqbii;

import org.junit.AfterClass;
import org.junit.Test;

import ch.epfl.sweng.qeeqbii.chat.Chats;
import ch.epfl.sweng.qeeqbii.chat.Request;

import static junit.framework.Assert.assertEquals;

/**
 * Created by nicol on 26.11.2017.
 */

public class ResquestClassTest {
    @Test
    public void testGetSet () {
        String date = "november";
        Request request = new Request (date);
        assertEquals(date,request.getDate());
        request.setDate("december");
        assertEquals("december",request.getDate());
    }

    @AfterClass
    public static void finish_all_activities() {
        ActivityFinisher.finishOpenActivities();
    }
}
