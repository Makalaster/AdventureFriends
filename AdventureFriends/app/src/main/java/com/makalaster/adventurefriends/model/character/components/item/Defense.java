package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Defense extends Item {
    private int mDefense;

    public Defense(long id, String name, String description, String type, int tier, int value, int defense) {
        super(id, name, description, type, tier, value);

        mDefense = defense;
    }

    public int getDefense() {
        return mDefense;
    }
}
