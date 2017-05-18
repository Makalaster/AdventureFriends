package com.makalaster.adventurefriends.model.campaign;

import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.npc.NPC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makalaster on 5/18/17.
 */

public class Module {
    public static final int MODULE_BATTLE = 0, MODULE_STORY = 1, MODULE_NON_BATTLE = 2;
    private int mType;
    private String mId, mTitle, mSummary;
    private List<Note> mNotes;
    private List<NPC> mNPCs;

    public Module() {
        //Empty constructor required by FirebaseRecyclerAdapter
    }

    public Module(String id, String title, String summary, int type) {
        mId = id;
        mTitle = title;
        mSummary = summary;

        mNotes = new ArrayList<>();
        mNPCs = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public List<Note> getNotes() {
        return mNotes;
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
    }

    public void addNote(Note note) {
        mNotes.add(note);
    }

    public List<NPC> getNPCs() {
        return mNPCs;
    }

    public void setNPCs(List<NPC> NPCs) {
        mNPCs = NPCs;
    }

    public void addNPC(NPC npc) {
        mNPCs.add(npc);
    }

    public String getType() {
        String type;
        switch (mType) {
            case MODULE_BATTLE:
                type = "Battle";
                break;
            case MODULE_STORY:
                type = "Story";
                break;
            case MODULE_NON_BATTLE:
                type = "Non-battle encounter";
                break;
            default:
                type = "Other";
        }
        return type;
    }
}
