package com.makalaster.adventurefriends.baseGames;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.makalaster.adventurefriends.model.character.components.Ability;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.character.components.item.Defense;
import com.makalaster.adventurefriends.model.character.components.item.Edible;
import com.makalaster.adventurefriends.model.character.components.item.Item;
import com.makalaster.adventurefriends.model.character.components.item.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold local data for Goblins? Goblins! game.
 */

public class GoblinsGoblins extends SQLiteOpenHelper {
    private static final String DB_NAME = "goblins_goblins.db";
    private static final int DB_VERSION = 1;

    /**
     * Table to hold abilities
     */
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
        public static final String COLUMN_CHECK = "check";
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
                    AbilitiesTable.COLUMN_EFFECTS + " TEXT," +
                    AbilitiesTable.COLUMN_CHECK + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_ABILITIES =
            "DROP TABLE IF EXISTS " + AbilitiesTable.TABLE_NAME;

    /**
     * Table to hold items
     */
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

    /**
     * Table to hold jobs
     */
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

    /**
     * Table to hold sizes
     */
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

    /**
     * Get all possible sizes in the database.
     * @return A list of all sizes.
     */
    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor sizeCursor = db.query(SizesTable.TABLE_NAME, null, null, null, null, null, null);

        if (sizeCursor.moveToFirst()) {
            while (!sizeCursor.isAfterLast()) {
                long id = sizeCursor.getLong(sizeCursor.getColumnIndex(SizesTable.COLUMN_ID));
                String name = sizeCursor.getString(sizeCursor.getColumnIndex(SizesTable.COLUMN_NAME));
                String bonus = sizeCursor.getString(sizeCursor.getColumnIndex(SizesTable.COLUMN_BONUS));
                String description = sizeCursor.getString(sizeCursor.getColumnIndex(SizesTable.COLUMN_DESCRIPTION));
                sizes.add(new Size(id, name, description, bonus));

                sizeCursor.moveToNext();
            }
        }
        sizeCursor.close();

