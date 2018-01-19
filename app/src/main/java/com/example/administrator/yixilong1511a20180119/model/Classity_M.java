package com.example.administrator.yixilong1511a20180119.model;

import com.example.administrator.yixilong1511a20180119.presenter.Classity_P;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.ClassityP_I;
import com.example.administrator.yixilong1511a20180119.util.OkHttp3Util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Classity_M {

    private ClassityP_I classityP_i;

    public Classity_M(ClassityP_I classityP_i) {
        this.classityP_i = classityP_i;
    }

    public void getdata(String classify) {
        //获取数据
        OkHttp3Util.doGet(classify, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    //获取到数据回传
                    classityP_i.success(string);
                }

            }
        });
    }
}
