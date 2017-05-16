package com.makalaster.adventurefriends.lobby;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.lobby.campaignRecyclerView.CampaignListRecyclerViewAdapter;
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
    // TODO: Rename and change types and number of parameters
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign("campaign1", "Campaign 1", "Drax"));
        campaigns.add(new Campaign("campaign2", "Campaign 2", "Groot"));
        campaigns.add(new Campaign("campaign3", "Campaign 3", "Mantis"));

        RecyclerView campaignRecycler = (RecyclerView) view.findViewById(R.id.campaign_list_recycler_view);
        campaignRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        campaignRecycler.setAdapter(new CampaignListRecyclerViewAdapter(campaigns, mListener));

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
        mCampaignIdEditText.setOnClickListener(this);
        mJoinExistingCampaignRadioButton = (RadioButton) chooserView.findViewById(R.id.join_campaign_radio_button);
        mJoinExistingCampaignRadioButton.setChecked(true);
        mJoinExistingCampaignRadioButton.setOnClickListener(this);
        mCreateNewCampaignRadioButton = (RadioButton) chooserView.findViewById(R.id.create_campaign_radio_button);
        mCreateNewCampaignRadioButton.setOnClickListener(this);

        builder.setTitle("Create or join?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("GO", null);

        AlertDialog dialog = builder.create();
        dialog.show();

        if (mListener != null) {
            mListener.onNewCampaign();
        }
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
            case R.id.existing_campaign_edit_text:
                if (mCreateNewCampaignRadioButton.isChecked()) {
                    mCreateNewCampaignRadioButton.setChecked(false);
                    mJoinExistingCampaignRadioButton.setChecked(true);
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
        Campaign onNewCampaign();
        void onJoinCampaign(String campaignId);
    }
}
