package com.example.administrator.yixilong1511a20180119.model;

import com.example.administrator.yixilong1511a20180119.presenter.Child_P;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.ChildP_I;
import com.example.administrator.yixilong1511a20180119.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Child_M {

    private ChildP_I childP_i;

    public Child_M(ChildP_I childP_i) {
        this.childP_i = childP_i;
    }

    public void getdata(String child, int cid) {

        HashMap<String, String> map = new HashMap<>();
        map.put("cid", cid + "");
        //获取数据
        OkHttp3Util.doPost(child, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应成功,回传
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    childP_i.success(string);
                }
            }
        });

    }
}
