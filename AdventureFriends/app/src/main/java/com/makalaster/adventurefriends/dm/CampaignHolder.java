package com.makalaster.adventurefriends.dm;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.campaign.Module;

import java.util.Map;

/**
 * Created by Makalaster on 5/19/17.
 */

public class CampaignHolder {
    private Campaign mCampaign;
    private Map<String, Module> mModules;

    private static CampaignHolder sInstance;

    public static CampaignHolder getInstance() {
        if (sInstance == null) {
            sInstance = new CampaignHolder();
        }
        return sInstance;
    }

    private CampaignHolder() {

    }

    public void loadCampaign(String campaignId) {
        DatabaseReference campaign = FirebaseDatabase.getInstance().getReference("campaigns/" + campaignId);
        campaign.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCampaign = dataSnapshot.getValue(Campaign.class);
                mModules = mCampaign.getModules();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void clearCampaign() {
        mCampaign = null;
        mModules = null;
    }

    public Module getModuleById(String moduleId) {
        return mModules.get(moduleId);
    }

    public Campaign getCampaign() {
        return mCampaign;
    }

    public void addModule(String newId, Module newModule) {
        mModules.put(newId, newModule);
    }
}
