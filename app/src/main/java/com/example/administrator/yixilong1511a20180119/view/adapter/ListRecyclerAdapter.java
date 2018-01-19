package com.example.administrator.yixilong1511a20180119.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yixilong1511a20180119.R;
import com.example.administrator.yixilong1511a20180119.model.bean.List_bean;
import com.example.administrator.yixilong1511a20180119.view.activity.DetailsActivity;
import com.example.administrator.yixilong1511a20180119.view.activity.ListActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerVH> {
    Context context;
    List<List_bean.DataBean> list;

    public ListRecyclerAdapter(Context context, List<List_bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ListRecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_layout, null);
        ListRecyclerVH listRecyclerVH = new ListRecyclerVH(view);

        return listRecyclerVH;
    }

    @Override
    public void onBindViewHolder(ListRecyclerVH holder, final int position) {

        //赋值
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image);

        holder.content.setText(list.get(position).getTitle());
        holder.beginPrice.setText(list.get(position).getBargainPrice() + "");
        //文字中间线
        holder.beginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.price.setText(list.get(position).getPrice() + "");

        //点击图片的时候
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到详情页面  要 pid
                int pid = list.get(position).getPid();
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("pid", pid + "");
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
