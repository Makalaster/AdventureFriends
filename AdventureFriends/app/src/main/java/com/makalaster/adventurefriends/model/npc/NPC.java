package com.makalaster.adventurefriends.model.npc;

/**
 * Created by Makalaster on 5/18/17.
 */

public class NPC {
    private String mName, mId;

    public NPC() {
    }

    public NPC(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
