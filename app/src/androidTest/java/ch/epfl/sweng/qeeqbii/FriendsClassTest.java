package ch.epfl.sweng.qeeqbii;

import org.junit.Test;

import ch.epfl.sweng.qeeqbii.chat.Chats;
import ch.epfl.sweng.qeeqbii.chat.Friends;

import static junit.framework.Assert.assertEquals;

/**
 * Created by nicol on 26.11.2017.
 */

public class FriendsClassTest {
    @Test
    public void testGetSet () {
        String date = "november";
        Friends friends = new Friends(date);
        assertEquals(date,friends.getDate());
        friends.setDate("december");
        assertEquals("december",friends.getDate());
    }
}
