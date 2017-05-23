package com.makalaster.adventurefriends.player.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.player.PlayerCharacterHolder;

/**
 * A simple {@link Fragment} subclass.

 * Use the {@link StatsPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsPageFragment extends Fragment {
    private PlayerCharacterHolder mPlayerCharacterHolder;

    public StatsPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StatsPageFragment.
     */
    public static StatsPageFragment newInstance() {
        StatsPageFragment fragment = new StatsPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayerCharacterHolder = PlayerCharacterHolder.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayerCharacter currentCharacter = mPlayerCharacterHolder.getPlayerCharacter();
        if (currentCharacter != null) {
            ((TextView) view.findViewById(R.id.character_name)).setText(currentCharacter.getName());
            ((TextView) view.findViewById(R.id.character_size)).setText(currentCharacter.getSize().getName());
            ((TextView) view.findViewById(R.id.character_job)).setText(currentCharacter.getJob().getName());
            ((TextView) view.findViewById(R.id.current_pg)).setText(String.valueOf(currentCharacter.getCurrentPG()));
            ((TextView) view.findViewById(R.id.max_pg)).setText(String.valueOf(currentCharacter.getMaxPG()));
            ((TextView) view.findViewById(R.id.current_xp)).setText(String.valueOf(currentCharacter.getCurrentXP()));
            ((TextView) view.findViewById(R.id.xp_to_next)).setText(String.valueOf(currentCharacter.getXPtoNext()));
            ((TextView) view.findViewById(R.id.body_value)).setText(String.valueOf(currentCharacter.getBody()));
            ((TextView) view.findViewById(R.id.mind_value)).setText(String.valueOf(currentCharacter.getMind()));
            ((TextView) view.findViewById(R.id.essence_value)).setText(String.valueOf(currentCharacter.getEssence()));
            ((TextView) view.findViewById(R.id.speed_value)).setText(String.valueOf(currentCharacter.getSpeed()));
            ((TextView) view.findViewById(R.id.money_value)).setText(String.valueOf(currentCharacter.getMoney()));
        }
    }
}
