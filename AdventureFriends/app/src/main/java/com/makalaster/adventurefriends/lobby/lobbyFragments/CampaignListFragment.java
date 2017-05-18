package com.makalaster.adventurefriends.lobby.lobbyFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.lobby.campaignRecyclerView.CampaignListRecyclerViewAdapter;
import com.makalaster.adventurefriends.lobby.campaignRecyclerView.CampaignViewHolder;
import com.makalaster.adventurefriends.model.campaign.Campaign;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCampaignSelectedListener} interface
 * to handle interaction events.
 * Use the {@link CampaignListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignListFragment extends Fragment implements View.OnClickListener{
    private OnCampaignSelectedListener mListener;
    private EditText mCampaignIdEditText;
    private RadioButton mJoinExistingCampaignRadioButton, mCreateNewCampaignRadioButton;

    public CampaignListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CampaignListFragment.
     */
    public static CampaignListFragment newInstance() {
        CampaignListFragment fragment = new CampaignListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign_list, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference campaignList = FirebaseDatabase.getInstance().getReference("campaigns");

        RecyclerView campaignRecycler = (RecyclerView) view.findViewById(R.id.campaign_list_recycler_view);
        campaignRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        campaignRecycler.setAdapter(new FirebaseRecyclerAdapter<Campaign, CampaignViewHolder>(
                Campaign.class, R.layout.layout_campaign_list_item, CampaignViewHolder.class, campaignList) {
                    @Override
                    protected void populateViewHolder(CampaignViewHolder viewHolder, final Campaign model, int position) {
                        viewHolder.mCampaignName.setText(model.getCampaignName());
                        viewHolder.mCharacterName.setText(model.getCharacterName());
                        viewHolder.mCampaignListItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListener.onCampaignSelected(model.getCampaignId());
                            }
                        });
                    }
                }
        );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.new_campaign_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
    }

    public void onButtonPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View chooserView = LayoutInflater.from(getContext()).inflate(R.layout.layout_create_or_join_dialog, null);
        builder.setView(chooserView);

        mCampaignIdEditText = (EditText) chooserView.findViewById(R.id.existing_campaign_edit_text);
        mJoinExistingCampaignRadioButton = (RadioButton) chooserView.findViewById(R.id.join_campaign_radio_button);
        mJoinExistingCampaignRadioButton.setChecked(true);
        mJoinExistingCampaignRadioButton.setOnClickListener(this);
        mCreateNewCampaignRadioButton = (RadioButton) chooserView.findViewById(R.id.create_campaign_radio_button);
        mCreateNewCampaignRadioButton.setOnClickListener(this);

        builder.setTitle("Join or create?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("GO", null);

        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mJoinExistingCampaignRadioButton.isChecked()) {
                    String campaignId = mCampaignIdEditText.getText().toString().trim();
                    if (campaignId.isEmpty()) {
                        mCampaignIdEditText.setError("Please enter a valid campaign ID");
                        mCampaignIdEditText.requestFocus();
                    } else {
                        if (mListener != null) {
                            mListener.onJoinCampaign(campaignId);
                            dialog.dismiss();
                        }
                    }
                } else {
                    if (mListener != null) {
                        mListener.onNewCampaign();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCampaignSelectedListener) {
            mListener = (OnCampaignSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCampaignSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join_campaign_radio_button:
                if (mCreateNewCampaignRadioButton.isChecked()) {
                    mCreateNewCampaignRadioButton.setChecked(false);
                    mCampaignIdEditText.setEnabled(true);
                }
                break;
            case R.id.create_campaign_radio_button:
                if (mJoinExistingCampaignRadioButton.isChecked()) {
                    mJoinExistingCampaignRadioButton.setChecked(false);
                    mCampaignIdEditText.setEnabled(false);
                }
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCampaignSelectedListener {
        void onCampaignSelected(String campaignId);
        void onNewCampaign();
        void onJoinCampaign(String campaignId);
    }
}