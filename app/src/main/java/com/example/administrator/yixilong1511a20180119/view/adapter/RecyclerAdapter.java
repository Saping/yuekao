package com.example.administrator.yixilong1511a20180119.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.yixilong1511a20180119.R;
import com.example.administrator.yixilong1511a20180119.model.bean.ChildClassity_bean;
import com.example.administrator.yixilong1511a20180119.util.My_api;
import com.example.administrator.yixilong1511a20180119.util.OkHttp3Util;
import com.example.administrator.yixilong1511a20180119.view.activity.ListActivity;
import com.example.administrator.yixilong1511a20180119.view.activity.MainActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerVH> {

    private Handler handler;
    Context context;
    List<ChildClassity_bean.DataBean> list;

    public RecyclerAdapter(Context context, List<ChildClassity_bean.DataBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.classify_child, null);
        RecyclerVH recyclerVH = new RecyclerVH(view);
        return recyclerVH;
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {


        //赋值
        String icon = list.get(position).getList().get(1).getIcon();
        Glide.with(context).load(icon).into(holder.image);

        holder.name.setText(list.get(1).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
                HashMap<String, String> map = new HashMap<>();
                map.put("pscid", "1");
                //访问接口
                OkHttp3Util.doPost(My_api.listApi, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        //获取到数据传导Activity
                        if (response.isSuccessful()) {
                            String string = response.body().string();

                            Intent intent = new Intent(context, ListActivity.class);
                            intent.putExtra("data", string);
                            context.startActivity(intent);
                        }
                    }
                });


            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
                HashMap<String, String> map = new HashMap<>();
                map.put("pscid", "1");
                //访问接口
                OkHttp3Util.doPost(My_api.listApi, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        //获取到数据传导Activity
                        if (response.isSuccessful()) {
                            String string = response.body().string();

                            Intent intent = new Intent(context, ListActivity.class);
                            intent.putExtra("data", string);
                            context.startActivity(intent);
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
