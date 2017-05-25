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

import java.util.Locale;

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
            String name = currentCharacter.getName();
            ((TextView) view.findViewById(R.id.character_name))
                    .setText(String.format(Locale.ENGLISH, " %s", name));

            String size = currentCharacter.getSize().getName();
            ((TextView) view.findViewById(R.id.character_size))
                    .setText(String.format(Locale.ENGLISH, " %s", size));

            String job = currentCharacter.getJob().getName();
            ((TextView) view.findViewById(R.id.character_job))
                    .setText(String.format(Locale.ENGLISH, " %s", job));

            int currentPg = currentCharacter.getCurrentPG();
            ((TextView) view.findViewById(R.id.current_pg))
                    .setText(String.format(Locale.ENGLISH, " %d", currentPg));

            int maxPg = currentCharacter.getMaxPG();
            ((TextView) view.findViewById(R.id.max_pg))
                    .setText(String.format(Locale.ENGLISH, "%d ", maxPg));

            int level = currentCharacter.getLevel();
            ((TextView) view.findViewById(R.id.current_level))
                    .setText(String.format(Locale.ENGLISH, " %d,", level));

            int currentXp = currentCharacter.getCurrentXP();
            ((TextView) view.findViewById(R.id.current_xp))
                    .setText(String.format(Locale.ENGLISH, " %d", currentXp));

            int xPToNext = currentCharacter.getXPtoNext();
            ((TextView) view.findViewById(R.id.xp_to_next))
                    .setText(String.format(Locale.ENGLISH, "%d ", xPToNext));

            int body = currentCharacter.getBody();
            ((TextView) view.findViewById(R.id.body_value))
                    .setText(String.format(Locale.ENGLISH, " %d", body));

            int mind = currentCharacter.getMind();
            ((TextView) view.findViewById(R.id.mind_value))
                    .setText(String.format(Locale.ENGLISH, " %d", mind));

            int essence = currentCharacter.getEssence();
            ((TextView) view.findViewById(R.id.essence_value))
                    .setText(String.format(Locale.ENGLISH, " %d", essence));

            int speed = currentCharacter.getSpeed();
            ((TextView) view.findViewById(R.id.speed_value))
                    .setText(String.format(Locale.ENGLISH, " %d", speed));

            int money = currentCharacter.getMoney();
            ((TextView) view.findViewById(R.id.money_value))
                    .setText(String.format(Locale.ENGLISH, " %d", money));
        }
    }
}
