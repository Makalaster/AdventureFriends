package com.makalaster.adventurefriends.model.character;

import com.makalaster.adventurefriends.model.character.components.Ability;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.character.components.item.Defense;
import com.makalaster.adventurefriends.model.character.components.item.Edible;
import com.makalaster.adventurefriends.model.character.components.item.Item;
import com.makalaster.adventurefriends.model.character.components.item.Weapon;

import java.util.Map;

/**
 * Created by Makalaster on 5/17/17.
 */

public class PlayerCharacter extends NonPlayerCharacter {
    private static final int STARTING_MONEY = 50;
    private static final int STARTING_LEVEL = 1;

    private int mCurrentXP, mXPtoNext;
    private String mOwnerId;

    public PlayerCharacter() {
    }

    public PlayerCharacter(String name, String id, Size size, Job job, String ownerId) {
        super(name, id, STARTING_LEVEL, size, job, STARTING_MONEY);

        mOwnerId = ownerId;
        mCurrentXP = 0;
        mXPtoNext = 100;

        addAbility("scratch", new Ability(1, "Scratch", "That's what claws are for!", "Take a swipe at your target.", null, 0, 3, 1, 0, "body"));

        switch (job.getName()) {
            case "Tank":
                addItemToInventory("sword", new Weapon(10, "Grass Sword", "Flimsy sword made of dried grass", "weapon, sword", 1, 1, 2, 1));
                equip((Weapon) getInventory().get("sword"));
                addItemToInventory("bacon", new Edible(21, "Bacon", "A delicious piece of cured fried piggy meat. Temporarily increases the consumer's body", "edible", 1, 3, "body 1"));
                addAbility("punch", new Ability(5, "Punch", "KNUCKLE SAMMICH", "Hold out your hand. Now curl your fingers in towards your palm, keeping your thumb on the outside. This is a fist. Now use it to bludgeon your target!", null, 0, 2, 1, 1, "body"));
                break;
            case "Doctor":
                addItemToInventory("bow", new Weapon(13, "Grass Bow", "Flimsy bow made of grass", "weapon, bow", 1, 1, 1, 4));
                equip((Weapon) getInventory().get("bow"));
                addItemToInventory("fungus", new Edible(20, "Fungus", "A gross piece of woodland fungus. Temporarily increases the consumer's mind", "edible", 1, 3, "mind 1"));
                addAbility("diagnose", new Ability(9, "Diagnose", "Yes, I see...", "Reveal a single opponent's stats.", "Reveal stats", 0, 0, 5, 2, "mind"));
                break;
            case "Cannon":
                addItemToInventory("wand", new Weapon(16, "Grass Wand", "A flimsy wand made of grass", "weapon, wand", 1, 1, 2, 4));
                equip((Weapon) getInventory().get("wand"));
                addItemToInventory("potion", new Edible(19, "Potion", "A viscous liquid that temporarily increases the consumer's essence", "edible", 1, 3, "essence 1"));
                addAbility("chill", new Ability(13, "Chill", "Yo, chill.", "Freeze a target", "Slow", 0, 2, 4, 3, "essence"));
                break;
        }
        addItemToInventory("boots", new Defense(1, "Grass boots", "Light boots made of grass", "defense, boots", 1, 2, 2));
        equip((Defense) getInventory().get("boots"));
        addItemToInventory("shirt", new Defense(7, "Grass shirt", "Light shirt made of grass", "defense, shirt", 1, 3, 3));
        equip((Defense) getInventory().get("shirt"));
        addItemToInventory("hat", new Defense(4, "Grass hat", "Light hat made of grass", "defense, hat", 1, 1, 1));
        equip((Defense) getInventory().get("hat"));
        addItemToInventory("adhesive_bandage", new Edible(22, "Adhesive Bandage", "A non-branded strip of bandage that sticks to the consumer and provides a small amount of health.", "edible", 1, 1, "PG 1"));
    }

    public int getCurrentXP() {
        return mCurrentXP;
    }

    public void setCurrentXP(int currentXP) {
        mCurrentXP = currentXP;
    }

    public int getXPtoNext() {
        return mXPtoNext;
    }

    public void setXPtoNext(int XPtoNext) {
        mXPtoNext = XPtoNext;
    }

    public static int getStartingMoney() {
        return STARTING_MONEY;
    }

    public static int getStartingLevel() {
        return STARTING_LEVEL;
    }

    public String getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(String ownerId) {
        mOwnerId = ownerId;
    }

    public void loot(NonPlayerCharacter nonPlayerCharacter) {
        int money = nonPlayerCharacter.getMoney();
        Map<String, Item> items = nonPlayerCharacter.getInventory();
    }

    public void levelUp() {
        int level = getLevel();
        setLevel(level + 1);
    }
}
