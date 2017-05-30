package com.makalaster.adventurefriends.model.campaign;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;

import java.util.HashMap;

/**
 * Class to hold the current module. Allows for local caching and reduces calls to FireBase database.
 * Also makes data available throughout the entire app, further reducing the amount of data that needs
 * to be passed between activities and fragments.
 */
//TODO move FireBase calls from activity to here

public class ModuleHolder {
    private static ModuleHolder sInstance;
    private Module mModule;
    private Map mMap;
    private HashMap<String, Note> mNotes;
    private HashMap<String, NonPlayerCharacter> mNPCs;

    private ModuleHolder() {
        mModule = new Module();
        mNotes = new HashMap<>();
        mNPCs = new HashMap<>();
        mMap = new Map();
    }

    public static ModuleHolder getInstance() {
        if (sInstance == null) {
            sInstance = new ModuleHolder();
        }
        return sInstance;
    }

    /**
     * Load the module from FireBase.
     * @param campaignId The campaign the module is a part of.
     * @param moduleId The ID of the current module.
     */
    public void loadModule(String campaignId, String moduleId) {
        DatabaseReference module = FirebaseDatabase.getInstance().getReference("campaigns/" + campaignId + "/modules/" + moduleId);
        module.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mModule = dataSnapshot.getValue(Module.class);
                mNotes = mModule.getNotes();
                mNPCs = mModule.getNPCs();
                mMap = mModule.getMap();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Reset the module's data so it can be used again with no risk of data overlap.
     */
    public void clearModule() {
        mModule = null;
        mNotes = null;
        mNPCs = null;
        mMap = null;
    }

    /**
     * Remove all data from the current map.
     */
    public void clearMap() {
        mMap.clearTiles();
    }

    /**
     * Get the current module.
     * @return The current module.
     */
    public Module getModule() {
        return mModule;
    }

    /**
     * Retrieve a note based on its ID.
     * @param noteId The ID of the note to get.
     * @return The note associated with the given ID.
     */
    public Note getNoteById(String noteId) {
        return mNotes.get(noteId);
    }

    /**
     * Add a note to the current module.
     * @param noteId The ID of the newly created note.
     * @param note The note to be added.
     */
    public void addNote(String noteId, Note note) {
        if (mNotes == null) {
            mNotes = new HashMap<>();
        }
        mNotes.put(noteId, note);
    }

    /**
     * Retrieve an NPC from the module based on its ID.
     * @param NPCId The ID of the NPC to get.
     * @return The NPC associated with the given ID.
     */
    public NonPlayerCharacter getNPCById(String NPCId) {
        return mNPCs.get(NPCId);
    }

    /**
     * Add an NPC to the current module.
     * @param NPCId The ID of the newly created NPC.
     * @param nonPlayerCharacter The NPC that was created.
     */
    public void addNPC(String NPCId, NonPlayerCharacter nonPlayerCharacter) {
        if (mNPCs == null) {
            mNPCs = new HashMap<>();
        }
        mNPCs.put(NPCId, nonPlayerCharacter);
    }

    /**
     * Get the map for the current module.
     * @return The map for the current module.
     */
    public Map getMap() {
        return mMap;
    }

    /**
     * Set the map for the current module.
     * @param map The new map for the module.
     */
    public void setMap(Map map) {
        mMap = map;
    }
}
