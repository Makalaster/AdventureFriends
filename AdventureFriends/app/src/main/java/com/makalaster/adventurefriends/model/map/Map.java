package com.makalaster.adventurefriends.model.map;

import android.util.Log;

import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;

/**
 * Created by Makalaster on 5/23/17.
 */

public class Map {
    private static final String TAG = "Map";
    public static final int TILE_WIDTH = 8, TILE_HEIGHT = 10;

    private Tile[][] mTiles;

    public Map() {
        mTiles = new Tile[TILE_WIDTH][TILE_HEIGHT];
        fillTiles();
    }

    public void fillTiles() {
        for (int i = 0; i < TILE_WIDTH; i++) {
            for (int j = 0; j < TILE_HEIGHT; j++) {
                mTiles[i][j] = new Tile(i, j);
            }
        }
    }

    public Tile[][] getTiles() {
        return mTiles;
    }

    public void setTiles(Tile[][] tiles) {
        mTiles = tiles;
    }

    public void addNonPlayer(NonPlayerCharacter nonPlayerCharacter, int x, int y) {
        Tile currentTile = getTile(x, y);

        currentTile.setContainsNonPlayer(true);
        currentTile.setNonPlayer(nonPlayerCharacter);
    }

    public void removeNonPlayer(int x, int y) {
        Tile currentTile = getTile(x, y);

        if (currentTile.containsNonPlayer()) {
            currentTile.setContainsNonPlayer(false);
            currentTile.setNonPlayer(null);
        }
    }

    public void addPlayer(PlayerCharacter playerCharacter, int x, int y) {
        Tile currentTile = getTile(x, y);

        currentTile.setContainsPlayer(true);
        currentTile.setPlayer(playerCharacter);
    }

    public void removePlayer(int x, int y) {
        Tile currentTile = getTile(x, y);

        if (currentTile.containsPlayer()) {
            currentTile.setContainsPlayer(false);
            currentTile.setPlayer(null);
        }
    }

    public Tile getTile(int x, int y){
        return mTiles[x][y];
    }
}
