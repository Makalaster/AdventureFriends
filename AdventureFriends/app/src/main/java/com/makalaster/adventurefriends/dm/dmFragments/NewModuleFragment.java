package com.makalaster.adventurefriends.dm.dmFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNewCampaignCreatedListener} interface
 * to handle interaction events.
 * Use the {@link NewModuleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewModuleFragment extends Fragment {
    private static final String TAG = "NewModuleFragment";

    private OnNewCampaignCreatedListener mListener;

    public NewModuleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewModuleFragment.
     */
    public static NewModuleFragment newInstance() {
        NewModuleFragment fragment = new NewModuleFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_module, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewCampaignCreatedListener) {
            mListener = (OnNewCampaignCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewCampaignCreatedListener");
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
    public interface OnNewCampaignCreatedListener {
        void onNewModuleCreated(String title, String summary, int type);
    }
}
