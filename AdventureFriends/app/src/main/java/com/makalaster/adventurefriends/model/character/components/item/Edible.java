package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Represents an edible item. Can be used by a player or NPC.
 */

public class Edible extends Item {
    private String mEffect;

    public Edible() {

    }

    public Edible(long id, String name, String description, String type, int tier, int value, String effect) {
        super(id, name, description, type, tier, value, effect);

        mEffect = effect;
    }

    @Override
    public String getEffect() {
        return mEffect;
    }

    @Override
    public void setEffect(String effect) {
        mEffect = effect;
    }
}
