package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.makalaster.adventurefriends.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCreateNoteListener} interface
 * to handle interaction events.
 * Use the {@link NewNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewNoteFragment extends Fragment {
    private OnCreateNoteListener mListener;

    public NewNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewNoteFragment.
     */
    public static NewNoteFragment newInstance() {
        NewNoteFragment fragment = new NewNoteFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText titleEditText = (EditText) view.findViewById(R.id.new_note_title);
        final EditText bodyEditText = (EditText) view.findViewById(R.id.new_note_body);

        view.findViewById(R.id.create_new_note_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String body = bodyEditText.getText().toString().trim();

                if (title.isEmpty()) {
                    titleEditText.setError("Please enter a title");
                    titleEditText.requestFocus();
                } else if (body.isEmpty()) {
                    bodyEditText.setError("Please enter a body");
                    bodyEditText.requestFocus();
                } else {
                    mListener.onCreateNote(title, body);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateNoteListener) {
            mListener = (OnCreateNoteListener) context;
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
    public interface OnCreateNoteListener {
        void onCreateNote(String title, String body);
    }
}
