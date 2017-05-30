package com.makalaster.adventurefriends.lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.LoginActivity;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.UserHolder;
import com.makalaster.adventurefriends.dm.CampaignHolder;
import com.makalaster.adventurefriends.dm.DMActivity;
import com.makalaster.adventurefriends.dm.dmFragments.ModuleListFragment;
import com.makalaster.adventurefriends.lobby.lobbyFragments.CampaignDetailFragment;
import com.makalaster.adventurefriends.lobby.lobbyFragments.CampaignListFragment;
import com.makalaster.adventurefriends.lobby.lobbyFragments.NewCampaignFragment;
import com.makalaster.adventurefriends.model.User;
import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.player.PlayerActivity;

/**
 * Activity to create and display a user's campaigns.
 */

public class LobbyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    CampaignListFragment.OnCampaignSelectedListener,
                    NewCampaignFragment.OnCreateNewCampaignListener,
                    CampaignDetailFragment.OnButtonPressedListener {

    private UserHolder mUserHolder;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUserHolder = UserHolder.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);

        User currentUser = mUserHolder.getCurrentUser();
        TextView drawerEmail = (TextView) hView.findViewById(R.id.user_email);
        drawerEmail.setText(currentUser.getEmail());
        TextView drawerUser = (TextView) hView.findViewById(R.id.user_name);
        drawerUser.setText(currentUser.getName());

        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        loadCampaignFragment();
    }

    /**
     * Load the fragment containing a list of the user's campaigns.
     */
    private void loadCampaignFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        CampaignListFragment campaignListFragment = CampaignListFragment.newInstance();
        transaction.add(R.id.lobby_fragment_container, campaignListFragment).commit();
    }

    /**
     * Appropriately handle the back button being pressed. Should not return to the login activity.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            CampaignHolder.getInstance().clearCampaign();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lobby, menu);
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

        } else if (id == R.id.nav_log_out) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            mUserHolder.clearCurrentUser();
                            startActivity(new Intent(LobbyActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Handles selecting a campaign. Launches the campaign detail fragment.
     * @param campaignId The ID of the campaign to be viewed.
     * @param dmId The ID of the DM for the campaign.
     */
    @Override
    public void onCampaignSelected(String campaignId, String dmId) {
        CampaignHolder.getInstance().loadCampaign(campaignId);

        Fragment campaignDetailFragment = CampaignDetailFragment.newInstance(campaignId);
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.lobby_fragment_container, campaignDetailFragment, "detail_fragment")
                .commit();
    }

    /**
     * Handles the choice of creating a new campaign. Launches the new campaign fragment.
     */
    @Override
    public void onNewCampaign() {
        Fragment newCampaignFragment = NewCampaignFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.lobby_fragment_container, newCampaignFragment)
                .commit();
    }

    /**
     * Handles the choice of joining an existing campaign. Launches the player activity.
     * @param campaignId The ID of the campaign to join.
     */
    @Override
    public void onJoinCampaign(String campaignId) {
        CampaignHolder holder = CampaignHolder.getInstance();
        holder.loadCampaign(campaignId);

        Intent playerIntent = new Intent(this, PlayerActivity.class);
        playerIntent.putExtra(ModuleListFragment.ARG_CAMPAIGN_ID, campaignId);
        startActivity(playerIntent);
        finish();
    }

    /**
     * Handles the creation of a new campaign. Launches the DM activity.
     * @param title The title of the new campaign.
     * @param description The description of the new campaign.
     * @param baseGame The base game of the new campaign.
     */
    @Override
    public void onCreateNewCampaign(String title, String description, String baseGame) {
        if (!baseGame.equals("Goblins? Goblins!")) {
            Toast.makeText(this, baseGame + " support coming soon!", Toast.LENGTH_SHORT).show();
        } else {
            User user = mUserHolder.getCurrentUser();
            String dmId = null;
            if (user != null) {
                dmId = user.getId();
            }

            DatabaseReference userReference = FirebaseDatabase.getInstance()
                    .getReference("users/" + user.getId() + "/campaigns");

            DatabaseReference campaignReference = FirebaseDatabase.getInstance().getReference("campaigns");
            DatabaseReference newCampaign = campaignReference.push();
            String id = newCampaign.getKey();

            Campaign newLocalCampaign = new Campaign(id, title, baseGame, dmId, description);
            CampaignHolder.getInstance().loadCampaign(id);
            newCampaign.setValue(newLocalCampaign);

            userReference.child(id).setValue(newLocalCampaign);

            Intent dmIntent = new Intent(this, DMActivity.class);
            dmIntent.putExtra(ModuleListFragment.ARG_CAMPAIGN_ID, id);
            startActivity(dmIntent);
            finish();
        }
    }

    /**
     * Handles pressing play in the detail view of a campaign. If the current user is the DM, the DM
     * activity is launched. Otherwise, the player activity is launched.
     * @param dmId The ID of the DM for the given campaign.
     * @param campaignId The ID of the given campaign.
     */
    @Override
    public void onPlayPressed(String dmId, String campaignId) {
        if (mUserHolder.getCurrentUser().getId().equals(dmId)) {
            Intent dmIntent = new Intent(this, DMActivity.class);
            dmIntent.putExtra(ModuleListFragment.ARG_CAMPAIGN_ID, campaignId);

            startActivity(dmIntent);
            finish();
        } else {
            Intent playerIntent = new Intent(this, PlayerActivity.class);
            playerIntent.putExtra(ModuleListFragment.ARG_CAMPAIGN_ID, campaignId);
            CampaignHolder.getInstance().loadCampaign(campaignId);
            startActivity(playerIntent);
            finish();
        }
    }

}
