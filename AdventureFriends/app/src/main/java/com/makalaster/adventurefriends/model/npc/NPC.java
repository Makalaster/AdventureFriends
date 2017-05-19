package com.makalaster.adventurefriends.model.npc;

/**
 * Created by Makalaster on 5/18/17.
 */

public class NPC {
    private String mName;

    public NPC() {
    }

    public NPC(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
