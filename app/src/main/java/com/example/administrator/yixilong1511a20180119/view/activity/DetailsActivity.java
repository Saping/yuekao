package com.example.administrator.yixilong1511a20180119.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yixilong1511a20180119.R;
import com.example.administrator.yixilong1511a20180119.model.bean.Details_bean;
import com.example.administrator.yixilong1511a20180119.model.bean.Shopping_bean;
import com.example.administrator.yixilong1511a20180119.presenter.Details_P;
import com.example.administrator.yixilong1511a20180119.presenter.Shoping_P;
import com.example.administrator.yixilong1511a20180119.util.GlideImageLoader;
import com.example.administrator.yixilong1511a20180119.util.My_api;
import com.example.administrator.yixilong1511a20180119.view.inter_F.DetailsV_I;
import com.example.administrator.yixilong1511a20180119.view.inter_F.ShoppingV_I;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements DetailsV_I, ShoppingV_I {


    private TextView title;
    private TextView beginPrice;
    private TextView price;
    private int pid;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String pid = getIntent().getStringExtra("pid");

        //找到控件
//        banner = findViewById(R.id.banner);
        pager = findViewById(R.id.viewPager);
        title = findViewById(R.id.title);
        beginPrice = findViewById(R.id.beginPrice);
        beginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        price = findViewById(R.id.price);
        //MVP获取数据
        Details_P details_p = new Details_P(DetailsActivity.this);
        details_p.getdata(My_api.details, pid);
        Toast.makeText(DetailsActivity.this, pid, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void success(final String s) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //获取导数据  解析  展示
                Gson gson = new Gson();
                Details_bean details_bean = gson.fromJson(s, Details_bean.class);
                Details_bean.DataBean data = details_bean.getData();

                pid = data.getPid();

                String[] split = data.getImages().split("\\|");
                //创建图片的集合
                final ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    list.add(split[i]);
                }
                //文字赋值
                title.setText(data.getTitle());
                beginPrice.setText(data.getBargainPrice() + "");
                price.setText(data.getPrice() + "");

                pager.setAdapter(new PagerAdapter() {
                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {
                        ImageView imageView = new ImageView(DetailsActivity.this);
                        Glide.with(DetailsActivity.this).load(list.get(position)).into(imageView);
                        //添加
                        container.addView(imageView);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //跳转到下一个放大图片的页面,,,并传值过去
                                Intent intent = new Intent(DetailsActivity.this, PicActivity.class);

                                //直接传递string类型的arrayList
                                intent.putStringArrayListExtra("list", list);
                                startActivity(intent);
                            }
                        });
                        return imageView;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView((View) object);
                    }

                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view == object;
                    }
                });
                //设置图片加载器
//                banner.setImageLoader(new GlideImageLoader());
//                //设置图片集合
//                banner.setImages(list);
//                //banner设置方法全部调用完毕时最后调用
//                banner.start();


            }
        });

    }

    //点击加入购物车
    public void btn(View view) {
        //MVP访问接口
        Shoping_P shoping_p = new Shoping_P(this);
        shoping_p.getdata(My_api.addShopping, pid);
    }

    //购物车响应的数据
    @Override
    public void successV(final String S) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Shopping_bean shopping_bean = gson.fromJson(S, Shopping_bean.class);
                String msg = shopping_bean.getMsg();

                Toast.makeText(DetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
