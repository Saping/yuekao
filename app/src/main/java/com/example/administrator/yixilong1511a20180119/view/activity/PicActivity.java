package com.example.administrator.yixilong1511a20180119.view.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.yixilong1511a20180119.R;
import com.example.administrator.yixilong1511a20180119.util.ZoomImageView;

import java.util.ArrayList;

public class PicActivity extends AppCompatActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        final ArrayList<String> list = getIntent().getStringArrayListExtra("list");

        pager = findViewById(R.id.mypager);

        if (list != null) {
            pager.setAdapter(new PagerAdapter() {
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ZoomImageView imageView = new ZoomImageView(PicActivity.this);
                    //加载图片显示
                    Glide.with(PicActivity.this).load(list.get(position)).into(imageView);

                    //添加
                    container.addView(imageView);
                    //返回
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position,
                                        Object object) {
                    container.removeView((View) object);
                }

                @Override
                public boolean isViewFromObject(View arg0, Object arg1) {
                    return arg0 == arg1;
                }

                @Override
                public int getCount() {
                    return list.size();
                }
            });
        }

    }
}
