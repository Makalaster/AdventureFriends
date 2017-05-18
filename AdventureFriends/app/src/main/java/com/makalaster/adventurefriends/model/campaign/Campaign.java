package com.makalaster.adventurefriends.model.campaign;

import com.makalaster.adventurefriends.model.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Makalaster on 5/16/17.
 */

public class Campaign {
    private String mCampaignId, mCampaignName, mBaseGame;
    private List<Player> mPlayers;
    private List<Module> mModules;

    public Campaign(String campaignId, String campaignName, String baseGame) {
        mCampaignId = campaignId;
        mCampaignName = campaignName;
        mBaseGame = baseGame;
        mPlayers = new ArrayList<>();
        mModules = new LinkedList<>();
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

    public String getBaseGame() {
        return mBaseGame;
    }

    public void setBaseGame(String baseGame) {
        mBaseGame = baseGame;
    }
}
