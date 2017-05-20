package com.makalaster.adventurefriends.model.character.components;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Ability {
    private long mId;
    private String mName, mQuote, mDescription, mEffects;
    private int mLevel, mDamage, mRange, mJobId;

    public Ability(long id, String name, String quote, String description, String effects, int level, int damage, int range, int jobId) {
        mId = id;
        mName = name;
        mQuote = quote;
        mDescription = description;
        mEffects = effects;
        mLevel = level;
        mDamage = damage;
        mRange = range;
        mJobId = jobId;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getQuote() {
        return mQuote;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEffects() {
        return mEffects;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getDamage() {
        return mDamage;
    }

    public int getRange() {
        return mRange;
    }

    public int getJobId() {
        return mJobId;
    }
}
