package com.makalaster.adventurefriends.model.campaign;

import com.makalaster.adventurefriends.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Makalaster on 5/16/17.
 */

public class Campaign {
    private String mCampaignId, mCampaignName, mBaseGame, mDmId, mCharacterName, mDescription;
    private Map<String, Player> mPlayers;
    private Map<String, Module> mModules;

    public Campaign() {
        //Empty constructor required by FirebaseRecyclerAdapter
    }

    public Campaign(String campaignId, String campaignName, String baseGame, String dmId, String description) {
        mCampaignId = campaignId;
        mCampaignName = campaignName;
        mBaseGame = baseGame;
        mPlayers = new HashMap<>();
        mModules = new HashMap<>();
        mDmId = dmId;
        mCharacterName = "DM";
        mDescription = description;
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

    public Map<String, Player> getPlayers() {
        return mPlayers;
    }

    public void setDmId(String dmId) {
        mDmId = dmId;
    }

    public void setPlayers(HashMap<String, Player> players) {
        mPlayers = players;
    }

    public void addPlayer(String id, Player player) {
        mPlayers.put(id, player);
    }

    public void setModules(Map<String, Module> modules) {
        mModules = modules;
    }

    public void addModule(String id, Module module) {
        mModules.put(id, module);
    }

    public Map<String, Module> getModules() {
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
