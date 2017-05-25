package com.makalaster.adventurefriends.model.map;

/**
 * An interface to handle what happens when a DM or player clicks on a tile.
 */

public interface OnTileClickedListener {
    void onDmTileClicked(Tile tile);
    void onPlayerTileClicked(Tile tile);
}
