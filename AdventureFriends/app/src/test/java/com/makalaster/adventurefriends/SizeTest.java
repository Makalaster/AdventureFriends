package com.makalaster.adventurefriends;

import com.makalaster.adventurefriends.model.character.components.Size;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test of the Size class.
 */

public class SizeTest {
    private Size testSize;

    @Before
    public void setup() {
        testSize = new Size(1, "Doctor", "The medical one", "2 mind");
    }

    @Test
    public void testGetBodyBonus() {
        assertEquals(0, testSize.getBodyBonus());
    }

    @Test
    public void testGetMindBonus() {
        assertEquals(2, testSize.getMindBonus());
    }

    @Test
    public void testGetEssenceBonus() {
        assertEquals(0, testSize.getEssenceBonus());
    }
}
