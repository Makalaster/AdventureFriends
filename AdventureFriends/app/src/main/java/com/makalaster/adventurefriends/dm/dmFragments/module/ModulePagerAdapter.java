package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Makalaster on 5/18/17.
 */

public class ModulePagerAdapter extends FragmentPagerAdapter {

    public ModulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Overview";
            case 1:
                return "NPCs";
            case 2:
                return "Notes";
            case 3:
                return "Map";
            default:
                return null;
        }
    }
}
