package com.example.administrator.yixilong1511a20180119.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yixilong1511a20180119.R;

/**
 * Created by Administrator on 2018/1/19.
 */

public class RecyclerVH extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView name;

    public RecyclerVH(View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image);

        name = itemView.findViewById(R.id.name);
    }
}
