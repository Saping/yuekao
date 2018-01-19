package com.example.administrator.yixilong1511a20180119.presenter;

import com.example.administrator.yixilong1511a20180119.model.Shoping_M;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.ShoppingP_I;
import com.example.administrator.yixilong1511a20180119.view.activity.DetailsActivity;
import com.example.administrator.yixilong1511a20180119.view.inter_F.ShoppingV_I;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Shoping_P implements ShoppingP_I {

    private ShoppingV_I shoppingV_i;
    private Shoping_M shoping_m;

    public Shoping_P(ShoppingV_I shoppingV_i) {
        shoping_m = new Shoping_M(this);
        this.shoppingV_i = shoppingV_i;
    }

    public void getdata(String addshopping, int pid) {
        shoping_m.getdata(addshopping, pid);
    }

    @Override
    public void success(String s) {
        shoppingV_i.successV(s);
    }
}
