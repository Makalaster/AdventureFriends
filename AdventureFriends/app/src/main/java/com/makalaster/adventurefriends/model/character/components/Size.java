package com.makalaster.adventurefriends.model.character.components;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Size {
    private long mId;
    private String mName, mDescription, mBonus;

    public Size() {}

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

    public int getBodyBonus() {
        int body = 0;
        String[] bonus = mBonus.split(" ");
        if (bonus[1].equals("body")) {
            body = Integer.parseInt(bonus[0]);
        }
        return body;
    }

    public int getMindBonus() {
        int mind = 0;
        String[] bonus = mBonus.split(" ");
        if (bonus[1].equals("mind")) {
            mind = Integer.parseInt(bonus[0]);
        }
        return mind;
    }

    public int getEssenceBonus() {
        int essence = 0;
        String[] bonus = mBonus.split(" ");
        if (bonus[1].equals("essence")) {
            essence = Integer.parseInt(bonus[0]);
        }
        return essence;
    }
}
