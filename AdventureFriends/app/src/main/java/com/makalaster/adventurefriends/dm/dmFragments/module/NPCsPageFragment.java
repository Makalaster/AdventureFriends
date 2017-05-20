package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.CampaignHolder;
import com.makalaster.adventurefriends.dm.dmFragments.module.moduleItemRecyclerView.ItemHolder;
import com.makalaster.adventurefriends.model.character.NPC;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAddNPCListener} interface
 * to handle interaction events.
 * Use the {@link NPCsPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NPCsPageFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MODULE_ID = "module_id";

    private String mModuleId;

    private OnAddNPCListener mListener;

    public NPCsPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param moduleId Parameter 1.
     * @return A new instance of fragment NPCsPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NPCsPageFragment newInstance(String moduleId) {
        NPCsPageFragment fragment = new NPCsPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MODULE_ID, moduleId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mModuleId = getArguments().getString(ARG_MODULE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_npcs_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String campaignId = CampaignHolder.getInstance().getCampaign().getCampaignId();
        DatabaseReference npcs = FirebaseDatabase.getInstance().getReference(
                "campaigns/" + campaignId + "/modules/" + mModuleId + "/npcs");

        FloatingActionButton newNPCFab = (FloatingActionButton) view.findViewById(R.id.new_npc_fab);
        newNPCFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddNPC();
            }
        });

        RecyclerView npcRecycler = (RecyclerView) view.findViewById(R.id.npc_recycler_view);
        npcRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        npcRecycler.setAdapter(new FirebaseRecyclerAdapter<NPC, ItemHolder>(NPC.class, R.layout.layout_module_item, ItemHolder.class, npcs) {
            @Override
            protected void populateViewHolder(ItemHolder viewHolder, final NPC model, int position) {
                viewHolder.mName.setText(model.getName());
                viewHolder.mItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onSelectNPC(model.getId());
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddNPCListener) {
            mListener = (OnAddNPCListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoadModuleListener");
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
    public interface OnAddNPCListener {
        void onAddNPC();
        void onSelectNPC(String id);
    }
}
