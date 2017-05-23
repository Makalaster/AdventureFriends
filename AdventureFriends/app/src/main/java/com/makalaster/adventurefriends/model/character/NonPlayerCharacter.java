package com.makalaster.adventurefriends.model.character;

import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.character.components.Ability;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.character.components.item.Defense;
import com.makalaster.adventurefriends.model.character.components.item.Edible;
import com.makalaster.adventurefriends.model.character.components.item.Item;
import com.makalaster.adventurefriends.model.character.components.item.Weapon;

import java.util.HashMap;

/**
 * Created by Makalaster on 5/18/17.
 */

public class NonPlayerCharacter {
    private static final int BASE_SPEED = 4;
    private static final int BASE_HEALTH = 10;
    public static final String WEAPON = "weapon", HAT = "hat", SHIRT = "shirt", BOOTS = "boots";

    private String mName, mId;
    private int mLevel, mSpeed, mBody, mMind, mEssence, mMaxPG, mCurrentPG, mMoney;
    private Size mSize;
    private Job mJob;
    private HashMap<String, Item> mInventory;
    private HashMap<String, Item> mEquipment;
    private HashMap<String, Note> mNotes;
    private HashMap<String, Ability> mAbilities;

    public NonPlayerCharacter() {
    }

    public NonPlayerCharacter(String name, String id, int level, Size size, Job job, int money) {
        mName = name;
        mId = id;
        mLevel = level;
        mSize = size;
        mJob = job;
        mBody = size.getBodyBonus() + job.getBodyBonus();
        mMind = size.getMindBonus() + job.getMindBonus();
        mEssence = size.getEssenceBonus() + job.getEssenceBonus();
        mSpeed = BASE_SPEED - mBody;
        mMaxPG = BASE_HEALTH + (mLevel * mBody) + mLevel;
        mCurrentPG = mMaxPG;
        mMoney = money;
        mInventory = new HashMap<>();
        mEquipment = new HashMap<>();
        fillEquipment();
        mNotes = new HashMap<>();
        mAbilities = new HashMap<>();
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

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public int getBody() {
        return mBody;
    }

    public void setBody(int body) {
        mBody = body;
    }

    public int getMind() {
        return mMind;
    }

    public void setMind(int mind) {
        mMind = mind;
    }

    public int getEssence() {
        return mEssence;
    }

    public void setEssence(int essence) {
        mEssence = essence;
    }

    public int getMaxPG() {
        return mMaxPG;
    }

    public void setMaxPG(int maxPG) {
        mMaxPG = maxPG;
    }

    public int getCurrentPG() {
        return mCurrentPG;
    }

    public void setCurrentPG(int currentPG) {
        mCurrentPG = currentPG;
    }

    public Size getSize() {
        return mSize;
    }

    public void setSize(Size size) {
        mSize = size;
    }

    public Job getJob() {
        return mJob;
    }

    public void setJob(Job job) {
        mJob = job;
    }

    public int getMoney() {
        return mMoney;
    }

    public void setMoney(int money) {
        mMoney = money;
    }

    public HashMap<String, Item> getInventory() {
        return mInventory;
    }

    public void setInventory(HashMap<String, Item> inventory) {
        mInventory = inventory;
    }

    public void addItemToInventory(String id, Item item) {
        mInventory.put(id, item);
    }

    public HashMap<String, Note> getNotes() {
        return mNotes;
    }

    public void setNotes(HashMap<String, Note> notes) {
        mNotes = notes;
    }

    public void addNote(String id, Note note) {
        mNotes.put(id, note);
    }

    public HashMap<String, Ability> getAbilities() {
        return mAbilities;
    }

    public void setAbilities(HashMap<String, Ability> abilities) {
        mAbilities = abilities;
    }

    public void addAbility(String id, Ability ability) {
        mAbilities.put(id, ability);
    }

    public void attackWithWeapon(NonPlayerCharacter target) {

    }

    public void attackWithAbility(Ability ability, NonPlayerCharacter target) {

    }

    public void equip(Weapon weapon) {
        if (mEquipment.get(WEAPON) != null) {
            if (mEquipment.get(WEAPON) instanceof Weapon) {
                ((Weapon) mEquipment.get(WEAPON)).setEquipped(false);
            }
            remove(weapon);
        }
        if (!weapon.isEquipped()) {
            weapon.setEquipped(true);
        }
        mEquipment.put(WEAPON, weapon);
    }

    public void remove(Weapon weapon) {
        mEquipment.put(WEAPON, null);
        if (weapon.isEquipped()) {
            weapon.setEquipped(false);
        }
    }

    public void equip(String slot, Defense defense) {
        if (mEquipment.get(slot) != null) {
            if (mEquipment.get(slot) instanceof Defense) {
                ((Defense) mEquipment.get(slot)).setEquipped(false);
            }
            remove(slot, defense);
        }
        if (!defense.isEquipped()) {
            defense.setEquipped(true);
        }
        mEquipment.put(slot, defense);
    }

    public void remove(String slot, Defense defense) {
        mEquipment.put(slot, null);
        if (defense.isEquipped()) {
            defense.setEquipped(false);
        }
    }

    public void drop(Item item) {
        if (mInventory.containsValue(item)) {
            mInventory.remove(item.getName());
        }
    }

    public void consume(Edible edible) {

    }

    public void give(NonPlayerCharacter npc, Item item) {

    }

    public void move() {

    }

    public HashMap<String, Item> getEquipment() {
        return mEquipment;
    }

    public void setEquipment(HashMap<String, Item> equipment) {
        mEquipment = equipment;
    }

    private void fillEquipment() {
        mEquipment.put(WEAPON, null);
        mEquipment.put(HAT, null);
        mEquipment.put(SHIRT, null);
        mEquipment.put(BOOTS, null);
    }
}