        return sizes;
    }

    /**
     * Get a size by its ID.
     * @param id The ID to search for.
     * @return The size associated with the given ID.
     */
    public Size getSizeById(long id) {
        Size size = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor sizeCursor = db.query(SizesTable.TABLE_NAME, null,
                SizesTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);
        if (sizeCursor.moveToFirst()) {
            String name = sizeCursor.getString(sizeCursor.getColumnIndex(SizesTable.COLUMN_NAME));
            String description = sizeCursor.getString(sizeCursor.getColumnIndex(SizesTable.COLUMN_DESCRIPTION));
            String bonus = sizeCursor.getString(sizeCursor.getColumnIndex(SizesTable.COLUMN_BONUS));

            size = new Size(id, name, description, bonus);
        }
        sizeCursor.close();
        return size;
    }

    /**
     * Get all possible jobs in the database.
     * @return A list of all jobs.
     */
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor jobsCursor = db.query(JobsTable.TABLE_NAME, null, null, null, null, null, null);

        if (jobsCursor.moveToFirst()) {
            while (!jobsCursor.isAfterLast()) {
                long id = jobsCursor.getLong(jobsCursor.getColumnIndex(JobsTable.COLUMN_ID));
                String name = jobsCursor.getString(jobsCursor.getColumnIndex(JobsTable.COLUMN_NAME));
                String bonus = jobsCursor.getString(jobsCursor.getColumnIndex(JobsTable.COLUMN_BONUS));
                String description = jobsCursor.getString(jobsCursor.getColumnIndex(JobsTable.COLUMN_DESCRIPTION));
                jobs.add(new Job(id, name, description, bonus));

                jobsCursor.moveToNext();
            }
        }
        jobsCursor.close();

        return jobs;
    }

    /**
     * Get a job by its ID.
     * @param id The ID to search for.
     * @return The job associated with the given ID.
     */
    public Job getJobById(long id) {
        Job job = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor jobCursor = db.query(JobsTable.TABLE_NAME, null,
                SizesTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);
        if (jobCursor.moveToFirst()) {
            String name = jobCursor.getString(jobCursor.getColumnIndex(SizesTable.COLUMN_NAME));
            String description = jobCursor.getString(jobCursor.getColumnIndex(SizesTable.COLUMN_DESCRIPTION));
            String bonus = jobCursor.getString(jobCursor.getColumnIndex(SizesTable.COLUMN_BONUS));

            job = new Job(id, name, description, bonus);
        }
        jobCursor.close();
        return job;
    }

    /**
     * Get all possible abilities in the database.
     * @return A list of all abilities.
     */
    public List<Ability> getAllAbilities() {
        List<Ability> abilities = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor abilitiesCursor = db.query(AbilitiesTable.TABLE_NAME, null, null, null, null, null, null);

        if (abilitiesCursor.moveToFirst()) {
            while (!abilitiesCursor.isAfterLast()) {
                abilities.add(buildAbility(abilitiesCursor));

                abilitiesCursor.moveToNext();
            }
        }
        abilitiesCursor.close();

        return abilities;
    }

    /**
     * Get all abilities associated with a specified job.
     * @param job The ID of the job for which abilities should be gotten.
     * @return A list of abilities associated with the given job ID.
     */
    public List<Ability> getAbilitiesByJob(int job) {
        List<Ability> abilities = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor abilitiesCursor = db.query(AbilitiesTable.TABLE_NAME, null,
                AbilitiesTable.COLUMN_JOB_ID + " = ?",
                new String[]{String.valueOf(job)},
                null, null, null);

        if (abilitiesCursor.moveToFirst()) {
            while (!abilitiesCursor.isAfterLast()) {
                abilities.add(buildAbility(abilitiesCursor));

                abilitiesCursor.moveToNext();
            }
        }
        abilitiesCursor.close();

        return abilities;
    }

    /**
     * Retrieve an ability from the database based on a given name.
     * @param name The name of the ability to search for.
     * @return The ability associated with the given name.
     */
    public Ability getAbilityByName(String name) {
        Ability ability = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor abilityCursor = db.query(AbilitiesTable.TABLE_NAME, null,
                AbilitiesTable.COLUMN_NAME + " = ?",
                new String[]{name},
                null, null, null);

        if (abilityCursor.moveToFirst()) {
            ability = buildAbility(abilityCursor);
        }

        abilityCursor.close();

        return ability;
    }

    /**
     * Convenience method to generate an ability from the data in a cursor.
     * @param cursor A cursor containing a line of information about an ability.
     * @return An ability object populated with the information from the cursor.
     */
    private Ability buildAbility(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(AbilitiesTable.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(AbilitiesTable.COLUMN_NAME));
        int level = cursor.getInt(cursor.getColumnIndex(AbilitiesTable.COLUMN_LEVEL));
        String quote = cursor.getString(cursor.getColumnIndex(AbilitiesTable.COLUMN_QUOTE));
        String description = cursor.getString(cursor.getColumnIndex(AbilitiesTable.COLUMN_DESCRIPTION));
        int damage = cursor.getInt(cursor.getColumnIndex(AbilitiesTable.COLUMN_DAMAGE));
        int range = cursor.getInt(cursor.getColumnIndex(AbilitiesTable.COLUMN_RANGE));
        int jobId = cursor.getInt(cursor.getColumnIndex(AbilitiesTable.COLUMN_JOB_ID));
        String effects = cursor.getString(cursor.getColumnIndex(AbilitiesTable.COLUMN_EFFECTS));
        String check = cursor.getString(cursor.getColumnIndex(AbilitiesTable.COLUMN_CHECK));

        return new Ability(id, name, quote, description, effects, level, damage, range, jobId, check);
    }

    /**
     * Get a list of all possible items from the database.
     * @return All of the items in the database.
     */
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor itemCursor = db.query(ItemsTable.TABLE_NAME, null, null, null, null, null, null);

        if (itemCursor.moveToFirst()) {
            while (!itemCursor.isAfterLast()) {
                long id = itemCursor.getLong(itemCursor.getColumnIndex(ItemsTable.COLUMN_ID));
                String name = itemCursor.getString(itemCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
                String description = itemCursor.getString(itemCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
                String type = itemCursor.getString(itemCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
                int tier = itemCursor.getInt(itemCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
                int value = itemCursor.getInt(itemCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
                String effect = itemCursor.getString(itemCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));
                items.add(new Item(id, name, description, type, tier, value, effect));

                itemCursor.moveToNext();
            }
        }
        itemCursor.close();

        return items;
    }

    /**
     * Get all possible weapon items from the database.
     * @return A list of all weapon items in the database.
     */
    public List<Weapon> getAllWeapons() {
        List<Weapon> weapons = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor weaponCursor = db.query(ItemsTable.TABLE_NAME, null,
                ItemsTable.COLUMN_TYPE + " LIKE ?",
                new String[]{"weapon%"},
                null, null, null);

        if (weaponCursor.moveToFirst()) {
            while (!weaponCursor.isAfterLast()) {
                long id = weaponCursor.getLong(weaponCursor.getColumnIndex(ItemsTable.COLUMN_ID));
                String name = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
                String description = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
                String type = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
                int tier = weaponCursor.getInt(weaponCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
                int value = weaponCursor.getInt(weaponCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
                String effect = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));
                int range = weaponCursor.getInt(weaponCursor.getColumnIndex(ItemsTable.COLUMN_RANGE));
                weapons.add(new Weapon(id, name, description, type, tier, value, Integer.parseInt(effect.substring(4)), range, effect));

                weaponCursor.moveToNext();
            }
        }
        weaponCursor.close();

        return weapons;
    }

    /**
     * Retrieve a weapon from the database by name.
     * @param weaponName The name of the weapon to search for.
     * @return The weapon associated with the given name.
     */
    public Weapon getWeaponByName(String weaponName) {
        Weapon weapon = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor weaponCursor = db.query(ItemsTable.TABLE_NAME, null,
                ItemsTable.COLUMN_NAME + " = ?",
                new String[]{weaponName},
                null, null, null);

        if (weaponCursor.moveToFirst()) {
            long id = weaponCursor.getLong(weaponCursor.getColumnIndex(ItemsTable.COLUMN_ID));
            String name = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
            String description = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
            String type = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
            int tier = weaponCursor.getInt(weaponCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
            int value = weaponCursor.getInt(weaponCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
            String effect = weaponCursor.getString(weaponCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));
            int range = weaponCursor.getInt(weaponCursor.getColumnIndex(ItemsTable.COLUMN_RANGE));

            weapon = new Weapon(id, name, description, type, tier, value, Integer.parseInt(effect.substring(4)), range, effect);
        }
        weaponCursor.close();

        return weapon;
    }

    /**
     * Get all possible defense items from the database.
     * @return A list of all defense items in the database.
     */
    public List<Defense> getAllDefense() {
        List<Defense> defense = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor defenseCursor = db.query(ItemsTable.TABLE_NAME, null,
                ItemsTable.COLUMN_TYPE + " LIKE ?",
                new String[]{"defense%"},
                null, null, null);

        if (defenseCursor.moveToFirst()) {
            while (!defenseCursor.isAfterLast()) {
                long id = defenseCursor.getLong(defenseCursor.getColumnIndex(ItemsTable.COLUMN_ID));
                String name = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
                String description = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
                String type = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
                int tier = defenseCursor.getInt(defenseCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
                int value = defenseCursor.getInt(defenseCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
                String effect = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));
                defense.add(new Defense(id, name, description, type, tier, value, Integer.parseInt(effect.substring(4)), effect));

                defenseCursor.moveToNext();
            }
        }
        defenseCursor.close();

        return defense;
    }

    /**
     * Retrieve a defense from the database by name.
     * @param defenseName The name of the defense to search for.
     * @return The defense associated with the given name.
     */
    public Defense getDefenseByName(String defenseName) {
        Defense defense = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor defenseCursor = db.query(ItemsTable.TABLE_NAME, null,
                ItemsTable.COLUMN_NAME + " = ?",
                new String[]{defenseName},
                null, null, null);

        if (defenseCursor.moveToFirst()) {
            long id = defenseCursor.getLong(defenseCursor.getColumnIndex(ItemsTable.COLUMN_ID));
            String name = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
            String description = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
            String type = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
            int tier = defenseCursor.getInt(defenseCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
            int value = defenseCursor.getInt(defenseCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
            String effect = defenseCursor.getString(defenseCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));

            defense = new Defense(id, name, description, type, tier, value, Integer.parseInt(effect.substring(4)), effect);
        }
        defenseCursor.close();

        return defense;
    }

    /**
     * Get all possible edible items from the database.
     * @return A list of all edible items in the database.
     */
    public List<Edible> getAllEdibles() {
        List<Edible> edibles = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor edibleCursor = db.query(ItemsTable.TABLE_NAME, null,
                ItemsTable.COLUMN_TYPE + " LIKE ?",
                new String[]{"weapon%"},
                null, null, null);

        if (edibleCursor.moveToFirst()) {
            while (!edibleCursor.isAfterLast()) {
                long id = edibleCursor.getLong(edibleCursor.getColumnIndex(ItemsTable.COLUMN_ID));
                String name = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
                String description = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
                String type = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
                int tier = edibleCursor.getInt(edibleCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
                int value = edibleCursor.getInt(edibleCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
                String effect = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));
                edibles.add(new Edible(id, name, description, type, tier, value, effect));

                edibleCursor.moveToNext();
            }
        }
        edibleCursor.close();

        return edibles;
    }

    /**
     * Retrieve an edible from the database by name.
     * @param edibleName The name of the edible to search for.
     * @return The edible associated with the given name.
     */
    public Edible getEdibleByName(String edibleName) {
        Edible edible = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor edibleCursor = db.query(ItemsTable.TABLE_NAME, null,
                ItemsTable.COLUMN_NAME + " = ?",
                new String[]{edibleName},
                null, null, null);

        if (edibleCursor.moveToFirst()) {
            long id = edibleCursor.getLong(edibleCursor.getColumnIndex(ItemsTable.COLUMN_ID));
            String name = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_NAME));
            String description = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION));
            String type = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_TYPE));
            int tier = edibleCursor.getInt(edibleCursor.getColumnIndex(ItemsTable.COLUMN_TIER));
            int value = edibleCursor.getInt(edibleCursor.getColumnIndex(ItemsTable.COLUMN_VALUE));
            String effect = edibleCursor.getString(edibleCursor.getColumnIndex(ItemsTable.COLUMN_EFFECT));
            edible = new Edible(id, name, description, type, tier, value, effect);
        }
        edibleCursor.close();

        return edible;
    }
}
