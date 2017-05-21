package com.makalaster.adventurefriends.dm.dmFragments.module.npcs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.baseGames.GoblinsGoblins;
import com.makalaster.adventurefriends.model.character.components.Job;
import com.makalaster.adventurefriends.model.character.components.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateNPCListener} interface
 * to handle interaction events.
 * Use the {@link NewNPCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewNPCFragment extends Fragment {

    private GoblinsGoblins mGoblinsGoblins;
    private EditText mName, mLevel, mMoney;
    private Spinner mSizeSpinner, mJobSpinner;
    private CreateNPCListener mListener;

    public NewNPCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewNPCFragment.
     */
    public static NewNPCFragment newInstance() {
        NewNPCFragment fragment = new NewNPCFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoblinsGoblins = GoblinsGoblins.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_npc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mName = (EditText) view.findViewById(R.id.npc_name_edit_text);
        mLevel = (EditText) view.findViewById(R.id.level_edit_text);
        mMoney = (EditText) view.findViewById(R.id.money_edit_text);

        mSizeSpinner = (Spinner) view.findViewById(R.id.size_spinner);
        ArrayList<String> sizes = getSizes();
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, sizes);
        mSizeSpinner.setAdapter(sizeAdapter);

        mJobSpinner = (Spinner) view.findViewById(R.id.job_spinner);
        ArrayList<String> jobs = getJobs();
        ArrayAdapter<String> jobAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, jobs);
        mJobSpinner.setAdapter(jobAdapter);

        Button createButton = (Button) view.findViewById(R.id.create_npc_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNPC();
            }
        });
    }

    private ArrayList<String> getSizes() {
        ArrayList<String> sizeNames = new ArrayList<>();
        List<Size> sizes = mGoblinsGoblins.getAllSizes();
        for (Size size : sizes) {
            sizeNames.add(size.getName() + " +" + size.getBonus());
        }

        return sizeNames;
    }

    private ArrayList<String> getJobs() {
        ArrayList<String> jobNames = new ArrayList<>();
        List<Job> jobs = mGoblinsGoblins.getAllJobs();
        for (Job job : jobs) {
            jobNames.add(job.getName() + " +" + job.getBonus());
        }

        return jobNames;
    }

    public void createNPC() {
        String name = mName.getText().toString().trim();
        String levelText = mLevel.getText().toString().trim();
        String moneyText = mMoney.getText().toString().trim();
        Size size = null;
        switch (mSizeSpinner.getSelectedItemPosition()) {
            case 0:
                size = mGoblinsGoblins.getSizeById(1);
                break;
            case 1:
                size = mGoblinsGoblins.getSizeById(2);
                break;
            case 2:
                size = mGoblinsGoblins.getSizeById(3);
                break;
        }
        Job job = null;
        switch (mJobSpinner.getSelectedItemPosition()) {
            case 0:
                job = mGoblinsGoblins.getJobById(1);
                break;
            case 1:
                job = mGoblinsGoblins.getJobById(2);
                break;
            case 2:
                job = mGoblinsGoblins.getJobById(3);
                break;
        }

        if (name.isEmpty()) {
            mName.setError("Please enter a name");
            mName.requestFocus();
        } else if (levelText.isEmpty() || Integer.parseInt(levelText) < 1) {
            mLevel.setError("Please enter a level greater than 0");
            mLevel.requestFocus();
        } else if (moneyText.isEmpty() || Integer.parseInt(moneyText) < 0) {
            mMoney.setError("Please enter a value for money");
            mMoney.requestFocus();
        } else {
            int level = Integer.parseInt(levelText);
            int money = Integer.parseInt(moneyText);
            mListener.onCreateNPC(name, level, money, size, job);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateNPCListener) {
            mListener = (CreateNPCListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NoteListener");
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
    public interface CreateNPCListener {
        void onCreateNPC(String name, int level, int money, Size size, Job job);
    }
}
