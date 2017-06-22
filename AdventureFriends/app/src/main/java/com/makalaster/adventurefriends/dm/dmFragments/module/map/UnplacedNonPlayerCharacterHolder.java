package com.makalaster.adventurefriends.dm.dmFragments.module.map;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;

/**
 * Created by Makalaster on 6/22/17.
 */

public class UnplacedNonPlayerCharacterHolder extends RecyclerView.ViewHolder {
    public View mUnplacedNPC;
    public TextView mNameAndLevel, mSizeAndJob, mNPCStats;

    public UnplacedNonPlayerCharacterHolder(View itemView) {
        super(itemView);

        mUnplacedNPC = itemView.findViewById(R.id.unplaced_npc);
        mNameAndLevel = (TextView) itemView.findViewById(R.id.name_and_level);
        mSizeAndJob = (TextView) itemView.findViewById(R.id.size_and_job);
        mNPCStats = (TextView) itemView.findViewById(R.id.npc_stats);
    }
}
