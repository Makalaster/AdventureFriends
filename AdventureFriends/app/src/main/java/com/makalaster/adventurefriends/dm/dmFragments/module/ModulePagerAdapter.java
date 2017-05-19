package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.makalaster.adventurefriends.model.campaign.Module;

/**
 * Created by Makalaster on 5/18/17.
 */

public class ModulePagerAdapter extends FragmentPagerAdapter {
    private String mModuleId;
    private Module mCurrentModule;

    public ModulePagerAdapter(FragmentManager fm, String moduleId) {
        super(fm);
        mModuleId = moduleId;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OverviewPageFragment.newInstance("","");
            case 1:
                return NPCsPageFragment.newInstance("", "");
            case 2:
                return NotesPageFragment.newInstance("","");
            case 3:
                return MapPageFragment.newInstance("", "");
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
