package com.example.administrator.yixilong1511a20180119.model;

import com.example.administrator.yixilong1511a20180119.presenter.Shoping_P;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.ShoppingP_I;
import com.example.administrator.yixilong1511a20180119.util.OkHttp3Util;
import com.example.administrator.yixilong1511a20180119.view.activity.DetailsActivity;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Shoping_M {

    private ShoppingP_I shoppingP_i;

    public Shoping_M(ShoppingP_I shoppingP_i) {
        this.shoppingP_i = shoppingP_i;
    }

    public void getdata(String addshopping, int pid) {

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "4123");
        map.put("pid", pid + "");

        OkHttp3Util.doPost(addshopping, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取数据  回调
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    shoppingP_i.success(string);
                }
            }
        });
    }
}
