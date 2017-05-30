package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.campaign.CampaignHolder;
import com.makalaster.adventurefriends.dm.dmFragments.module.moduleItemRecyclerView.ItemHolder;
import com.makalaster.adventurefriends.model.Note;

/**
 * Displays a list of all of the notes associated with a module.
 * The note titles are displayed in a FireBaseRecyclerAdapter.
 * Includes a FloatingActionButton to add a new note.
 * Tapping a note opens its detail page.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoteListener} interface
 * to handle interaction events.
 * Use the {@link NotesPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesPageFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MODULE_ID = "module_id";

    private String mModuleId;
    private NoteListener mListener;

    public NotesPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param moduleId Parameter 1.
     * @return A new instance of fragment NotesPageFragment.
     */
    public static NotesPageFragment newInstance(String moduleId) {
        NotesPageFragment fragment = new NotesPageFragment();
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
        return inflater.inflate(R.layout.fragment_notes_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String campaignId = CampaignHolder.getInstance().getCampaign().getCampaignId();
        DatabaseReference notesRef = FirebaseDatabase.getInstance().getReference(
                "campaigns/" + campaignId + "/modules/" + mModuleId + "/notes");

        RecyclerView notesRecycler = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        notesRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        notesRecycler.setAdapter(new FirebaseRecyclerAdapter<Note, ItemHolder>(Note.class, R.layout.layout_module_item, ItemHolder.class, notesRef) {
            @Override
            protected void populateViewHolder(ItemHolder viewHolder, final Note model, int position) {
                viewHolder.mName.setText(model.getTitle());
                viewHolder.mItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onSelectNote(model.getId());
                    }
                });
            }
        });

        FloatingActionButton newNoteFab = (FloatingActionButton) view.findViewById(R.id.new_note_fab);
        newNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddNote();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NoteListener) {
            mListener = (NoteListener) context;
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
    public interface NoteListener {
        void onAddNote();
        void onSelectNote(String noteId);
    }
}
