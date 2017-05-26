package com.makalaster.adventurefriends;

import com.makalaster.adventurefriends.model.campaign.Module;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Module class
 */

public class ModuleTest {
    private Module mModule;

    @Before
    public void setup() {
        mModule = new Module("abc123", "Test Module", "A module for testing", 1);
    }

    @Test
    public void testGetTypeAsString() {
        assertEquals("Battle", mModule.getTypeAsString());
        mModule.setType(2);
        assertEquals("Story", mModule.getTypeAsString());
        mModule.setType(3);
        assertEquals("Non-battle encounter", mModule.getTypeAsString());
        mModule.setType(0);
        assertEquals("Other", mModule.getTypeAsString());
    }
}
