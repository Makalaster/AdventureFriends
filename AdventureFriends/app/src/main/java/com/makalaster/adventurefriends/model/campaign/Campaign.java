package com.makalaster.adventurefriends.model.campaign;

/**
 * Created by Makalaster on 5/16/17.
 */

public class Campaign {
    private String mCampaignId, mCampaignName, mPlayerName;

    public Campaign(String campaignId, String campaignName, String playerName) {
        mCampaignId = campaignId;
        mCampaignName = campaignName;
        mPlayerName = playerName;
    }

    public String getCampaignId() {
        return mCampaignId;
    }

    public String getCampaignName() {
        return mCampaignName;
    }

    public void setCampaignName(String campaignName) {
        mCampaignName = campaignName;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String playerName) {
        mPlayerName = playerName;
    }
}
