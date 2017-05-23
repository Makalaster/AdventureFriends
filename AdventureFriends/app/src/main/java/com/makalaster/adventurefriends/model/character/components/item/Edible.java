package com.makalaster.adventurefriends.model.character.components.item;

/**
 * Created by Makalaster on 5/20/17.
 */

public class Edible extends Item {
    private String mEffect;

    public Edible() {

    }

    public Edible(long id, String name, String description, String type, int tier, int value, String effect) {
        super(id, name, description, type, tier, value, effect);

        mEffect = effect;
    }
}
