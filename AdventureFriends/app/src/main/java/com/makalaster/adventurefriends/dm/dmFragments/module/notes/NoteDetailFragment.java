package com.makalaster.adventurefriends.dm.dmFragments.module.notes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.campaign.ModuleHolder;
import com.makalaster.adventurefriends.model.Note;

/**
 * Fragment for viewing a note's details and editing its content.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNoteSavedListener} interface
 * to handle interaction events.
 * Use the {@link NoteDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NOTE_ID = "note_id";

    private String mNoteId;
    private TextView mTitleText, mBodyText;
    private EditText mEditTitle, mEditBody;
    private Button mSaveButton, mCancelButton;
    private OnNoteSavedListener mListener;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param noteId Parameter 2.
     * @return A new instance of fragment NoteDetailFragment.
     */
    public static NoteDetailFragment newInstance(String noteId) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOTE_ID, noteId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNoteId = getArguments().getString(ARG_NOTE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Note currentNote = ModuleHolder.getInstance().getNoteById(mNoteId);

        mTitleText = (TextView) view.findViewById(R.id.current_title_text_view);
        mTitleText.setText(currentNote.getTitle());
        mBodyText = (TextView) view.findViewById(R.id.current_body_text_view);
        mBodyText.setText(currentNote.getBody());
        mEditTitle = (EditText) view.findViewById(R.id.current_title_edit_text);
        mEditBody = (EditText) view.findViewById(R.id.current_body_edit_text);
        mSaveButton = (Button) view.findViewById(R.id.edit_save_button);
        mCancelButton = (Button) view.findViewById(R.id.cancel_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSaveButton.getText().equals(getString(R.string.edit))) {
                    switchToEdit();
                } else {
                    update();
                }
            }
        });
    }

    /**
     * Update the layout to allow the current note to be edited in place.
     */
    private void switchToEdit() {
        mEditTitle.setText(mTitleText.getText());
        mEditTitle.setVisibility(View.VISIBLE);
        mTitleText.setVisibility(View.INVISIBLE);
        mEditBody.setText(mBodyText.getText());
        mEditBody.setVisibility(View.VISIBLE);
        mBodyText.setVisibility(View.INVISIBLE);

        mSaveButton.setText(R.string.save);
        mCancelButton.setVisibility(View.VISIBLE);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revert();
            }
        });
    }

    /**
     * Restore the layout to its original state without making any changes.
     */
    private void revert() {
        mTitleText.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);
        mBodyText.setVisibility(View.VISIBLE);
        mEditBody.setVisibility(View.GONE);
        mCancelButton.setVisibility(View.GONE);
        mSaveButton.setText(R.string.edit);
    }

    /**
     * Save the changes to the note and restore the layout to its original state with the changes.
     */
    private void update() {
        String newTitle = mEditTitle.getText().toString().trim();
        String newBody = mEditBody.getText().toString().trim();
        if (newTitle.isEmpty()) {
            mEditTitle.setError(getString(R.string.new_note_title_error));
            mEditTitle.requestFocus();
        } else if (newBody.isEmpty()) {
            mEditBody.setError(getString(R.string.new_note_body_error));
            mEditBody.requestFocus();
        } else {
            mTitleText.setText(mEditTitle.getText().toString().trim());
            mTitleText.setVisibility(View.VISIBLE);
            mEditTitle.setVisibility(View.GONE);
            mBodyText.setText(mEditBody.getText().toString().trim());
            mBodyText.setVisibility(View.VISIBLE);
            mEditBody.setVisibility(View.GONE);
            mCancelButton.setVisibility(View.GONE);
            mSaveButton.setText(R.string.edit);

            mListener.onSaveNote(mNoteId, newTitle, newBody);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteSavedListener) {
            mListener = (OnNoteSavedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNoteSavedListener");
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
    public interface OnNoteSavedListener {
        void onSaveNote(String noteId, String newTitle, String newBody);
    }
}
