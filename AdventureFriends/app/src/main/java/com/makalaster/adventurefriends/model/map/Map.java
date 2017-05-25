package com.makalaster.adventurefriends.model.map;

import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;

import java.util.ArrayList;

/**
 * Represents a map object. Currently maps are 8 tiles by 10 tiles.
 */

public class Map {
    private static final String TAG = "Map";
    public static final int TILE_WIDTH = 8, TILE_HEIGHT = 10;
    private boolean isLaunched;

    private ArrayList<ArrayList<Tile>> mTiles;

    public Map() {
        mTiles = new ArrayList<>(TILE_WIDTH);
        fillTiles();
    }

    /**
     * Fill the tiles of a new map, to make sure none of the spaces are null.
     */
    private void fillTiles() {
        for (int i = 0; i < TILE_WIDTH; i++) {
            mTiles.add(i, new ArrayList<Tile>(TILE_HEIGHT));
            for (int j = 0; j < TILE_HEIGHT; j++) {
                mTiles.get(i).add(j, new Tile(i, j));
            }
        }
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return mTiles;
    }

    public void setTiles(ArrayList<ArrayList<Tile>> tiles) {
        mTiles = tiles;
    }

    /**
     * Reset all the tiles of the map.
     */
    public void clearTiles() {
        mTiles = null;
        mTiles = new ArrayList<>(TILE_WIDTH);
        fillTiles();
    }

    /**
     * Add a non-player character to a tile on the map.
     * @param nonPlayerCharacter The non player character to be added.
     * @param x The X coordinate of the non-player character's location.
     * @param y The Y coordinate of the non-player character's location.
     */
    public void addNonPlayer(NonPlayerCharacter nonPlayerCharacter, int x, int y) {
        Tile currentTile = getTile(x, y);

        currentTile.setContainsNonPlayer(true);
        currentTile.setNonPlayer(nonPlayerCharacter);
    }

    /**
     * Remove a non-player character from a tile on the map.
     * @param x The X coordinate of the non-player character's location.
     * @param y The Y coordinate of the non-player character's location.
     */
    public void removeNonPlayer(int x, int y) {
        Tile currentTile = getTile(x, y);

        if (currentTile.containsNonPlayer()) {
            currentTile.setContainsNonPlayer(false);
            currentTile.setNonPlayer(null);
        }
    }

    /**
     * Add a player character to a tile on the map.
     * @param playerCharacter The player character to be added.
     * @param x The X coordinate of the player character's location.
     * @param y The Y coordinate of the player character's location.
     */
    public void addPlayer(PlayerCharacter playerCharacter, int x, int y) {
        Tile currentTile = getTile(x, y);

        currentTile.setContainsPlayer(true);
        currentTile.setPlayer(playerCharacter);
    }

    /**
     * Remove a player character from a tile on the map.
     * @param x The X coordinate of the player character's location.
     * @param y The Y coordinate of the player character's location.
     */
    public void removePlayer(int x, int y) {
        Tile currentTile = getTile(x, y);

        if (currentTile.containsPlayer()) {
            currentTile.setContainsPlayer(false);
            currentTile.setPlayer(null);
        }
    }

    public Tile getTile(int x, int y){
        return mTiles.get(x).get(y);
    }

    public void setTile(int x, int y, Tile tile) {
        mTiles.get(x).set(y, tile);
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    public void setLaunched(boolean launched) {
        isLaunched = launched;
    }
}
