package com.makalaster.adventurefriends.model.campaign;

import com.makalaster.adventurefriends.model.User;
import com.makalaster.adventurefriends.model.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Makalaster on 5/16/17.
 */

public class Campaign {
    private String mCampaignId, mCampaignName, mBaseGame, mDmId, mCharacterName;
    private List<Player> mPlayers;
    private List<Module> mModules;

    public Campaign() {
        //Empty constructor required by FirebaseRecyclerAdapter
    }

    public Campaign(String campaignId, String campaignName, String baseGame, String dmId) {
        mCampaignId = campaignId;
        mCampaignName = campaignName;
        mBaseGame = baseGame;
        mPlayers = new ArrayList<>();
        mModules = new LinkedList<>();
        mDmId = dmId;
        mCharacterName = "DM";
    }

    public String getCampaignId() {
        return mCampaignId;
    }

    public void setCampaignId(String campaignId) {
        mCampaignId = campaignId;
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

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public List<Module> getModules() {
        return mModules;
    }

    public String getDmId() {
        return mDmId;
    }

    public String getCharacterName() {
        return mCharacterName;
    }

    public void setCharacterName(String characterName) {
        mCharacterName = characterName;
    }
}
