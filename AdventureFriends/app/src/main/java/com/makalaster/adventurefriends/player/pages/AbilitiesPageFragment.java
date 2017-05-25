package com.makalaster.adventurefriends.player.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.character.components.Ability;
import com.makalaster.adventurefriends.player.PlayerCharacterHolder;
import com.makalaster.adventurefriends.player.pages.recyclers.AbilityRecyclerAdapter;

import java.util.ArrayList;

/**
 * Fragment to display all of a player's abilities.
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link AbilitiesPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbilitiesPageFragment extends Fragment {


    public AbilitiesPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AbilitiesPageFragment.
     */
    public static AbilitiesPageFragment newInstance() {
        AbilitiesPageFragment fragment = new AbilitiesPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abilities_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayerCharacterHolder playerCharacterHolder = PlayerCharacterHolder.getInstance();
        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.addAll(playerCharacterHolder.getAbilities().values());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.abilities_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new AbilityRecyclerAdapter(abilities));
    }
}
