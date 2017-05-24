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
    private HashMap<String, Item> mEquipment;
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
        mEquipment = playerCharacter.getEquipment();
        mNotes = playerCharacter.getNotes();
    }

    public void clearCharacter() {
        mPlayerCharacter = null;
        mNotes = null;
        mInventory = null;
        mEquipment = null;
        mAbilities = null;
    }

    public PlayerCharacter getPlayerCharacter() {
        return mPlayerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        mPlayerCharacter = playerCharacter;
    }

    public HashMap<String, Ability> getAbilities() {
        if (mAbilities == null) {
            mAbilities = new HashMap<>();
        }
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
        if (mInventory == null) {
            mInventory = new HashMap<>();
        }
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
        if (mNotes == null) {
            mNotes = new HashMap<>();
        }
        return mNotes;
    }

    public Note getNoteById(String id) {
        return mNotes.get(id);
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

    public HashMap<String, Item> getEquipment() {
        if (mEquipment == null) {
            mEquipment = new HashMap<>();
        }
        return mEquipment;
    }

    public void setEquipment(HashMap<String, Item> equipment) {
        mEquipment = equipment;
    }
}
