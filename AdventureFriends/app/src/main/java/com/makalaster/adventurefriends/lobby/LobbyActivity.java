package com.makalaster.adventurefriends.lobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.makalaster.adventurefriends.LoginActivity;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.DMActivity;

public class LobbyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    CampaignListFragment.OnCampaignSelectedListener,
                    NewCampaignFragment.OnCreateNewCampaignListener {
    private static final String TAG = "LobbyActivity";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";

    private FirebaseAuth mAuth;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);

        if (mAuth != null) {
            if (mAuth.getCurrentUser() != null) {
                TextView drawerEmail = (TextView) hView.findViewById(R.id.user_email);
                drawerEmail.setText(mAuth.getCurrentUser().getEmail());
                TextView drawerUser = (TextView) hView.findViewById(R.id.user_name);
                drawerUser.setText(mAuth.getCurrentUser().getDisplayName());
            }
        }

        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        loadCampaignFragment();
    }

    private void loadCampaignFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        CampaignListFragment campaignListFragment = CampaignListFragment.newInstance();
        transaction.add(R.id.lobby_fragment_container, campaignListFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
                            startActivity(new Intent(LobbyActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCampaignSelected(String campaignId) {
        Fragment campaignDetailFragment = CampaignDetailFragment.newInstance(campaignId);
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.lobby_fragment_container, campaignDetailFragment)
                .commit();
    }

    @Override
    public void onNewCampaign() {
        Fragment newCampaignFragment = NewCampaignFragment.newInstance();
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.lobby_fragment_container, newCampaignFragment)
                .commit();
    }

    @Override
    public void onJoinCampaign(String campaignId) {
        Toast.makeText(this, "Joining campaign " + campaignId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateNewCampaign(String title, String description, String baseGame) {
        if (!baseGame.equals("Dungeons & Dragons")) {
            Toast.makeText(this, "Please choose Dungeons & Dragons. Nothing else is available yet.", Toast.LENGTH_SHORT).show();
        } else {
            Intent dmIntent = new Intent(this, DMActivity.class);
            dmIntent.putExtra("title", title);
            dmIntent.putExtra("description", description);
            dmIntent.putExtra("base_game", baseGame);
            startActivity(dmIntent);
            finish();
        }
    }
}
