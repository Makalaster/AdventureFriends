package com.makalaster.adventurefriends.dm.dmFragments.module.map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.campaign.ModuleHolder;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;
import com.makalaster.adventurefriends.model.map.MapView;
import com.makalaster.adventurefriends.model.map.OnTileClickedListener;
import com.makalaster.adventurefriends.model.map.Tile;

import java.util.ArrayList;

/**
 * Displays the current map for a module.
 * The DM can use this to edit the map and control NPC actions.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMapInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapPageFragment extends Fragment implements OnTileClickedListener, UnplacedNonPlayerCharacterRecyclerViewAdapter.OnItemClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MODULE_ID = "module_id";
    private static final String TAG = "MapPageFragment";

    private String mModuleId;
    private Map mMap;
    private AlertDialog mDialog;
    private Tile mTile;

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
        Map map = ModuleHolder.getInstance().getMap();
        if (map == null) {
            mMap = new Map();
        } else {
            mMap = map;
        }

        mapView.setMap(mMap);

        FloatingActionButton updateMapFab = (FloatingActionButton) view.findViewById(R.id.update_map_fab);
        updateMapFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mapView.isEditMode()) {
                    mapView.setEditMode(true);
                    Toast.makeText(v.getContext(), "Editing", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.onMapSaved(mMap);
                    mapView.setEditMode(false);
                    Toast.makeText(v.getContext(), "Map saved", Toast.LENGTH_SHORT).show();
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

    /**
     * When the map is in edit mode, the DM can place or remove NPCs on the map.
     * @param tile The tile that was clicked on.
     */
    @Override
    public void onDmTileClicked(Tile tile) {
        if (tile.containsNonPlayer()) {
            tile.getNonPlayer().setPlaced(false);
            mMap.removeNonPlayer(tile.getX(), tile.getY());
        } else {
            mTile = tile;
            displayNPCDialog();
            //TODO choose from list of existing NPCs.
            //TODO only show unplaced NPCs.
            //TODO tapping placed NPC adds to unplaced list.

            /*mMap.addNonPlayer(
                    new NonPlayerCharacter("Jimmy", "1234", 5,
                            GoblinsGoblins.getInstance(getContext()).getSizeById(1),
                            GoblinsGoblins.getInstance(getContext()).getJobById(1),
                            5), tile.getX(), tile.getY());
            */
        }
    }

    private void displayNPCDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View npcListView = LayoutInflater.from(getContext()).inflate(R.layout.layout_unplaced_npcs_dialog, null);
        builder.setView(npcListView);

        RecyclerView npcList = (RecyclerView) npcListView.findViewById(R.id.unplaced_npc_recycler);
        npcList.setLayoutManager(new LinearLayoutManager(npcListView.getContext(), LinearLayoutManager.VERTICAL, false));
        npcList.setAdapter(new UnplacedNonPlayerCharacterRecyclerViewAdapter(getUnplacedNPCs(), this));

        builder.setTitle("Select an NPC to place")
                .setNegativeButton("Cancel", null);

        mDialog = builder.create();
        mDialog.show();
    }

    private ArrayList<NonPlayerCharacter> getUnplacedNPCs() {
        ModuleHolder moduleHolder = ModuleHolder.getInstance();
        ArrayList<NonPlayerCharacter> allNPCs = moduleHolder.getNPCs();
        ArrayList<NonPlayerCharacter> unplaced = new ArrayList<>();
        for (NonPlayerCharacter npc : allNPCs) {
            if (!npc.isPlaced()) {
                unplaced.add(npc);
            }
        }

        return unplaced;
    }

    //TODO implement controlling non-player characters
    @Override
    public void onPlayerTileClicked(Tile tile) {

    }

    @Override
    public void placeNPC(String id) {
        ModuleHolder moduleHolder = ModuleHolder.getInstance();
        NonPlayerCharacter placedNPC = moduleHolder.getNPCById(id);
        placedNPC.setPlaced(true);
        mMap.addNonPlayer(placedNPC, mTile.getX(), mTile.getY());

        mDialog.dismiss();
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
        void onMapSaved(Map map);
    }
}
