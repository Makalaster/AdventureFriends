package com.makalaster.adventurefriends.player;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
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
import com.makalaster.adventurefriends.dm.CampaignHolder;
import com.makalaster.adventurefriends.dm.dmFragments.ModuleListFragment;
import com.makalaster.adventurefriends.lobby.LobbyActivity;
import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;
import com.makalaster.adventurefriends.player.pages.AbilitiesPageFragment;
import com.makalaster.adventurefriends.player.pages.EquipmentPageFragment;
import com.makalaster.adventurefriends.player.pages.InventoryPageFragment;
import com.makalaster.adventurefriends.player.pages.MapPageFragment;
import com.makalaster.adventurefriends.player.pages.NotesPageFragment;
import com.makalaster.adventurefriends.player.pages.StatsPageFragment;

public class PlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AbilitiesPageFragment.OnFragmentInteractionListener,
        EquipmentPageFragment.OnFragmentInteractionListener,
        InventoryPageFragment.OnFragmentInteractionListener,
        MapPageFragment.OnFragmentInteractionListener,
        NotesPageFragment.OnFragmentInteractionListener,
        StatsPageFragment.OnFragmentInteractionListener,
        NewCharacterFragment.OnPlayerCharacterCreatedListener {

    private FragmentManager mFragmentManager;
    private String mCurrentCampaignId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
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

        Bundle args = getIntent().getExtras();
        mCurrentCampaignId = args.getString(ModuleListFragment.ARG_CAMPAIGN_ID);
        boolean userAlreadyInCampaign = args.getBoolean("user_exists", true);

        if (userAlreadyInCampaign) {
            displayPager();
        } else {
            displayNewCharacter();
        }
    }

    public void displayPager() {
        Fragment pagerFragment = PlayerPagerFragment.newInstance("", "");
        mFragmentManager.beginTransaction()
                .replace(R.id.player_fragment_container, pagerFragment, "pager")
                .commit();
    }

    public void displayNewCharacter() {
        Fragment newPlayerFragment = NewCharacterFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.player_fragment_container, newPlayerFragment, "new_player")
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            confirmAndExit();
        }
    }

    private void confirmAndExit() {
        //TODO make confirmation
        Intent returnToLobby = new Intent(this, LobbyActivity.class);
        startActivity(returnToLobby);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player, menu);
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCharacterCreated(String name, Size size, Job job) {
        Campaign currentCampaign = CampaignHolder.getInstance().getCampaign();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference player = database.getReference("users/" + userId);
        DatabaseReference playerCharacters = player.child("characters");
        DatabaseReference newCharacter = playerCharacters.push();
        String key = newCharacter.getKey();
        PlayerCharacter newLocalCharacter = new PlayerCharacter(name, key, size, job, userId);
        newCharacter.setValue(newLocalCharacter);

        DatabaseReference playerCampaigns = player.child("campaigns");
        playerCampaigns.child(mCurrentCampaignId).setValue(currentCampaign);

        DatabaseReference campaignCharacters = database.getReference("campaigns/" + mCurrentCampaignId + "/players");
        campaignCharacters.child(userId).setValue(newLocalCharacter);

        mFragmentManager.popBackStack();
        displayPager();
    }
}
