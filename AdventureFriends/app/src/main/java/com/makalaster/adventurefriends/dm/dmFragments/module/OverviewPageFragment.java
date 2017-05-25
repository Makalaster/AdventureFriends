package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.CampaignHolder;
import com.makalaster.adventurefriends.model.campaign.Module;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoadModuleListener} interface
 * to handle interaction events.
 * Use the {@link OverviewPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverviewPageFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MODULE_ID = "MODULE_ID";

    private String mModuleId;
    private Module mCurrentModule;

    private OnLoadModuleListener mListener;

    public OverviewPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param moduleId Parameter 1.
     * @return A new instance of fragment OverviewPageFragment.
     */
    public static OverviewPageFragment newInstance(String moduleId) {
        OverviewPageFragment fragment = new OverviewPageFragment();
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
        mCurrentModule = CampaignHolder.getInstance().getModuleById(mModuleId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.module_title)).setText(mCurrentModule.getTitle());
        ((TextView)view.findViewById(R.id.module_type)).setText(mCurrentModule.getTypeAsString());
        ((TextView)view.findViewById(R.id.module_summary)).setText(mCurrentModule.getSummary());

        view.findViewById(R.id.launch_module_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLaunchModule(mModuleId);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoadModuleListener) {
            mListener = (OnLoadModuleListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoadModuleListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnLoadModuleListener {
        void onLaunchModule(String moduleId);
        void onCompleteModule(String moduleId);
    }
}
