package com.example.administrator.yixilong1511a20180119.presenter;

import com.example.administrator.yixilong1511a20180119.model.Classity_M;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.ClassityP_I;
import com.example.administrator.yixilong1511a20180119.view.inter_F.ClassityV_I;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Classity_P implements ClassityP_I {

    private Classity_M classity_m;
    private ClassityV_I classityV_i;

    public Classity_P(ClassityV_I classityV_i) {
        classity_m = new Classity_M(this);
        this.classityV_i = classityV_i;
    }

    public void getdata(String classify) {
        classity_m.getdata(classify);
    }

    @Override
    public void success(String s) {
        classityV_i.success(s);
    }
}
