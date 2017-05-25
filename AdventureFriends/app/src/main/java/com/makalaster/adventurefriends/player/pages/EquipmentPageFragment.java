package com.makalaster.adventurefriends.player.pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.model.character.NonPlayerCharacter;
import com.makalaster.adventurefriends.model.character.components.item.Defense;
import com.makalaster.adventurefriends.model.character.components.item.Item;
import com.makalaster.adventurefriends.model.character.components.item.Weapon;
import com.makalaster.adventurefriends.player.PlayerCharacterHolder;

import java.util.HashMap;
import java.util.Locale;

/**
 * Displays all of the player's equipment in the designated equipment slots.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EquipmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EquipmentPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EquipmentPageFragment extends Fragment {
    private static final String TAG = "EquipmentPageFragment";
    private PlayerCharacterHolder mPlayerCharacterHolder;

    private EquipmentInteractionListener mListener;

    public EquipmentPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment EquipmentPageFragment.
     */
    public static EquipmentPageFragment newInstance() {
        EquipmentPageFragment fragment = new EquipmentPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayerCharacterHolder = PlayerCharacterHolder.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipment_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO fix crashing bug on app resume
        HashMap<String, Item> equipment = mPlayerCharacterHolder.getEquipment();
        Defense hat = (Defense) equipment.get(NonPlayerCharacter.HAT);
        Defense shirt = (Defense) equipment.get(NonPlayerCharacter.SHIRT);
        Defense boots = (Defense) equipment.get(NonPlayerCharacter.BOOTS);
        Weapon weapon = (Weapon) equipment.get(NonPlayerCharacter.WEAPON);

        ((TextView) view.findViewById(R.id.hat_name_and_description))
                .setText(hat.getName() + ", " + hat.getDescription());
        ((TextView) view.findViewById(R.id.hat_effect))
                .setText(String.format(Locale.ENGLISH, "Defense: %d", hat.getDefense()));
        ((TextView) view.findViewById(R.id.shirt_effect))
                .setText(String.format(Locale.ENGLISH, "Defense: %d", shirt.getDefense()));
        ((TextView) view.findViewById(R.id.shirt_name_and_description))
                .setText(shirt.getName() + ", " + shirt.getDescription());
        ((TextView) view.findViewById(R.id.boots_effect))
                .setText(String.format(Locale.ENGLISH, "Defense: %d", boots.getDefense()));
        ((TextView) view.findViewById(R.id.boots_name_and_description))
                .setText(boots.getName() + ", " + boots.getDescription());
        ((TextView) view.findViewById(R.id.weapon_effect))
                .setText(String.format(Locale.ENGLISH, "Damage: %d", weapon.getDamage()));
        ((TextView) view.findViewById(R.id.weapon_range))
                .setText(String.valueOf(weapon.getRange()));
        ((TextView) view.findViewById(R.id.weapon_name_and_description))
                .setText(weapon.getName() + ", " + weapon.getDescription());

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EquipmentInteractionListener) {
            mListener = (EquipmentInteractionListener) context;
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

    //TODO implement equipment interaction to add and remove equipment
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
    public interface EquipmentInteractionListener {
        void onEquipmentSelected(Uri uri);
    }
}
