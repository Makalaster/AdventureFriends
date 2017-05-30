package com.makalaster.adventurefriends.model.campaign;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;

import java.util.HashMap;

/**
 * Class to hold the current campaign. Allows for local caching and reduces calls to FireBase database.
 * Also makes data available throughout the entire app, further reducing the amount of data that needs
 * to be passed between activities and fragments.
 */
//TODO move FireBase calls from activity to here

public class CampaignHolder {
    private Campaign mCampaign;
    private String mCampaignId;
    private HashMap<String, Module> mModules;
    private HashMap<String, PlayerCharacter> mPlayers;
    private Map mCurrentMap;

    private static CampaignHolder sInstance;

    public static CampaignHolder getInstance() {
        if (sInstance == null) {
            sInstance = new CampaignHolder();
        }
        return sInstance;
    }

    private CampaignHolder() {

    }

    /**
     * Load the campaign from FireBase based on the given campaign ID.
     * @param campaignId The ID of the campaign to load.
     */
    public void loadCampaign(String campaignId) {
        DatabaseReference campaign = FirebaseDatabase.getInstance().getReference("campaigns/" + campaignId);
        campaign.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    mCampaign = dataSnapshot.getValue(Campaign.class);
                    mCampaignId = mCampaign.getCampaignId();
                    mModules = mCampaign.getModules();
                    mPlayers = mCampaign.getPlayers();
                    mCurrentMap = mCampaign.getCurrentMap();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Empty out the campaign to prevent data carrying over from previous sessions.
     */
    public void clearCampaign() {
        mCampaign = null;
        mModules = null;
        mPlayers = null;
        mCampaignId = null;
        mCurrentMap = null;
    }

    /**
     * Set the current campaign to an existing campaign object.
     * Faster but may not be as up to date.
     * @param campaign The existing campaign object to load.
     */
    public void setCampaign(Campaign campaign) {
        mCampaign = campaign;
        mModules = mCampaign.getModules();
        mPlayers = mCampaign.getPlayers();
        mCampaignId = mCampaign.getCampaignId();
        mCurrentMap = campaign.getCurrentMap();
    }

    /**
     * Get the current campaign ID.
     * @return The current campaign's ID.
     */
    public String getCampaignId() {
        return mCampaignId;
    }

    /**
     * Set the ID of the current campaign.
     * @param campaignId The ID to set.
     */
    public void setCampaignId(String campaignId) {
        mCampaignId = campaignId;
    }

    /**
     * Retrieve a specific module from the campaign based on ID.
     * @param moduleId The ID of the module to get.
     * @return The module associated with the given ID.
     */
    public Module getModuleById(String moduleId) {
        return mModules.get(moduleId);
    }

    /**
     * Return the current campaign object.
     * @return The current campaign.
     */
    public Campaign getCampaign() {
        return mCampaign;
    }

    /**
     * Add a module to the current campaign.
     * @param newId The ID of the new module.
     * @param newModule The new module to be added.
     */
    public void addModule(String newId, Module newModule) {
        if (mModules == null) {
            mModules = new HashMap<>();
        }
        mModules.put(newId, newModule);
    }

    /**
     * Get the players in the current campaign.
     * @return The players in the current campaign.
     */
    public HashMap<String, PlayerCharacter> getPlayers() {
        if (mPlayers == null) {
            mPlayers = new HashMap<>();
        }
        return mPlayers;
    }

    /**
     * Add a player to the campaign.
     * @param id The ID of the player to be added.
     * @param player The player to be added.
     */
    public void addPlayer(String id, PlayerCharacter player) {
        if (mPlayers == null) {
            mPlayers = new HashMap<>();
        }
        mPlayers.put(id, player);
    }

    /**
     * Get the current map for the campaign.
     * @return The current map.
     */
    public Map getCurrentMap() {
        return mCurrentMap;
    }

    /**
     * Set the current map for the campaign.
     * @param currentMap The new current map.
     */
    public void setCurrentMap(Map currentMap) {
        mCurrentMap = currentMap;
    }
}
