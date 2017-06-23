package com.makalaster.adventurefriends.model.campaign;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;

import java.util.ArrayList;

/**
 * Class to hold the current module. Allows for local caching and reduces calls to FireBase database.
 * Also makes data available throughout the entire app, further reducing the amount of data that needs
 * to be passed between activities and fragments.
 */
//TODO move FireBase calls from activity to here

public class ModuleHolder {
    private static ModuleHolder sInstance;
    private Module mModule;
    private DatabaseReference mModuleReference;

    private ModuleHolder() {
        mModule = new Module();
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
        mModuleReference = FirebaseDatabase.getInstance().getReference("campaigns/" + campaignId + "/modules/" + moduleId);
        mModuleReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mModule = dataSnapshot.getValue(Module.class);
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
    }

    /**
     * Remove all data from the current map.
     */
    public void clearMap() {
        mModule.getMap().clearTiles();
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
        return mModule.getNotes().get(noteId);
    }

    /**
     * Add a note to the current module.
     * @param noteId The ID of the newly created note.
     * @param note The note to be added.
     */
    public void addNote(String noteId, Note note) {
        mModule.addNote(noteId, note);
    }

    /**
     * Retrieve an NPC from the module based on its ID.
     * @param NPCId The ID of the NPC to get.
     * @return The NPC associated with the given ID.
     */
    public NonPlayerCharacter getNPCById(String NPCId) {
        return mModule.getNPCs().get(NPCId);
    }

    /**
     * Add an NPC to the current module.
     * @param NPCId The ID of the newly created NPC.
     * @param nonPlayerCharacter The NPC that was created.
     */
    public void addNPC(String NPCId, NonPlayerCharacter nonPlayerCharacter) {
        mModule.addNPC(NPCId, nonPlayerCharacter);
    }

    public void setNPCPlaced(String id, boolean placed) {
        getNPCById(id).setPlaced(placed);
        mModuleReference.child("npcs").child(id).child("placed").setValue(placed);
    }

    public ArrayList<NonPlayerCharacter> getNPCs() {
        ArrayList<NonPlayerCharacter> npcs = new ArrayList<>();
        npcs.addAll(mModule.getNPCs().values());

        return npcs;
    }

    /**
     * Get the map for the current module.
     * @return The map for the current module.
     */
    public Map getMap() {
        return mModule.getMap();
    }

    /**
     * Set the map for the current module.
     * @param map The new map for the module.
     */
    public void setMap(Map map) {
        mModule.setMap(map);
    }
}
