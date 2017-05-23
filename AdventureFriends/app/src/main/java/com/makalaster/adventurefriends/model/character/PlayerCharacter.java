package com.makalaster.adventurefriends.model.character;

import android.content.Context;

import com.makalaster.adventurefriends.baseGames.GoblinsGoblins;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.character.components.item.Defense;
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

    public PlayerCharacter(String name, String id, Size size, Job job, String ownerId, Context context) {
        super(name, id, STARTING_LEVEL, size, job, STARTING_MONEY);

        mOwnerId = ownerId;
        mCurrentXP = 0;
        mXPtoNext = 100;

        GoblinsGoblins goblinsGoblins = GoblinsGoblins.getInstance(context.getApplicationContext());

        addAbility("scratch", goblinsGoblins.getAbilityByName("Scratch"));

        switch (job.getName()) {
            case "Tank":
                addItemToInventory(NonPlayerCharacter.WEAPON, goblinsGoblins.getWeaponByName("Grass Sword"));
                equip((Weapon) getInventory().get(NonPlayerCharacter.WEAPON));
                addItemToInventory("bacon", goblinsGoblins.getEdibleByName("Bacon"));
                addAbility("punch", goblinsGoblins.getAbilityByName("Punch"));
                break;
            case "Doctor":
                addItemToInventory("bow", goblinsGoblins.getWeaponByName("Grass Bow"));
                equip((Weapon) getInventory().get("bow"));
                addItemToInventory("fungus", goblinsGoblins.getEdibleByName("Fungus"));
                addAbility("diagnose", goblinsGoblins.getAbilityByName("Diagnose"));
                break;
            case "Cannon":
                addItemToInventory("wand", goblinsGoblins.getWeaponByName("Grass Wand"));
                equip((Weapon) getInventory().get("wand"));
                addItemToInventory("potion", goblinsGoblins.getEdibleByName("Potion"));
                addAbility("chill", goblinsGoblins.getAbilityByName("Chill"));
                break;
        }
        addItemToInventory("boots", goblinsGoblins.getDefenseByName("Grass Boots"));
        equip(NonPlayerCharacter.BOOTS, (Defense) getInventory().get("boots"));
        addItemToInventory("shirt", goblinsGoblins.getDefenseByName("Grass Shirt"));
        equip(NonPlayerCharacter.SHIRT, (Defense) getInventory().get("shirt"));
        addItemToInventory("hat", goblinsGoblins.getDefenseByName("Grass Hat"));
        equip(NonPlayerCharacter.HAT, (Defense) getInventory().get("hat"));
        addItemToInventory("adhesive_bandage", goblinsGoblins.getEdibleByName("Adhesive Bandage"));
    }

    public int getCurrentXP() {
        return mCurrentXP;
    }

    public void setCurrentXP(int currentXP) {
        mCurrentXP = currentXP;
    }

    public void increaseXP(int increaseBy) {
        mCurrentXP += increaseBy;
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

    public void addMoney(int money) {
        int currentMoney = getMoney();
        setMoney(currentMoney + money);
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
