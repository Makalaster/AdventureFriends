package com.makalaster.adventurefriends.dm.dmFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.moduleRecyclerView.ModuleViewHolder;
import com.makalaster.adventurefriends.model.campaign.Campaign;
import com.makalaster.adventurefriends.model.campaign.Module;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnModuleInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModuleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModuleListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_CAMPAIGN_ID = "campaign_id";

    private static final String TAG = "ModuleListFragment";

    private String mCampaignId;
    private DatabaseReference mCurrentCampaignReference;
    private Campaign mCurrentCampaign;

    private OnModuleInteractionListener mListener;

    public ModuleListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param campaignId Parameter 1.
     * @return A new instance of fragment ModuleListFragment.
     */
    public static ModuleListFragment newInstance(String campaignId) {
        ModuleListFragment fragment = new ModuleListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CAMPAIGN_ID, campaignId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCampaignId = getArguments().getString(ARG_CAMPAIGN_ID);
            mCurrentCampaignReference = FirebaseDatabase.getInstance().getReference("campaigns/" + mCampaignId);

            mCurrentCampaignReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mCurrentCampaign = dataSnapshot.getValue(Campaign.class);
                    populateLabels();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void populateLabels() {
        if (getView() != null) {
            View view = getView();

            ((TextView)view.findViewById(R.id.campaign_title)).setText(mCurrentCampaign.getCampaignName());
            ((TextView)view.findViewById(R.id.campaign_id)).setText(mCurrentCampaign.getCampaignId());
            ((TextView)view.findViewById(R.id.campaign_description)).setText(mCurrentCampaign.getDescription());
            ((TextView)view.findViewById(R.id.campaign_base_game)).setText(mCurrentCampaign.getBaseGame());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference modulesReference = mCurrentCampaignReference.child("modules");
        RecyclerView moduleRecyclerView = (RecyclerView) view.findViewById(R.id.module_recycler_view);
        moduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        moduleRecyclerView.setAdapter(new FirebaseRecyclerAdapter<Module, ModuleViewHolder>
                (Module.class, R.layout.layout_module_list_item, ModuleViewHolder.class, modulesReference) {
            @Override
            protected void populateViewHolder(ModuleViewHolder viewHolder, Module model, int position) {
                
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.new_module_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAddNewModule();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnModuleInteractionListener) {
            mListener = (OnModuleInteractionListener) context;
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
    public interface OnModuleInteractionListener {
        void onAddNewModule();
        void onSelectModule();
    }
}
