package com.makalaster.adventurefriends.lobby.campaignRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;

/**
 * Created by Makalaster on 5/16/17.
 */

public class CampaignViewHolder extends RecyclerView.ViewHolder {
    public TextView mCampaignName, mCharacterName;
    public View mCampaignListItem;

    public CampaignViewHolder(View itemView) {
        super(itemView);

        mCampaignName = (TextView) itemView.findViewById(R.id.campaign_name);
        mCharacterName = (TextView) itemView.findViewById(R.id.character_name);
        mCampaignListItem = itemView.findViewById(R.id.campaign_list_item);
    }
}
