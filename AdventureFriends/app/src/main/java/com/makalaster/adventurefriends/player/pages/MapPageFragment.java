package com.makalaster.adventurefriends.player.pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.character.PlayerCharacter;
import com.makalaster.adventurefriends.model.map.Map;
import com.makalaster.adventurefriends.model.map.MapView;
import com.makalaster.adventurefriends.model.map.OnTileClickedListener;
import com.makalaster.adventurefriends.model.map.Tile;
import com.makalaster.adventurefriends.player.PlayerCharacterHolder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapPageFragment extends Fragment implements OnTileClickedListener {
    private OnFragmentInteractionListener mListener;
    private Map mMap;

    public MapPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapPageFragment.
     */
    public static MapPageFragment newInstance() {
        MapPageFragment fragment = new MapPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_page2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapView mapView = (MapView) view.findViewById(R.id.player_map);
        mapView.setTileClickedListener(this);
        mMap = new Map();

        mapView.setMap(mMap);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement EquipmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDmTileClicked(Tile tile) {

    }

    @Override
    public void onPlayerTileClicked(Tile tile) {
        PlayerCharacter me = PlayerCharacterHolder.getInstance().getPlayerCharacter();

        if (tile.containsNonPlayer()) {
            me.attackWithWeapon(tile.getNonPlayer());
        } else {
            if (me.getCurrentLocation() == null) {
                mMap.addPlayer(me, tile.getX(), tile.getY());
                me.setCurrentLocation(tile);
            } else {
                mMap.removePlayer(me.getCurrentLocation().getX(), me.getCurrentLocation().getY());
                mMap.addPlayer(me, tile.getX(), tile.getY());
                me.setCurrentLocation(tile);
            }
        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
