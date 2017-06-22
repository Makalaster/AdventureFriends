package com.makalaster.adventurefriends.dm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.dmFragments.ModuleListFragment;
import com.makalaster.adventurefriends.dm.dmFragments.NewModuleFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.map.MapPageFragment;
import com.makalaster.adventurefriends.model.campaign.CampaignHolder;
import com.makalaster.adventurefriends.model.campaign.ModuleHolder;
import com.makalaster.adventurefriends.dm.dmFragments.module.ModulePagerFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.NPCsPageFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.NotesPageFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.OverviewPageFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.notes.NewNoteFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.notes.NoteDetailFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.npcs.NPCDetailFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.npcs.NewNPCFragment;
import com.makalaster.adventurefriends.lobby.LobbyActivity;
import com.makalaster.adventurefriends.model.Note;
import com.makalaster.adventurefriends.model.campaign.Module;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.model.map.Map;

/**
 * Activity that a user will launch when a user creates a new campaign or launches a campaign for
 * which they are the DM.
 */

public class DMActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ModuleListFragment.OnModuleInteractionListener,
        NewModuleFragment.OnNewModuleCreatedListener,
        OverviewPageFragment.OnLoadModuleListener,
        NPCsPageFragment.OnAddNPCListener,
        NewNPCFragment.CreateNPCListener,
        NotesPageFragment.NoteListener,
        MapPageFragment.OnMapInteractionListener,
        NewNoteFragment.OnCreateNoteListener,
        NoteDetailFragment.OnNoteSavedListener {

    private FragmentManager mFragmentManager;
    private String mCampaignId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //TODO display user account info in nav drawer

        mCampaignId = getIntent().getStringExtra(ModuleListFragment.ARG_CAMPAIGN_ID);
        loadModuleList(mCampaignId);
    }

    //TODO load correct module/page on rotate
    /**
     * Load the list of modules for a campaign.
     * @param id The ID of the current campaign.
     */
    private void loadModuleList(String id) {
        ModuleListFragment moduleListFragment = ModuleListFragment.newInstance(id);
        mFragmentManager.beginTransaction()
                .replace(R.id.dm_fragment_container, moduleListFragment, "module_list")
                .commit();
    }

    /**
     * Appropriately handle presses of the back button. Should not close activity while
     * fragments are displayed.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            switch (mFragmentManager.findFragmentById(R.id.dm_fragment_container).getTag()) {
                case "module_list":
                    super.onBackPressed();
                    CampaignHolder.getInstance().clearCampaign();
                    confirmAndExit();
                    break;
                case "new_note":
                    mFragmentManager.popBackStack();
                    break;
                case "note_detail":
                    mFragmentManager.popBackStack();
                    break;
                case "new_npc":
                    mFragmentManager.popBackStack();
                    break;
                case "npc_detail":
                    mFragmentManager.popBackStack();
                    break;
                default:
                    ModuleHolder.getInstance().clearModule();
                    mFragmentManager.popBackStack();
                    loadModuleList(mCampaignId);
            }
        }
    }

    /**
     * Return to the lobby activity and finish this activity.
     */
    private void confirmAndExit() {
        //TODO make confirmation
        Intent returnToLobby = new Intent(this, LobbyActivity.class);
        startActivity(returnToLobby);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Handles what happens when new module FAB is pressed.
     * Loads fragment to create a new module.
     */
    @Override
    public void onAddNewModule() {
        Fragment newModuleFragment = NewModuleFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, newModuleFragment, "new_module")
                .commit();
    }

    /**
     * Handles a module being selected. Loads fragment containing module detail viewpager.
     * @param moduleId The ID of the module to be loaded.
     */
    @Override
    public void onModuleSelected(String moduleId) {
        ModuleHolder.getInstance().loadModule(mCampaignId, moduleId);
        Fragment pagerFragment = ModulePagerFragment.newInstance(moduleId);
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, pagerFragment, "module_detail")
                .commit();
    }

    /**
     * Handles the creation of a new module. Pops the new module fragment off the back stack.
     * Pushes a new node into the FireBase database, then uses that as the new module ID.
     * @param title The title of the new module.
     * @param summary The summary of the new module.
     * @param type The type of the new module.
     */
    @Override
    public void onNewModuleCreated(String title, String summary, int type) {
        DatabaseReference currentCampaignReference = FirebaseDatabase.getInstance().getReference("campaigns/" + mCampaignId);
        DatabaseReference newModule = currentCampaignReference.child("modules").push();
        String id = newModule.getKey();
        Module newLocalModule = new Module(id, title, summary, type);
        newModule.setValue(newLocalModule);
        CampaignHolder.getInstance().addModule(id, newLocalModule);

        mFragmentManager.popBackStack();
        loadModuleList(mCampaignId);
    }

    /**
     * Handles a module being launched. Sets the current map to the module's map.
     * @param moduleId The ID of the module being launched.
     */
    @Override
    public void onLaunchModule(String moduleId) {
        CampaignHolder campaignHolder = CampaignHolder.getInstance();
        campaignHolder.setCurrentMap(ModuleHolder.getInstance().getMap());

        DatabaseReference currentMapReference = FirebaseDatabase.getInstance()
                .getReference("campaigns/" + mCampaignId + "/currentMap");
        currentMapReference.setValue(ModuleHolder.getInstance().getMap());
    }

    /**
     * Handles a module being completed. Clears the current map.
     * @param moduleId The ID of the module being completed.
     */
    @Override
    public void onCompleteModule(String moduleId) {
        CampaignHolder campaignHolder = CampaignHolder.getInstance();

        Map clearedMap = ModuleHolder.getInstance().getMap();
        clearedMap.clearTiles();

        campaignHolder.setCurrentMap(clearedMap);

        DatabaseReference currentMapReference = FirebaseDatabase.getInstance().getReference("campaigns/" + mCampaignId + "/currentMap");
        currentMapReference.setValue(clearedMap);
    }

    /**
     * Handles the map being saved by the DM in a module.
     * @param map The new state of the map for the module.
     */
    @Override
    public void onMapSaved(Map map) {
        Module module = ModuleHolder.getInstance().getModule();
        module.setMap(map);

        DatabaseReference mapReference = FirebaseDatabase.getInstance()
                .getReference("campaigns/" + mCampaignId + "/modules/" + module.getId() + "/map");

        mapReference.setValue(map);
    }

    /**
     * Handles presses of the new NPC fab. Launches the new NPC fragment.
     */
    @Override
    public void onAddNPC() {
        Fragment newNPCFragment = NewNPCFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, newNPCFragment, "new_npc")
                .commit();
    }

    /**
     * Handles a new NPC being created. Pops the new NPC fragment off the back stack.
     * @param name The name of the new NPC.
     * @param level The level of the new NPC.
     * @param money The amount of money help by the new NPC.
     * @param size The size of the new NPC.
     * @param job The job of the new NPC.
     */
    @Override
    public void onCreateNPC(String name, int level, int money, Size size, Job job) {
        Module module = ModuleHolder.getInstance().getModule();

        DatabaseReference currentModuleReference = FirebaseDatabase.getInstance().getReference("campaigns/" + mCampaignId + "/modules/" + module.getId());
        DatabaseReference newNpc = currentModuleReference.child("npcs").push();
        String id = newNpc.getKey();
        NonPlayerCharacter newLocalNonPlayerCharacter = new NonPlayerCharacter(name, id, level, size, job, money);
        newNpc.setValue(newLocalNonPlayerCharacter);
        ModuleHolder.getInstance().addNPC(id, newLocalNonPlayerCharacter);

        mFragmentManager.popBackStack();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * Handles the selection of an NPC. Launches the NPC detail fragment.
     * @param id the ID of the selected NPC.
     */
    @Override
    public void onSelectNPC(String id) {
        Fragment npcDetailFragment = NPCDetailFragment.newInstance(id);
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, npcDetailFragment, "npc_detail")
                .commit();
    }

    /**
     * Handles pressing the new note FAB. Launches the new note fragment.
     */
    @Override
    public void onAddNote() {
        Fragment newNoteFragment = NewNoteFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, newNoteFragment, "new_note")
                .commit();
    }

    /**
     * Handles selecting a note. Launches the note detail fragment.
     * @param noteId
     */
    @Override
    public void onSelectNote(String noteId) {
        Fragment noteDetailFragment = NoteDetailFragment.newInstance(noteId);
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, noteDetailFragment, "note_detail")
                .commit();
    }

    /**
     * Handles creating a new note. Pops the new note fragment off the back stack.
     * @param title The title of the new note.
     * @param body The body of the new note.
     */
    @Override
    public void onCreateNote(String title, String body) {
        Module module = ModuleHolder.getInstance().getModule();

        DatabaseReference currentModuleReference = FirebaseDatabase.getInstance().getReference("campaigns/" + mCampaignId + "/modules/" + module.getId());
        DatabaseReference newNote = currentModuleReference.child("notes").push();
        String id = newNote.getKey();
        Note newLocalNote = new Note(id, title, body);
        newNote.setValue(newLocalNote);
        ModuleHolder.getInstance().addNote(id, newLocalNote);

        mFragmentManager.popBackStack();
    }

    /**
     * Handles updates to an existing note.
     * @param noteId The ID of the note being updated.
     * @param newTitle The updated title of the note.
     * @param newBody The updated body of the note.
     */
    @Override
    public void onSaveNote(String noteId, String newTitle, String newBody) {
        ModuleHolder moduleHolder = ModuleHolder.getInstance();
        String moduleId = moduleHolder.getModule().getId();
        Note note = moduleHolder.getNoteById(noteId);
        note.setTitle(newTitle);
        note.setBody(newBody);

        DatabaseReference noteToUpdate = FirebaseDatabase.getInstance().getReference(
                "campaigns/" + mCampaignId + "/modules/" + moduleId + "/notes/" + noteId);
        noteToUpdate.setValue(note);
    }
}
