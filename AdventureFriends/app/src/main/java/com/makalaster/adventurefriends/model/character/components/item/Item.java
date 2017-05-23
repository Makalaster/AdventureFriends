package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Item {
    private long mId;
    private String mName, mDescription, mType, mEffect;
    private int mTier, mValue;

    public Item() {
    }

    public Item(long id, String name, String description, String type, int tier, int value, String effect) {
        mId = id;
        mName = name;
        mDescription = description;
        mType = type;
        mTier = tier;
        mValue = value;
        mEffect = effect;
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

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setTier(int tier) {
        mTier = tier;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public String getEffect() {
        return mEffect;
    }

    public void setEffect(String effect) {
        mEffect = effect;
    }
}
