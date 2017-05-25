package com.makalaster.adventurefriends.player.pages.recyclers;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.character.components.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Makalaster on 5/22/17.
 */

public class AbilityRecyclerAdapter extends RecyclerView.Adapter<AbilityHolder> {
    private ArrayList<Ability> mAbilities;

    public AbilityRecyclerAdapter(ArrayList<Ability> abilities) {
        mAbilities = abilities;
    }

    @Override
    public AbilityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ability_holder, parent, false);

        return new AbilityHolder(view);
    }

    @Override
    public void onBindViewHolder(AbilityHolder holder, int position) {
        Ability ability = mAbilities.get(position);

        Resources res = holder.mCheck.getContext().getResources();

        holder.mName.setText(ability.getName());
        holder.mLevel.setText(String.format(Locale.ENGLISH, " %s %d",
                res.getString(R.string.level_label), ability.getLevel()));
        holder.mDamage.setText(String.format(Locale.ENGLISH, " %d ", ability.getDamage()));
        holder.mRange.setText(String.format(Locale.ENGLISH, " %d ", ability.getRange()));
        holder.mCheck.setText(String.format(Locale.ENGLISH, " %s", ability.getCheck()));

        String effects = ability.getEffects();
        holder.mEffects.setText(String.format(Locale.ENGLISH, " %s", effects == null ? "None" : effects));
        holder.mDescription.setText(ability.getDescription());
        holder.mQuote.setText(ability.getQuote());
    }

    @Override
    public int getItemCount() {
        return mAbilities.size();
    }
}
