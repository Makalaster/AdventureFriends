package com.makalaster.adventurefriends.model.character;

import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.character.components.item.Item;

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
