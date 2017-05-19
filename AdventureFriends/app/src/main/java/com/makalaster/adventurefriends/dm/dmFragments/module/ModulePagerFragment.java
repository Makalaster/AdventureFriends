package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.content.Context;
import android.net.Uri;
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
 * A simple {@link Fragment} subclass.
 * Use the {@link ModulePagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModulePagerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MODULE_ID = "module_id";

    private String mModuleId;

    public ModulePagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param moduleId Parameter 1.
     * @return A new instance of fragment ModulePagerFragment.
     */
    public static ModulePagerFragment newInstance(String moduleId) {
        ModulePagerFragment fragment = new ModulePagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MODULE_ID, moduleId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mModuleId = getArguments().getString(ARG_MODULE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager modulePager = (ViewPager) view.findViewById(R.id.module_view_pager);
        ModulePagerAdapter modulePagerAdapter = new ModulePagerAdapter(getChildFragmentManager(), mModuleId);
        modulePager.setAdapter(modulePagerAdapter);

        TabLayout moduleTabs = (TabLayout) view.findViewById(R.id.module_tab_layout);
        moduleTabs.setupWithViewPager(modulePager);
    }
}
