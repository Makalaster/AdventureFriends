package com.makalaster.adventurefriends.model;

import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.character.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makalaster on 5/17/17.
 */

public class User {
    private String mId;
    private List<Campaign> mCampaigns;
    private List<Character> mCharacters;

    public User(String id) {
        mId = id;
        mCampaigns = new ArrayList<>();
        mCharacters = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }

    public List<Campaign> getCampaigns() {
        return mCampaigns;
    }

    public List<Character> getCharacters() {
        return mCharacters;
    }
}
