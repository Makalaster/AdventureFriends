package com.makalaster.adventurefriends.dm.dmFragments.module.map;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Makalaster on 6/22/17.
 */

public class UnplacedNonPlayerCharacterRecyclerViewAdapter extends RecyclerView.Adapter<UnplacedNonPlayerCharacterHolder> {
    private ArrayList<NonPlayerCharacter> mUnassignedNPCs;
    private OnItemClickListener mListener;

    public UnplacedNonPlayerCharacterRecyclerViewAdapter(ArrayList<NonPlayerCharacter> unassignedNPCs, OnItemClickListener listener) {
        mUnassignedNPCs = unassignedNPCs;
        mListener = listener;
    }

    @Override
    public UnplacedNonPlayerCharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_unplaced_npc_item, parent, false);

        return new UnplacedNonPlayerCharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(UnplacedNonPlayerCharacterHolder holder, int position) {
        final NonPlayerCharacter currentNPC = mUnassignedNPCs.get(position);

        holder.mNameAndLevel.setText(String.format(Locale.ENGLISH, "%s, Level: %d", currentNPC.getName(), currentNPC.getLevel()));
        holder.mSizeAndJob.setText(String.format(Locale.ENGLISH, "Size: %s Job: %s", currentNPC.getSize().getName(), currentNPC.getJob().getName()));
        holder.mNPCStats.setText(String.format(Locale.ENGLISH, "Mind: %d Body: %d Essence: %d", currentNPC.getMind(), currentNPC.getBody(), currentNPC.getEssence()));

        holder.mUnplacedNPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.placeNPC(currentNPC.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUnassignedNPCs.size();
    }

    public interface OnItemClickListener {
        void placeNPC(String id);
    }
}
