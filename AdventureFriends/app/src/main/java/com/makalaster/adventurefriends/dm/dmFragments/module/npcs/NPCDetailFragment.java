package com.makalaster.adventurefriends.dm.dmFragments.module.npcs;

import android.content.Context;
import android.content.res.Resources;
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

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NPCDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NPCDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NPC_ID = "npc_id";

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

        int currentPreciousGoo = npc.getCurrentPG(), maxPreciousGoo = npc.getMaxPG();
        ((TextView) view.findViewById(R.id.health))
                .setText(String.format(Locale.ENGLISH, "%d %s %d %s",
                        currentPreciousGoo, getString(R.string.slash),
                        maxPreciousGoo, getString(R.string.precious_goo)));

        int body = npc.getBody(), mind = npc.getMind(), essence = npc.getEssence();
        ((TextView) view.findViewById(R.id.stats))
                .setText(String.format(Locale.ENGLISH, "%s %d %s %d %s %d",
                        getString(R.string.body_label), body,
                        getString(R.string.mind_label), mind,
                        getString(R.string.essence_label), essence));

        int level = npc.getLevel(), money = npc.getMoney();
        ((TextView) view.findViewById(R.id.level_and_money))
                .setText(String.format(Locale.ENGLISH, "%s %d %s %d %s",
                        getString(R.string.level_label), level,
                        getString(R.string.money_label), money,
                        getString(R.string.pearls)));
    }

    //TODO maintain npc (equipment, abilities...)
    //TODO npc holder singleton?
}
