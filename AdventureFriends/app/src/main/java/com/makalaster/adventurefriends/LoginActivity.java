package com.makalaster.adventurefriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.baseGames.DBAssetHelper;
import com.makalaster.adventurefriends.lobby.LobbyActivity;
import com.makalaster.adventurefriends.model.User;

import java.util.Arrays;

/**
 * Activity to handle logging in through FireBase. Uses FireBaseUI to handle authentication through
 * various services including Google, Facebook, Twitter, and email/password authentication.
 * If a user is already logged in, they are immediately sent to the lobby activity. If not, they are
 * presented with sign-in provider options. Once they are signed in, they are sent to the lobby activity.
 */
public class LoginActivity extends AppCompatActivity implements UserHolder.UserLoadedListener {
    private static final String TAG = "LoginActivity";
    private static final int AF_SIGN_IN = 1;

    private FirebaseAuth mAuth;
    private UserHolder mUserHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBAssetHelper dbSetup = new DBAssetHelper(this);
        dbSetup.getReadableDatabase();

        mUserHolder = UserHolder.getInstance();
        mUserHolder.setListener(this);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            mUserHolder.LoadCurrentUser(mAuth.getCurrentUser().getUid());
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                                new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                                new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()))
                    .setIsSmartLockEnabled(false)
                    .build(),
                    AF_SIGN_IN
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AF_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == ResultCodes.OK) {
                checkForUserInDatabaseAndAddIfNotPresent();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.d(TAG, "onActivityResult: back pressed");
                } else if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.d(TAG, "onActivityResult: no network");
                } else if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.d(TAG, "onActivityResult: unknown error");
                }
            }
        }
    }

    /**
     * Check to see whether a user is already present in the database. If not, the user is added.
     */
    private void checkForUserInDatabaseAndAddIfNotPresent() {
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("users/");
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            DatabaseReference child = database.child(currentUser.getUid());
            child.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        User newUser = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail());
                        database.child(currentUser.getUid()).setValue(newUser);
                    }
                    mUserHolder.LoadCurrentUser(currentUser.getUid());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException();
                }
            });
        }
    }

    private void goToLobby() {
        startActivity(new Intent(this, LobbyActivity.class));
        finish();
    }

    @Override
    public void onUserLoaded() {
        goToLobby();
    }
}
