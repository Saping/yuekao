package com.example.administrator.yixilong1511a20180119.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.yixilong1511a20180119.R;
import com.example.administrator.yixilong1511a20180119.model.bean.List_bean;
import com.example.administrator.yixilong1511a20180119.view.adapter.ListRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {


    private XRecyclerView recycler;
    private List<List_bean.DataBean> list;
    private ListRecyclerAdapter listRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recycler = findViewById(R.id.recycler);

        String data = getIntent().getStringExtra("data");
        //解析  展示
        Gson gson = new Gson();
        List_bean list_bean = gson.fromJson(data, List_bean.class);
        list = list_bean.getData();

        setAdapter();

        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                //重新获取数据
                try {
                    Thread.sleep(2000);
                    recycler.refreshComplete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onLoadMore() {
                //加载
                try {
                    Thread.sleep(2000);
                    recycler.loadMoreComplete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void setAdapter() {

        if (listRecyclerAdapter == null) {
            //设置适配器  布局管理器
            listRecyclerAdapter = new ListRecyclerAdapter(ListActivity.this, list);
            recycler.setAdapter(listRecyclerAdapter);
            recycler.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        } else {
            listRecyclerAdapter.notifyDataSetChanged();
        }

    }
}
