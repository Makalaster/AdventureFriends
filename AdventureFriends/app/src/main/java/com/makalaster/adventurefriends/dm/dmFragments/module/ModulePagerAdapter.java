package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.makalaster.adventurefriends.dm.dmFragments.module.map.MapPageFragment;

/**
 * Pager adapter for the detail fragments of a campaign module.
 */

public class ModulePagerAdapter extends FragmentPagerAdapter {
    private String mModuleId;

    public ModulePagerAdapter(FragmentManager fm, String moduleId) {
        super(fm);
        mModuleId = moduleId;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OverviewPageFragment.newInstance(mModuleId);
            case 1:
                return NPCsPageFragment.newInstance(mModuleId);
            case 2:
                return NotesPageFragment.newInstance(mModuleId);
            case 3:
                return MapPageFragment.newInstance(mModuleId);
            default:
                return null;
        }
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
