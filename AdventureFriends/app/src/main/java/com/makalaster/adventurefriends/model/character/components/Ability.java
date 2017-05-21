package com.makalaster.adventurefriends.model.character.components;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Ability {
    private long mId;
    private String mName, mQuote, mDescription, mEffects, mCheck;
    private int mLevel, mDamage, mRange, mJobId;

    public Ability(long id, String name, String quote, String description, String effects, int level, int damage, int range, int jobId, String check) {
        mId = id;
        mName = name;
        mQuote = quote;
        mDescription = description;
        mEffects = effects;
        mLevel = level;
        mDamage = damage;
        mRange = range;
        mJobId = jobId;
        mCheck = check;
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

    public String getCheck() {
        return mCheck;
    }

    public void setCheck(String check) {
        mCheck = check;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setQuote(String quote) {
        mQuote = quote;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setEffects(String effects) {
        mEffects = effects;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public void setDamage(int damage) {
        mDamage = damage;
    }

    public void setRange(int range) {
        mRange = range;
    }

    public void setJobId(int jobId) {
        mJobId = jobId;
    }
}
