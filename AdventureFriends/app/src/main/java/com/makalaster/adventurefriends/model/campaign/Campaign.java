package com.makalaster.adventurefriends.model.campaign;

import com.makalaster.adventurefriends.model.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Makalaster on 5/16/17.
 */

public class Campaign {
    private String mCampaignId, mCampaignName, mBaseGame, mDmId, mCharacterName, mDescription;
    private List<Player> mPlayers;
    private List<Module> mModules;

    public Campaign() {
        //Empty constructor required by FirebaseRecyclerAdapter
    }

    public Campaign(String campaignId, String campaignName, String baseGame, String dmId, String description) {
        mCampaignId = campaignId;
        mCampaignName = campaignName;
        mBaseGame = baseGame;
        mPlayers = new ArrayList<>();
        mModules = new LinkedList<>();
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

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public void setDmId(String dmId) {
        mDmId = dmId;
    }

    public void setPlayers(List<Player> players) {
        mPlayers = players;
    }

    public void addPlayer(Player player) {
        mPlayers.add(player);
    }

    public void setModules(List<Module> modules) {
        mModules = modules;
    }

    public void addModule(Module module) {
        mModules.add(module);
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
