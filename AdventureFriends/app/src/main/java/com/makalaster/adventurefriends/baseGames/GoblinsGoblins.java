package com.makalaster.adventurefriends.baseGames;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

/**
 * Class to hold local data for Goblins? Goblins! game.
 */

public class GoblinsGoblins extends SQLiteOpenHelper {
    private static final String DB_NAME = "goblins_goblins.db";
    private static final int DB_VERSION = 1;

    public static abstract class AbilitiesTable implements BaseColumns {
        public static final String TABLE_NAME = "abilities";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_QUOTE = "quote";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DAMAGE = "damage";
        public static final String COLUMN_RANGE = "range";
        public static final String COLUMN_JOB_ID = "job_id";
        public static final String COLUMN_EFFECTS = "effects";
    }

    private static final String SQL_CREATE_TABLE_ABILITIES =
            "CREATE TABLE " + AbilitiesTable.TABLE_NAME + " (" +
                    AbilitiesTable.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    AbilitiesTable.COLUMN_NAME + " TEXT," +
                    AbilitiesTable.COLUMN_LEVEL + " INTEGER," +
                    AbilitiesTable.COLUMN_QUOTE + " TEXT," +
                    AbilitiesTable.COLUMN_DESCRIPTION + " TEXT," +
                    AbilitiesTable.COLUMN_DAMAGE + " INTEGER," +
                    AbilitiesTable.COLUMN_RANGE + " INTEGER," +
                    AbilitiesTable.COLUMN_JOB_ID + " INTEGER," +
                    AbilitiesTable.COLUMN_EFFECTS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_ABILITIES =
            "DROP TABLE IF EXISTS " + AbilitiesTable.TABLE_NAME;

    public static abstract class ItemsTable implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_TIER = "tier";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_EFFECT = "effect";
        public static final String COLUMN_RANGE = "range";
    }

    private static final String SQL_CREATE_TABLE_ITEMS =
            "CREATE TABLE " + ItemsTable.TABLE_NAME + " (" +
                    ItemsTable.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    ItemsTable.COLUMN_NAME + " TEXT," +
                    ItemsTable.COLUMN_DESCRIPTION + " TEXT," +
                    ItemsTable.COLUMN_TYPE + " TEXT," +
                    ItemsTable.COLUMN_TIER + " INTEGER," +
                    ItemsTable.COLUMN_VALUE + " INTEGER," +
                    ItemsTable.COLUMN_EFFECT + " TEXT," +
                    ItemsTable.COLUMN_RANGE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_ITEMS =
            "DROP TABLE IF EXISTS " + ItemsTable.TABLE_NAME;


    public static abstract class JobsTable implements BaseColumns {
        public static final String TABLE_NAME = "jobs";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BONUS = "bonus";
    }

    private static final String SQL_CREATE_TABLE_JOBS =
            "CREATE TABLE " + JobsTable.TABLE_NAME + " (" +
                    JobsTable.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    JobsTable.COLUMN_NAME + " TEXT," +
                    JobsTable.COLUMN_DESCRIPTION + " TEXT," +
                    JobsTable.COLUMN_BONUS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_JOBS =
            "DROP TABLE IF EXISTS " + JobsTable.TABLE_NAME;

    public static abstract class SizesTable implements BaseColumns {
        public static final String TABLE_NAME = "sizes";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BONUS = "bonus";
        public static final String COLUMN_DESCRIPTION = "description";
    }

    private static final String SQL_CREATE_TABLE_SIZES =
            "CREATE TABLE " + SizesTable.TABLE_NAME + " (" +
                    SizesTable.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    SizesTable.COLUMN_NAME + " TEXT," +
                    SizesTable.COLUMN_BONUS + " TEXT," +
                    SizesTable.COLUMN_DESCRIPTION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_SIZES =
            "DROP TABLE IF EXISTS " + SizesTable.TABLE_NAME;

    private static GoblinsGoblins sInstance;

    public static GoblinsGoblins getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GoblinsGoblins(context.getApplicationContext());
        }
        return sInstance;
    }

    private GoblinsGoblins(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ABILITIES);
        db.execSQL(SQL_CREATE_TABLE_ITEMS);
        db.execSQL(SQL_CREATE_TABLE_JOBS);
        db.execSQL(SQL_CREATE_TABLE_SIZES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_ABILITIES);
        db.execSQL(SQL_DELETE_ENTRIES_ITEMS);
        db.execSQL(SQL_DELETE_ENTRIES_JOBS);
        db.execSQL(SQL_DELETE_ENTRIES_SIZES);

        onCreate(db);
    }

    //TODO get list of all sizes
    //TODO get list of all jobs
    //TODO get list of all abilities
    //TODO get list of all items
    //TODO get list of all weapons
    //TODO get list of all defense
    //TODO get list of all edibles
    //TODO get lists of abilities by job

}
