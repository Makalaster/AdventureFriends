package com.makalaster.adventurefriends.player.pages.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;

/**
 * Holds the views for an ability.
 */

public class AbilityHolder extends RecyclerView.ViewHolder {
    public TextView mName, mLevel, mDamage, mRange, mCheck, mEffects, mDescription, mQuote;

    public AbilityHolder(View itemView) {
        super(itemView);

        mName = (TextView) itemView.findViewById(R.id.ability_name);
        mLevel = (TextView) itemView.findViewById(R.id.ability_level);
        mDamage = (TextView) itemView.findViewById(R.id.ability_damage);
        mRange = (TextView) itemView.findViewById(R.id.ability_range);
        mCheck = (TextView) itemView.findViewById(R.id.ability_check);
        mEffects = (TextView) itemView.findViewById(R.id.ability_effects);
        mDescription = (TextView) itemView.findViewById(R.id.ability_description);
        mQuote = (TextView) itemView.findViewById(R.id.ability_quote);
    }
}
