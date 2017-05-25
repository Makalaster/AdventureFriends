package com.makalaster.adventurefriends.model.character.components;

/**
 * Represents a job that a player or non-player character can have.
 */

public class Job {
    private long mId;
    private String mName, mDescription, mBonus;

    public Job() {

    }

    public Job(long id, String name, String description, String bonus) {
        mId = id;
        mName = name;
        mDescription = description;
        mBonus = bonus;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getBonus() {
        return mBonus;
    }

    /**
     * Get the integer value of the "body" attribute bonus for this job.
     * @return The integer body value.
     */
    public int getBodyBonus() {
        int body = 0;
        String[] bonus = mBonus.split(" ");
        if (bonus[1].equals("body")) {
            body = Integer.parseInt(bonus[0]);
        }
        return body;
    }

    /**
     * Get the integer value of the "mind" attribute bonus for this job.
     * @return The integer mind value.
     */
    public int getMindBonus() {
        int mind = 0;
        String[] bonus = mBonus.split(" ");
        if (bonus[1].equals("mind")) {
            mind = Integer.parseInt(bonus[0]);
        }
        return mind;
    }

    /**
     * Get the integer value of the "essence" attribute bonus for this job.
     * @return The integer essence value.
     */
    public int getEssenceBonus() {
        int essence = 0;
        String[] bonus = mBonus.split(" ");
        if (bonus[1].equals("essence")) {
            essence = Integer.parseInt(bonus[0]);
        }
        return essence;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setBonus(String bonus) {
        mBonus = bonus;
    }
}
