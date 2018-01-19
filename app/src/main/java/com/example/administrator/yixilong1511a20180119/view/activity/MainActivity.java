package com.example.administrator.yixilong1511a20180119.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.yixilong1511a20180119.R;
import com.example.administrator.yixilong1511a20180119.model.bean.ChildClassity_bean;
import com.example.administrator.yixilong1511a20180119.model.bean.Classify_bean;
import com.example.administrator.yixilong1511a20180119.presenter.Child_P;
import com.example.administrator.yixilong1511a20180119.presenter.Classity_P;
import com.example.administrator.yixilong1511a20180119.util.My_api;
import com.example.administrator.yixilong1511a20180119.view.adapter.RecyclerAdapter;
import com.example.administrator.yixilong1511a20180119.view.inter_F.ChildV_I;
import com.example.administrator.yixilong1511a20180119.view.inter_F.ClassityV_I;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClassityV_I, ChildV_I {

    private Classity_P classity_p;
    List<String> list = new ArrayList<>();
    private ListView listView;
    private Child_P child_p;
    private RecyclerView recycler;
    private List<Classify_bean.DataBean> data;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        listView = findViewById(R.id.list_view);
        recycler = findViewById(R.id.recycler);


        //获取数据,实现点击左边listView改变右边数据


        //首先要获取数据
        classity_p = new Classity_P(this);
        classity_p.getdata(My_api.Classify);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(MainActivity.this, "" + i, Toast.LENGTH_SHORT).show();
                //当我点击子条目的时候,把pid拿到,然后访问接口
                int cid = data.get(i).getCid();
                child_p = new Child_P(MainActivity.this);
                child_p.getdata(My_api.child, cid);

            }
        });
    }

    @Override
    public void success(final String s) {
        //获取到数据之后   解析  展示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Classify_bean classify_bean = gson.fromJson(s, Classify_bean.class);
                data = classify_bean.getData();

                //创建集合,把名字添加到集合

                for (int i = 0; i < data.size(); i++) {
                    list.add(data.get(i).getName());
                }
                //放到ListView展示
                listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list));


            }
        });

    }

    //获取到子分类数据
    @Override
    public void successV(String s) {
        //解析  展示
        Gson gson = new Gson();
        ChildClassity_bean childClassity_bean = gson.fromJson(s, ChildClassity_bean.class);
        final List<ChildClassity_bean.DataBean> data = childClassity_bean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //设置RecyvlerView
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, data,handler);
                recycler.setAdapter(recyclerAdapter);
                recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
//                Toast.makeText(MainActivity.this,data+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
