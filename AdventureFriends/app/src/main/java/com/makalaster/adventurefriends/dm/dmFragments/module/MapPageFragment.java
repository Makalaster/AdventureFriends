package com.makalaster.adventurefriends.dm.dmFragments.module;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.baseGames.GoblinsGoblins;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;
import com.makalaster.adventurefriends.model.map.MapView;
import com.makalaster.adventurefriends.model.map.OnTileClickedListener;
import com.makalaster.adventurefriends.model.map.Tile;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMapInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapPageFragment extends Fragment implements OnTileClickedListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MODULE_ID = "module_id";

    private String mModuleId;
    private Map mMap;

    private OnMapInteractionListener mListener;

    public MapPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param moduleId Parameter 1.
     * @return A new instance of fragment MapPageFragment.
     */
    public static MapPageFragment newInstance(String moduleId) {
        MapPageFragment fragment = new MapPageFragment();
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
        return inflater.inflate(R.layout.fragment_map_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MapView mapView = (MapView) view.findViewById(R.id.dm_map);
        mapView.setTileClickedListener(this);
        mMap = new Map();

        mapView.setMap(mMap);

        FloatingActionButton updateMapFab = (FloatingActionButton) view.findViewById(R.id.update_map_fab);
        updateMapFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mapView.isEditMode()) {
                    mapView.setEditMode(true);
                    Toast.makeText(v.getContext(), "Editing", Toast.LENGTH_SHORT).show();
                } else {
                    mapView.setEditMode(false);
                    Toast.makeText(v.getContext(), "Map saved", Toast.LENGTH_SHORT).show();
                    mListener.onMapSaved();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMapInteractionListener) {
            mListener = (OnMapInteractionListener) context;
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

    @Override
    public void onDmTileClicked(Tile tile) {
        if (tile.containsNonPlayer()) {
            mMap.removeNonPlayer(tile.getX(), tile.getY());
        } else {
            mMap.addNonPlayer(
                    new NonPlayerCharacter("Jimmy", "1234", 5,
                            GoblinsGoblins.getInstance(getContext()).getSizeById(1),
                            GoblinsGoblins.getInstance(getContext()).getJobById(1),
                            5), tile.getX(), tile.getY());
        }
    }

    @Override
    public void onPlayerTileClicked(Tile tile) {

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
    public interface OnMapInteractionListener {
        void onMapSaved();
    }
}
