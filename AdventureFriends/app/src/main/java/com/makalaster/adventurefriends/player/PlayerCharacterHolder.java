package com.makalaster.adventurefriends.player;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.model.character.components.Ability;
import com.makalaster.adventurefriends.model.character.components.item.Item;

import java.util.HashMap;

/**
 * Created by Makalaster on 5/21/17.
 */

public class PlayerCharacterHolder {
    private PlayerCharacter mPlayerCharacter;
    private HashMap<String, Ability> mAbilities;
    private HashMap<String, Item> mInventory;
    private HashMap<String, Note> mNotes;

    private static PlayerCharacterHolder sInstance;

    private PlayerCharacterHolder() {

    }

    public static PlayerCharacterHolder getInstance() {
        if (sInstance == null) {
            sInstance = new PlayerCharacterHolder();
        }
        return sInstance;
    }

    public void loadCharacter(PlayerCharacter playerCharacter) {
        mPlayerCharacter = playerCharacter;
        mAbilities = playerCharacter.getAbilities();
        mInventory = playerCharacter.getInventory();
        mNotes = playerCharacter.getNotes();
    }

    public void clearCharacter() {
        mPlayerCharacter = null;
        mNotes = null;
        mInventory = null;
        mAbilities = null;
    }

    public PlayerCharacter getPlayerCharacter() {
        return mPlayerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        mPlayerCharacter = playerCharacter;
    }

    public HashMap<String, Ability> getAbilities() {
        return mAbilities;
    }

    public void addAbility(String id, Ability ability) {
        if (mAbilities == null) {
            mAbilities = new HashMap<>();
        }
        mAbilities.put(id, ability);
    }

    public void setAbilities(HashMap<String, Ability> abilities) {
        mAbilities = abilities;
    }

    public HashMap<String, Item> getInventory() {
        return mInventory;
    }

    public void setInventory(HashMap<String, Item> inventory) {
        mInventory = inventory;
    }

    public void addItemToInventory(String id, Item item) {
        if (mInventory == null) {
            mInventory = new HashMap<>();
        }
        mInventory.put(id, item);
    }

    public HashMap<String, Note> getNotes() {
        return mNotes;
    }

    public void setNotes(HashMap<String, Note> notes) {
        mNotes = notes;
    }

    public void addNote(String id, Note note) {
        if (mNotes == null) {
            mNotes = new HashMap<>();
        }
        mNotes.put(id, note);
    }
}
