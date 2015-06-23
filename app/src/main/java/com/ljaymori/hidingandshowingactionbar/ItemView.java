package com.ljaymori.hidingandshowingactionbar;

import android.view.View;
import android.widget.TextView;

public class ItemView extends ItemParentView {

    private TextView tvTitle;
    private TextView tvContent;

    public ItemView(View itemView) {
        super(itemView);

        tvTitle = (TextView) itemView.findViewById(R.id.text_title);
        tvContent = (TextView) itemView.findViewById(R.id.text_content);
    }

    public void setItem(ItemData id) {
        tvTitle.setText(id.getTitle());
        tvContent.setText(id.getContent());
    }
}
