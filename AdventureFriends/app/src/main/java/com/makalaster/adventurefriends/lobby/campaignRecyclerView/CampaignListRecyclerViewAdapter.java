package com.makalaster.adventurefriends.lobby.campaignRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.campaign.Campaign;

import java.util.List;

/**
 * Created by Makalaster on 5/16/17.
 */

public class CampaignListRecyclerViewAdapter extends RecyclerView.Adapter<CampaignViewHolder> {
    private List<Campaign> mCampaigns;

    public CampaignListRecyclerViewAdapter(List<Campaign> campaigns) {
        mCampaigns = campaigns;
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_campaign_list_item, parent, false);

        return new CampaignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampaignViewHolder holder, int position) {
        holder.mCampaignName.setText(mCampaigns.get(position).getCampaignName());
        holder.mCharacterName.setText(mCampaigns.get(position).getPlayerName());
    }

    @Override
    public int getItemCount() {
        return mCampaigns.size();
    }
}
