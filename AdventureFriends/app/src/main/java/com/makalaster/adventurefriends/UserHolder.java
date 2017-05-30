package com.makalaster.adventurefriends;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.User;
import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;

import java.util.HashMap;

/**
 * Hold the current user info.
 */

public class UserHolder {
    private userLoadedListener mListener;
    private DatabaseReference mUserReference;
    private User mCurrentUser;
    private HashMap<String, Campaign> mCampaigns;
    private HashMap<String, PlayerCharacter> mCharacters;
    private String mEmail, mId, mName;

    private static UserHolder sInstance;

    private UserHolder() {

    }

    public static UserHolder getInstance() {
        if (sInstance == null) {
            sInstance = new UserHolder();
        }
        return sInstance;
    }

    public void setListener(userLoadedListener listener) {
        mListener = listener;
    }

    public void LoadCurrentUser(String userId) {
        mUserReference = FirebaseDatabase.getInstance().getReference("users/" + userId);
        mUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCurrentUser = dataSnapshot.getValue(User.class);
                mCampaigns = mCurrentUser.getCampaigns();
                mCharacters = mCurrentUser.getCharacters();
                mEmail = mCurrentUser.getEmail();
                mId = mCurrentUser.getId();
                mName = mCurrentUser.getName();

                mListener.onUserLoaded();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void clearCurrentUser() {
        mCurrentUser = null;
        mCampaigns = null;
        mCharacters = null;
        mEmail = null;
        mId = null;
        mName = null;
        mUserReference = null;
    }

    public userLoadedListener getListener() {
        return mListener;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }

    public HashMap<String, Campaign> getCampaigns() {
        return mCampaigns;
    }

    public void setCampaigns(HashMap<String, Campaign> campaigns) {
        mCampaigns = campaigns;
    }

    public void addCampaign(String id, Campaign campaign) {

    }

    public HashMap<String, PlayerCharacter> getCharacters() {
        return mCharacters;
    }

    public void setCharacters(HashMap<String, PlayerCharacter> characters) {
        mCharacters = characters;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public static void setInstance(UserHolder instance) {
        sInstance = instance;
    }

    public interface userLoadedListener {
        void onUserLoaded();
    }
}
