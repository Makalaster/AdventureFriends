package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Item {
    private long mId;
    private String mName, mDescription, mType;
    private int mTier, mValue;

    public Item() {
    }

    public Item(long id, String name, String description, String type, int tier, int value) {
        mId = id;
        mName = name;
        mDescription = description;
        mType = type;
        mTier = tier;
        mValue = value;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getType() {
        return mType;
    }

    public int getTier() {
        return mTier;
    }

    public int getValue() {
        return mValue;
    }
}
