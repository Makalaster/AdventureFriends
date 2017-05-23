package com.makalaster.adventurefriends.player.pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makalaster.adventurefriends.R;
import com.makalaster.adventurefriends.dm.CampaignHolder;
import com.makalaster.adventurefriends.model.character.components.item.Item;
import com.makalaster.adventurefriends.player.pages.recyclers.InventoryItemHolder;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnInventoryItemSelectedListener} interface
 * to handle interaction events.
 * Use the {@link InventoryPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryPageFragment extends Fragment {

    private OnInventoryItemSelectedListener mListener;

    public InventoryPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InventoryPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryPageFragment newInstance() {
        InventoryPageFragment fragment = new InventoryPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String campaignId = CampaignHolder.getInstance().getCampaign().getCampaignId();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference inventoryRef = FirebaseDatabase.getInstance()
                .getReference("campaigns/" + campaignId + "/players/" + uid + "/inventory");

        RecyclerView inventoryRecycler = (RecyclerView) view.findViewById(R.id.inventory_recycler);
        inventoryRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        inventoryRecycler.setAdapter(
                new FirebaseRecyclerAdapter<Item, InventoryItemHolder>(Item.class, R.layout.layout_inventory_item_holder, InventoryItemHolder.class, inventoryRef) {
                    @Override
                    protected void populateViewHolder(InventoryItemHolder viewHolder, Item model, int position) {
                        viewHolder.mItemName.setText(model.getName());
                        viewHolder.mItemType.setText(model.getType());
                        viewHolder.mItemValue.setText(String.format(Locale.ENGLISH, "Value: %d pearl(s)", model.getValue()));
                        viewHolder.mItemEffect.setText(model.getEffect());
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryItemSelectedListener) {
            mListener = (OnInventoryItemSelectedListener) context;
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
    public interface OnInventoryItemSelectedListener {
        void onInventoryItemSelected(Uri uri);
    }
}
