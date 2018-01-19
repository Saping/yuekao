package com.example.administrator.yixilong1511a20180119.presenter;

import com.example.administrator.yixilong1511a20180119.model.Details_M;
import com.example.administrator.yixilong1511a20180119.presenter.inter_F.DetailsP_I;
import com.example.administrator.yixilong1511a20180119.view.activity.DetailsActivity;
import com.example.administrator.yixilong1511a20180119.view.inter_F.DetailsV_I;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Details_P implements DetailsP_I {

    private DetailsV_I detailsV_i;
    private Details_M details_m;

    public Details_P(DetailsV_I detailsV_i) {
        details_m = new Details_M(this);
        this.detailsV_i = detailsV_i;
    }

    public void getdata(String details, String pid) {
        details_m.getdata(details, pid);
    }

    @Override
    public void success(String s) {
        //回调
        detailsV_i.success(s);
    }
}
