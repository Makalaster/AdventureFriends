package com.makalaster.adventurefriends.model;

import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.character.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Makalaster on 5/17/17.
 */

public class User {
    private String mId;
    private String mName;
    private String mEmail;
    private HashMap<String, Campaign> mCampaigns;
    private HashMap<String, Character> mCharacters;

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

    public HashMap<String, Character> getCharacters() {
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

    public void setCharacters(HashMap<String, Character> characters) {
        mCharacters = characters;
    }

    public void addCharacter(String id, Character character) {
        mCharacters.put(id, character);
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }
}
