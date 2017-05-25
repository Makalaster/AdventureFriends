package com.makalaster.adventurefriends.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;

/**
 * Holds the viewpager for a player's detail pages.
 *
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link PlayerPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerPagerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public PlayerPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlayerPagerFragment.
     */
    public static PlayerPagerFragment newInstance() {
        PlayerPagerFragment fragment = new PlayerPagerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager playerPager = (ViewPager) view.findViewById(R.id.player_view_pager);
        PlayerPagerAdapter playerPagerAdapter = new PlayerPagerAdapter(getChildFragmentManager());
        playerPager.setAdapter(playerPagerAdapter);

        TabLayout playerTabs = (TabLayout) view.findViewById(R.id.player_tab_layout);
        playerTabs.setupWithViewPager(playerPager);
    }
}
