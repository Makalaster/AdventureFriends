package com.makalaster.adventurefriends.lobby.campaignRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.lobby.lobbyFragments.CampaignListFragment;
import com.makalaster.adventurefriends.model.campaign.Campaign;

import java.util.List;

/**
 * Adapter for a recycler view to display the list of campaigns for which a user is a member.
 * Displays the name of a campaign and the user's character name in the campaign, or "DM" if they are the DM.
 */

public class CampaignListRecyclerViewAdapter extends RecyclerView.Adapter<CampaignViewHolder> {
    private List<Campaign> mCampaigns;
    private CampaignListFragment.OnCampaignSelectedListener mOnCampaignSelectedListener;

    public CampaignListRecyclerViewAdapter(List<Campaign> campaigns, CampaignListFragment.OnCampaignSelectedListener listener) {
        mCampaigns = campaigns;
        mOnCampaignSelectedListener = listener;
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_campaign_list_item, parent, false);

        return new CampaignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampaignViewHolder holder, int position) {
        final Campaign currentCampaign = mCampaigns.get(position);

        holder.mCampaignName.setText(currentCampaign.getCampaignName());
        holder.mCharacterName.setText(currentCampaign.getCharacterName());
        holder.mCampaignListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCampaignSelectedListener.onCampaignSelected(currentCampaign.getCampaignId(), currentCampaign.getDmId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCampaigns.size();
    }
}
