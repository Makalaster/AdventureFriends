package com.makalaster.adventurefriends;

import com.makalaster.adventurefriends.model.character.components.Job;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test of the Job class.
 */

public class JobTest {
    private Job testJob;

    @Before
    public void setup() {
        testJob = new Job(1, "Tank", "The meaty one", "2 body");
    }

    @Test
    public void testGetBodyBonus() {
        assertEquals(2, testJob.getBodyBonus());
    }

    @Test
    public void testGetMindBonus() {
        assertEquals(0, testJob.getMindBonus());
    }

    @Test
    public void testGetEssenceBonus() {
        assertEquals(0, testJob.getEssenceBonus());
    }
}
