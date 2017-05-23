package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Weapon extends Item {
    private int mDamage, mRange;
    private boolean mIsEquipped;

    public Weapon() {

    }

    public Weapon(long id, String name, String description, String type, int tier, int value, int damage, int range, String effect) {
        super(id, name, description, type, tier, value, effect);

        mDamage = damage;
        mRange = range;
    }

    public int getDamage() {
        return mDamage;
    }

    public int getRange() {
        return mRange;
    }

    public boolean isEquipped() {
        return mIsEquipped;
    }

    public void setEquipped(boolean equipped) {
        mIsEquipped = equipped;
    }

    public void setDamage(int damage) {
        mDamage = damage;
    }

    public void setRange(int range) {
        mRange = range;
    }
}
