package com.makalaster.adventurefriends.model.character.components;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Size {
    private long mId;
    private String mName, mDescription, mBonus;

    public Size(long id, String name, String description, String bonus) {
        mId = id;
        mName = name;
        mDescription = description;
        mBonus = bonus;
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

    public String getBonus() {
        return mBonus;
    }
}
