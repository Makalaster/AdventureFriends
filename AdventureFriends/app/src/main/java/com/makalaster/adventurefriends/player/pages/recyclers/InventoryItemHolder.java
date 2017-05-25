package com.makalaster.adventurefriends.player.pages.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;

/**
 * Holds the views for an inventory item.
 */

public class InventoryItemHolder extends RecyclerView.ViewHolder {
    public TextView mItemName, mItemType, mItemValue, mItemEffect;
    public View mInventoryItem;

    public InventoryItemHolder(View itemView) {
        super(itemView);

        mItemName = (TextView) itemView.findViewById(R.id.item_name);
        mItemType = (TextView) itemView.findViewById(R.id.item_type);
        mItemValue = (TextView) itemView.findViewById(R.id.item_value);
        mItemEffect = (TextView) itemView.findViewById(R.id.item_effect);
        mInventoryItem = itemView.findViewById(R.id.inventory_item);
    }
}
