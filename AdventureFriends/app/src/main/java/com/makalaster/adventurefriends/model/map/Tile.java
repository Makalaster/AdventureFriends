package com.makalaster.adventurefriends.model.map;

import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;

/**
 * Created by Makalaster on 5/23/17.
 */

public class Tile {
    private int mX, mY;
    private boolean mContainsPlayer, mContainsNonPlayer;
    private PlayerCharacter mPlayer;
    private NonPlayerCharacter mNonPlayer;

    public Tile() {

    }

    public Tile(int x, int y) {
        mX = x;
        mY = y;
        mContainsPlayer = false;
        mContainsNonPlayer = false;
        mPlayer = null;
        mNonPlayer = null;
    }

    public boolean containsPlayer() {
        return mContainsPlayer;
    }

    public void setContainsPlayer(boolean containsPlayer) {
        mContainsPlayer = containsPlayer;
    }

    public boolean containsNonPlayer() {
        return mContainsNonPlayer;
    }

    public void setContainsNonPlayer(boolean containsNonPlayer) {
        mContainsNonPlayer = containsNonPlayer;
    }

    public PlayerCharacter getPlayer() {
        return mPlayer;
    }

    public void setPlayer(PlayerCharacter player) {
        mPlayer = player;
    }

    public NonPlayerCharacter getNonPlayer() {
        return mNonPlayer;
    }

    public void setNonPlayer(NonPlayerCharacter nonPlayer) {
        mNonPlayer = nonPlayer;
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        mY = y;
    }

    public boolean isContainsPlayer() {
        return mContainsPlayer;
    }

    public boolean isContainsNonPlayer() {
        return mContainsNonPlayer;
    }
}
