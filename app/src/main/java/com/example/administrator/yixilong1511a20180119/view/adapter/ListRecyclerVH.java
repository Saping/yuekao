package com.example.administrator.yixilong1511a20180119.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yixilong1511a20180119.R;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ListRecyclerVH extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView content;
    public TextView beginPrice;
    public TextView price;

    public ListRecyclerVH(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        content = itemView.findViewById(R.id.content);
        beginPrice = itemView.findViewById(R.id.beginPrice);
        price = itemView.findViewById(R.id.price);

    }
}
