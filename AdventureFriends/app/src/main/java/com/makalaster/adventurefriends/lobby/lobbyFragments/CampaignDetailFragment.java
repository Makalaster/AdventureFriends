package com.makalaster.adventurefriends.lobby.lobbyFragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.campaign.Campaign;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Display the basic details of a fragment.
 * Pressing the play button will launch the fragment.
 * The selected campaign is loaded into the CampaignHolder.
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link CampaignDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CAMPAIGN_ID = "campaign ID";

    private String mCampaignId;
    private Campaign mCurrentCampaign;
    private TextView mCampaignTitle, mCampaignDescription, mCampaignBaseGame;

    private OnButtonPressedListener mListener;

    public CampaignDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param campaignId Parameter 1.
     * @return A new instance of fragment CampaignDetailFragment.
     */
    public static CampaignDetailFragment newInstance(String campaignId) {
        CampaignDetailFragment fragment = new CampaignDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CAMPAIGN_ID, campaignId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get a reference to the current campaign while it is loading into the campaign holder.
     * Populate remaining views after the campaign has loaded.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCampaignId = getArguments().getString(ARG_CAMPAIGN_ID);
            DatabaseReference currentCampaignReference = FirebaseDatabase.getInstance().getReference("campaigns/" + mCampaignId);

            currentCampaignReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mCurrentCampaign = dataSnapshot.getValue(Campaign.class);
                    setupRemainingViews();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView campaignId = (TextView) view.findViewById(R.id.campaign_id);
        campaignId.setText(mCampaignId);

        // Set up the campaign ID text view to display a context menu so the text can be copied.
        registerForContextMenu(campaignId);
        view.findViewById(R.id.play_existing_campaign_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlayPressed(mCurrentCampaign.getDmId(), mCampaignId);
            }
        });

        mCampaignTitle = (TextView) view.findViewById(R.id.campaign_title);
        mCampaignDescription = (TextView) view.findViewById(R.id.campaign_description);
        mCampaignBaseGame = (TextView) view.findViewById(R.id.campaign_base_game);
    }

    /**
     * Populate the text for the details after the campaign has loaded.
     */
    public void setupRemainingViews() {
        mCampaignTitle.setText(mCurrentCampaign.getCampaignName());
        mCampaignDescription.setText(mCurrentCampaign.getDescription());
        mCampaignBaseGame.setText(mCurrentCampaign.getBaseGame());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, v.getId(), 0, "Copy");

        TextView campaignId = (TextView) v;

        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
        ClipData copyText = ClipData.newPlainText("simple text", campaignId.getText());
        clipboard.setPrimaryClip(copyText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CampaignDetailFragment.OnButtonPressedListener) {
            mListener = (CampaignDetailFragment.OnButtonPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnModuleInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnButtonPressedListener {
        void onPlayPressed(String dmId, String campaignId);
    }
}
