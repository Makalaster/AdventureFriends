package com.makalaster.adventurefriends.player;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.makalaster.adventurefriends.player.pages.AbilitiesPageFragment;
import com.makalaster.adventurefriends.player.pages.EquipmentPageFragment;
import com.makalaster.adventurefriends.player.pages.InventoryPageFragment;
import com.makalaster.adventurefriends.player.pages.MapPageFragment;
import com.makalaster.adventurefriends.player.pages.NotesPageFragment;
import com.makalaster.adventurefriends.player.pages.StatsPageFragment;

/**
 * Created by Makalaster on 5/21/17.
 */

public class PlayerPagerAdapter extends FragmentPagerAdapter {

    public PlayerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StatsPageFragment.newInstance();
            case 1:
                return AbilitiesPageFragment.newInstance();
            case 2:
                return EquipmentPageFragment.newInstance();
            case 3:
                return InventoryPageFragment.newInstance();
            case 4:
                return NotesPageFragment.newInstance("", "");
            case 5:
                return MapPageFragment.newInstance("", "");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Stats";
            case 1:
                return "Abilities";
            case 2:
                return "Equipment";
            case 3:
                return "Inventory";
            case 4:
                return "Notes";
            case 5:
                return "Map";
            default:
                return null;
        }
    }
}
