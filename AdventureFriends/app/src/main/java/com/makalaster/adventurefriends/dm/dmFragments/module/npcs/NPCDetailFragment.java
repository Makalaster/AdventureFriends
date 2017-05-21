package com.makalaster.adventurefriends.dm.dmFragments.module.npcs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.dmFragments.module.ModuleHolder;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NPCDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NPCDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NPC_ID = "param1";

    private String mNpcId;

    public NPCDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param npcId Parameter 1.
     * @return A new instance of fragment NPCDetailFragment.
     */
    public static NPCDetailFragment newInstance(String npcId) {
        NPCDetailFragment fragment = new NPCDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NPC_ID, npcId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNpcId = getArguments().getString(ARG_NPC_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_npcdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NonPlayerCharacter npc = ModuleHolder.getInstance().getNPCById(mNpcId);
        ((TextView) view.findViewById(R.id.npc_name)).setText(npc.getName());
        ((TextView) view.findViewById(R.id.current_pg)).setText(String.valueOf(npc.getCurrentPG()));
        ((TextView) view.findViewById(R.id.max_pg)).setText(String.valueOf(npc.getMaxPG()));
        ((TextView) view.findViewById(R.id.body)).setText(String.valueOf(npc.getBody()));
        ((TextView) view.findViewById(R.id.mind)).setText(String.valueOf(npc.getMind()));
        ((TextView) view.findViewById(R.id.essence)).setText(String.valueOf(npc.getEssence()));
        ((TextView) view.findViewById(R.id.npc_level)).setText(String.valueOf(npc.getLevel()));
        ((TextView) view.findViewById(R.id.npc_money)).setText(String.valueOf(npc.getMoney()));
    }

    //TODO maintain npc (equipment, abilities...)
    //TODO npc holder singleton?
}
