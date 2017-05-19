package com.makalaster.adventurefriends.dm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.dmFragments.ModuleListFragment;
import com.makalaster.adventurefriends.dm.dmFragments.NewModuleFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.MapPageFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.ModulePagerFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.NPCsPageFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.NotesPageFragment;
import com.makalaster.adventurefriends.dm.dmFragments.module.OverviewPageFragment;
import com.makalaster.adventurefriends.lobby.LobbyActivity;
import com.makalaster.adventurefriends.model.campaign.Module;

public class DMActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ModuleListFragment.OnModuleInteractionListener,
        NewModuleFragment.OnNewCampaignCreatedListener,
        OverviewPageFragment.OnLoadModuleListener,
        NPCsPageFragment.OnAddNPCListener,
        NotesPageFragment.NoteListener,
        MapPageFragment.OnFragmentInteractionListener {

    private FirebaseAuth mAuth;
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
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //TODO display user account info in nav drawer

        mCampaignId = getIntent().getStringExtra(ModuleListFragment.ARG_CAMPAIGN_ID);
        loadModuleList(mCampaignId);
    }

    private void loadModuleList(String id) {
        ModuleListFragment moduleListFragment = ModuleListFragment.newInstance(id);
        mFragmentManager.beginTransaction()
                //.addToBackStack(null)
                .replace(R.id.dm_fragment_container, moduleListFragment, "module_list")
                .commit();
    }

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
                default:
                    mFragmentManager.popBackStack();
                    loadModuleList(mCampaignId);
            }
        }
    }

    private void confirmAndExit() {
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

    @Override
    public void onAddNewModule() {
        Fragment newModuleFragment = NewModuleFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, newModuleFragment, "new_module")
                .commit();
    }

    @Override
    public void onModuleSelected(String moduleId) {
        Fragment pagerFragment = ModulePagerFragment.newInstance(moduleId);
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dm_fragment_container, pagerFragment, "module_detail")
                .commit();
    }

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

    @Override
    public void onLaunchModule(String moduleId) {

    }

    @Override
    public void onCompleteModule(String moduleId) {

    }

    @Override
    public void onAddNPC() {

    }

    @Override
    public void onSelectNPC() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAddNote() {

    }

    @Override
    public void onSelectNote(String noteId) {

    }
}
