package com.makalaster.adventurefriends.model.campaign;

import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;

import java.util.HashMap;

/**
 * Represents a campaign object.
 */

public class Campaign {
    private String mCampaignId, mCampaignName, mBaseGame, mDmId, mCharacterName, mDescription;
    private HashMap<String, PlayerCharacter> mPlayers;
    private HashMap<String, Module> mModules;
    private Map mCurrentMap;

    public Campaign() {
        //Empty constructor required by FireBase
    }

    public Campaign(String campaignId, String campaignName, String baseGame, String dmId, String description) {
        mCampaignId = campaignId;
        mCampaignName = campaignName;
        mBaseGame = baseGame;
        mPlayers = new HashMap<>();
        mModules = new HashMap<>();
        mDmId = dmId;
        mCharacterName = "DM"; //Default value for a new campaign. Will be changed for each player as they join a campaign.
        mDescription = description;

        mCurrentMap = null;
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

    public HashMap<String, PlayerCharacter> getPlayers() {
        return mPlayers;
    }

    public void setDmId(String dmId) {
        mDmId = dmId;
    }

    public void setPlayers(HashMap<String, PlayerCharacter> players) {
        mPlayers = players;
    }

    public void addPlayer(String id, PlayerCharacter player) {
        mPlayers.put(id, player);
    }

    public void setModules(HashMap<String, Module> modules) {
        mModules = modules;
    }

    public void addModule(String id, Module module) {
        mModules.put(id, module);
    }

    public HashMap<String, Module> getModules() {
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

    public Map getCurrentMap() {
        return mCurrentMap;
    }

    public void setCurrentMap(Map currentMap) {
        mCurrentMap = currentMap;
    }
}
