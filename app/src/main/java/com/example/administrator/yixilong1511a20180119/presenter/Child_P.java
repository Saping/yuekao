package com.example.administrator.yixilong1511a20180119.presenter;

import com.example.administrator.yixilong1511a20180119.model.Child_M;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.ChildP_I;
import com.example.administrator.yixilong1511a20180119.view.inter_F.ChildV_I;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Child_P implements ChildP_I {

    private Child_M child_m;
    private ChildV_I childV_i;

    public Child_P(ChildV_I childV_i) {
        child_m = new Child_M(this);
        this.childV_i = childV_i;
    }

    public void getdata(String child, int cid) {
        child_m.getdata(child, cid);
    }

    @Override
    public void success(String s) {
        childV_i.successV(s);
    }
}
