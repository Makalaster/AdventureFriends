package com.makalaster.adventurefriends.lobby.lobbyFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.makalaster.adventurefriends.R;

/**
 * User interface for creating a new campaign.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCreateNewCampaignListener} interface
 * to handle interaction events.
 * Use the {@link NewCampaignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCampaignFragment extends Fragment {

    private OnCreateNewCampaignListener mListener;
    private EditText mNewCampaignTitle, mNewCampaignDescription;
    private Spinner mBaseCampaignSpinner;

    public NewCampaignFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewCampaignFragment.
     */
    public static NewCampaignFragment newInstance() {
        NewCampaignFragment fragment = new NewCampaignFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_campaign, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewCampaignTitle = (EditText) view.findViewById(R.id.new_campaign_name_edit_text);
        mNewCampaignDescription = (EditText) view.findViewById(R.id.new_campaign_description_edit_text);
        mBaseCampaignSpinner = (Spinner) view.findViewById(R.id.base_game_spinner);
        Button createCampaignButton = (Button) view.findViewById(R.id.start_campaign_button);

        createCampaignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });
    }

    /**
     * Validate information provided by the user and create a new campaign.
     */
    public void onButtonPressed() {
        String title = mNewCampaignTitle.getText().toString().trim();
        String description = mNewCampaignDescription.getText().toString().trim();
        if (title.isEmpty()) {
            mNewCampaignTitle.setError(getString(R.string.campaign_title_error));
            mNewCampaignTitle.requestFocus();
        } else if (mBaseCampaignSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), R.string.campaign_spinner_error, Toast.LENGTH_SHORT).show();
            mBaseCampaignSpinner.requestFocus();
        } else {
            if (mListener != null) {
                mListener.onCreateNewCampaign(title, description, String.valueOf(mBaseCampaignSpinner.getSelectedItem()));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateNewCampaignListener) {
            mListener = (OnCreateNewCampaignListener) context;
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
    public interface OnCreateNewCampaignListener {
        void onCreateNewCampaign(String title, String description, String baseGame);
    }
}
