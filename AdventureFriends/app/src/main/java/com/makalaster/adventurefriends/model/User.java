package com.makalaster.adventurefriends.model;

import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;

import java.util.HashMap;

/**
 * Represents a user object. Users can be players or DMs, depending on whether they join or create a campaign.
 */

public class User {
    private String mId;
    private String mName;
    private String mEmail;
    private HashMap<String, Campaign> mCampaigns;
    private HashMap<String, PlayerCharacter> mCharacters;

    public User() {

    }

    public User(String id, String name, String email) {
        mId = id;
        mName = name;
        mEmail = email;
        mCampaigns = new HashMap<>();
        mCharacters = new HashMap<>();
    }

    public String getId() {
        return mId;
    }

    public HashMap<String, Campaign> getCampaigns() {
        return mCampaigns;
    }

    public HashMap<String, PlayerCharacter> getCharacters() {
        return mCharacters;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setCampaigns(HashMap<String, Campaign> campaigns) {
        mCampaigns = campaigns;
    }

    public void addCampaign(String id, Campaign campaign) {
        mCampaigns.put(id, campaign);
    }

    public void setCharacters(HashMap<String, PlayerCharacter> characters) {
        mCharacters = characters;
    }

    public void addCharacter(String id, PlayerCharacter playerCharacter) {
        mCharacters.put(id, playerCharacter);
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }
}
