package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Represents a defensive item. Can be used by a player or NPC.
 */

public class Defense extends Item {
    private int mDefense;
    private boolean mIsEquipped;

    public Defense() {

    }

    public Defense(long id, String name, String description, String type, int tier, int value, int defense, String effect) {
        super(id, name, description, type, tier, value, effect);

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
