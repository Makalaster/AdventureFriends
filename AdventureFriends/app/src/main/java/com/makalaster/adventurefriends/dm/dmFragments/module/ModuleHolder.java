package com.makalaster.adventurefriends.dm.dmFragments.module;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.campaign.Module;
import com.makalaster.adventurefriends.model.npc.NPC;

import java.util.HashMap;

/**
 * Created by Makalaster on 5/19/17.
 */

public class ModuleHolder {
    private static ModuleHolder sInstance;
    private Module mModule;
    private HashMap<String, Note> mNotes;
    private HashMap<String, NPC> mNPCs;

    private ModuleHolder() {

    }

    public static ModuleHolder getInstance() {
        if (sInstance == null) {
            sInstance = new ModuleHolder();
        }
        return sInstance;
    }

    public void loadModule(String campaignId, String moduleId) {
        DatabaseReference module = FirebaseDatabase.getInstance().getReference("campaigns/" + campaignId + "/modules/" + moduleId);
        module.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mModule = dataSnapshot.getValue(Module.class);
                mNotes = mModule.getNotes();
                mNPCs = mModule.getNPCs();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void clearModule() {
        mModule = null;
        mNotes = null;
        mNPCs = null;
    }

    public Module getModule() {
        return mModule;
    }

    public Note getNoteById(String noteId) {
        return mNotes.get(noteId);
    }

    public void addNote(String noteId, Note note) {
        mNotes.put(noteId, note);
    }

    public NPC getNPCById(String NPCId) {
        return mNPCs.get(NPCId);
    }

    public void addNPC(String NPCId, NPC npc) {
        mNPCs.put(NPCId, npc);
    }
}
