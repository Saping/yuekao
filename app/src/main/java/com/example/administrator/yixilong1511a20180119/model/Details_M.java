package com.example.administrator.yixilong1511a20180119.model;

import com.example.administrator.yixilong1511a20180119.presenter.Details_P;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.DetailsP_I;
import com.example.administrator.yixilong1511a20180119.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Details_M {

    private DetailsP_I detailsP_i;

    public Details_M(DetailsP_I detailsP_i) {
        this.detailsP_i = detailsP_i;

    }

    public void getdata(String details, String pid) {

        HashMap<String, String> map = new HashMap<>();
        map.put("pid", pid);
        //获取数据
        OkHttp3Util.doPost(details, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //回传
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    detailsP_i.success(string);
                }

            }
        });
    }
}
