package com.makalaster.adventurefriends.dm.moduleRecyclerView;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.makalaster.adventurefriends.R;

/**
 * Created by Makalaster on 5/18/17.
 */

public class ModuleViewHolder extends ViewHolder {
    public TextView mModuleName, mModuleType;
    public View mModuleListItem;

    public ModuleViewHolder(View itemView) {
        super(itemView);

        mModuleName = (TextView) itemView.findViewById(R.id.module_name);
        mModuleType = (TextView) itemView.findViewById(R.id.module_type);
        mModuleListItem = itemView.findViewById(R.id.module_list_item);
    }
}
