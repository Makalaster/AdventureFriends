package com.makalaster.adventurefriends.model.campaign;

/**
 * Created by Makalaster on 5/16/17.
 */

public class Campaign {
    private String mCampaignName, mPlayerName;

    public Campaign(String campaignName, String playerName) {
        mCampaignName = campaignName;
        mPlayerName = playerName;
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
