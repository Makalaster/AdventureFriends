package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Defense extends Item {
    private int mDefense;
    private boolean mIsEquipped;

    public Defense() {

    }

    public Defense(long id, String name, String description, String type, int tier, int value, int defense) {
        super(id, name, description, type, tier, value);

        mDefense = defense;
    }

    public int getDefense() {
        return mDefense;
    }

    public boolean isEquipped() {
        return mIsEquipped;
    }

    public void setEquipped(boolean equipped) {
        mIsEquipped = equipped;
    }

    public void setDefense(int defense) {
        mDefense = defense;
    }
}
