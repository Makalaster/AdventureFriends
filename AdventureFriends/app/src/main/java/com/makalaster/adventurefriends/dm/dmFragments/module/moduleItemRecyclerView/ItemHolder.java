package com.makalaster.adventurefriends.dm.dmFragments.module.moduleItemRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;

/**
 * Created by Makalaster on 5/19/17.
 */

public class ItemHolder extends RecyclerView.ViewHolder {
    public TextView mName;
    public View mItem;

    public ItemHolder(View itemView) {
        super(itemView);

        mName = (TextView) itemView.findViewById(R.id.item_title);
        mItem = itemView.findViewById(R.id.npc_or_note_list_item);
    }
}
