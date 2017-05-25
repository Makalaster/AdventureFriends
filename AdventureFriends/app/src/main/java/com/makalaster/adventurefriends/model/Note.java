package com.makalaster.adventurefriends.model;

/**
 * Represents a note object. Can be part of a module or a player character.
 */

public class Note {
    private String mTitle, mBody, mId;

    public Note() {
    }

    public Note(String id, String title, String body) {
        mId = id;
        mTitle = title;
        mBody = body;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
