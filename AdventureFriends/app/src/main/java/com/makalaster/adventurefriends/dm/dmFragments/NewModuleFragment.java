package com.makalaster.adventurefriends.dm.dmFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.makalaster.adventurefriends.R;

/**
 * Page to create a new module.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNewModuleCreatedListener} interface
 * to handle interaction events.
 * Use the {@link NewModuleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewModuleFragment extends Fragment {
    private OnNewModuleCreatedListener mListener;
    private EditText mNewModuleTitle, mNewModuleSummary;
    private Spinner mModuleTypeSpinner;

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

        mNewModuleTitle = (EditText) view.findViewById(R.id.new_module_title);
        mNewModuleSummary = (EditText) view.findViewById(R.id.new_module_summary);
        mModuleTypeSpinner = (Spinner) view.findViewById(R.id.new_module_type_spinner);
        Button createModuleButton = (Button) view.findViewById(R.id.create_new_module_button);

        createModuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createModule();
            }
        });
    }

    /**
     * Gather details for the new module from the input sources.
     * Generate errors for missing information
     */
    private void createModule() {
        String title = mNewModuleTitle.getText().toString().trim();
        String summary = mNewModuleSummary.getText().toString().trim();

        if (title.isEmpty()) {
            mNewModuleTitle.setError(getString(R.string.note_title_error));
            mNewModuleTitle.requestFocus();
        } else if (mModuleTypeSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), R.string.module_type_error, Toast.LENGTH_SHORT).show();
        } else {
            mListener.onNewModuleCreated(title, summary, mModuleTypeSpinner.getSelectedItemPosition());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewModuleCreatedListener) {
            mListener = (OnNewModuleCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewModuleCreatedListener");
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
    public interface OnNewModuleCreatedListener {
        void onNewModuleCreated(String title, String summary, int type);
    }
}
