package ch.epfl.sweng.qeeqbii;

import org.junit.Test;

import ch.epfl.sweng.qeeqbii.chat.Chats;

import static junit.framework.Assert.assertEquals;

/**
 * Created by nicol on 26.11.2017.
 */

public class ChatClassTest {
    @Test
    public void testGetSet () {
        String date = "november";
        Chats chat = new Chats (date);
        assertEquals(date,chat.getDate());
        chat.setDate("december");
        assertEquals("december",chat.getDate());
    }
}

