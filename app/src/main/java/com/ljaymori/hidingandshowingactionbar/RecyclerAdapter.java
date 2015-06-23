package com.ljaymori.hidingandshowingactionbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ItemParentView> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;

    private ArrayList<ItemData> items = new ArrayList<ItemData>();
    private Context mContext;

    public RecyclerAdapter(Context context, ArrayList<ItemData> list) {
        mContext = context;
        items = list;
    }


    public void addAll(ArrayList<ItemData> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ItemParentView onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
            return new ItemView(v);
        } else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_header, viewGroup, false);
            return new ItemHeaderView(v);
        }
    }

    @Override
    public void onBindViewHolder(ItemParentView viewHolder, int position) {
        if(viewHolder instanceof ItemView) {
            ((ItemView) viewHolder).setItem(items.get(position));

        }
    }

    @Override
    public int getItemCount() {
        return items.size() - 1;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

}
