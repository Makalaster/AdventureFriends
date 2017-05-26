package com.makalaster.adventurefriends;

import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.character.components.item.Defense;
import com.makalaster.adventurefriends.model.character.components.item.Item;
import com.makalaster.adventurefriends.model.character.components.item.Weapon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the NonPlayerCharacter class.
 */

public class TestNonPlayerCharacter {
    private NonPlayerCharacter testNonPlayerCharacter;

    @Before
    public void setup() {
        Job job = new Job(1, "Cannon", "Boom", "3 essence");
        Size size = new Size(2, "Regular", "Look at me, I'm regular sized!", "2 mind");

        testNonPlayerCharacter = new NonPlayerCharacter("Bill", "aeiou", 2, size, job, 10);
    }

    @Test
    public void testEquipWeapon() {
        Weapon weapon = new Weapon(1, "Weapon", "Description", "Blade", 3, 1000, 20, 30, "Wrecks you");
        testNonPlayerCharacter.equip(weapon);
        assertEquals(weapon, testNonPlayerCharacter.getEquipment().get("weapon"));
    }

    @Test
    public void testRemoveWeapon() {
        Weapon weapon = new Weapon(1, "Weapon", "Description", "Blade", 3, 1000, 20, 30, "Wrecks you");
        testNonPlayerCharacter.equip(weapon);
        assertEquals(weapon, testNonPlayerCharacter.getEquipment().get("weapon"));
        testNonPlayerCharacter.remove(weapon);
        assertEquals(null, testNonPlayerCharacter.getEquipment().get("weapon"));
    }

    @Test
    public void testEquipDefense() {
        Defense defense = new Defense(1, "Great hat", "A really great hat", "defense, hat", 3, 40, 2, "Makes you look great");
        testNonPlayerCharacter.equip("hat", defense);
        assertEquals(defense, testNonPlayerCharacter.getEquipment().get("hat"));
    }

    @Test
    public void testRemoveDefense() {
        Defense defense = new Defense(1, "Great hat", "A really great hat", "defense, hat", 3, 40, 2, "Makes you look great");
        testNonPlayerCharacter.equip("hat", defense);
        assertEquals(defense, testNonPlayerCharacter.getEquipment().get("hat"));
        testNonPlayerCharacter.remove("hat", defense);
        assertEquals(null, testNonPlayerCharacter.getEquipment().get("hat"));
    }
}
