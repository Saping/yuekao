package com.example.administrator.yixilong1511a20180119.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Dash on 2017/12/8.
 * compile 'com.squareup.okhttp3:okhttp:3.6.0'
 * compile 'com.squareup.okio:okio:1.11.0'
 * compile 'com.google.code.gson:gson:2.6.2'
 * compile 'com.github.bumptech.glide:glide:3.6.1'
 */
public class OkHttp3Util {

    /**
     * 懒汉 安全 加同步
     * 私有的静态成员变量 只声明不创建
     * 私有的构造方法
     * 提供返回实例的静态方法
     */
    private static OkHttpClient okHttpClient = null;


    private OkHttp3Util() {
    }

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            //加同步安全
            synchronized (OkHttp3Util.class) {
                if (okHttpClient == null) {
                    //okhttp可以缓存数据....指定缓存路径
                    File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
                    //指定缓存大小
                    int cacheSize = 10 * 1024 * 1024;

                    okHttpClient = new OkHttpClient.Builder()//构建器
                            .connectTimeout(15, TimeUnit.SECONDS)//连接超时
                            .writeTimeout(20, TimeUnit.SECONDS)//写入超时
                            .readTimeout(20, TimeUnit.SECONDS)//读取超时

                            //.addInterceptor(new CommonParamsInterceptor())//添加的是应用拦截器...公共参数
                            //.addNetworkInterceptor(new CacheInterceptor())//添加的网络拦截器

                            .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))//设置缓存
                            .build();
                }
            }

        }

        return okHttpClient;
    }

    /**
     * get请求
     * 参数1 url
     * 参数2 回调Callback
     */

    public static void doGet(String oldUrl, Callback callback) {

        //要添加的公共参数...map
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");

        StringBuilder stringBuilder = new StringBuilder();//创建一个stringBuilder

        stringBuilder.append(oldUrl);

        if (oldUrl.contains("?")) {
            //?在最后面....2类型
            if (oldUrl.indexOf("?") == oldUrl.length() - 1) {

            } else {
                //3类型...拼接上&
                stringBuilder.append("&");
            }
        } else {
            //不包含? 属于1类型,,,先拼接上?号
            stringBuilder.append("?");
        }

        //添加公共参数....
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //拼接
            stringBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        //删掉最后一个&符号
        if (stringBuilder.indexOf("&") != -1) {
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
        }

        String newUrl = stringBuilder.toString();//新的路径


        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        //创建Request
        Request request = new Request.Builder().url(newUrl).build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(callback);


    }

    /**
     * post请求
     * 参数1 url
     * 参数2 Map<String, String> params post请求的时候给服务器传的数据
     * add..("","")
     * add()
     */

    public static void doPost(String url, Map<String, String> params, Callback callback) {
        //要添加的公共参数...map
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");


        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        //3.x版本post请求换成FormBody 封装键值对参数

        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));

        }

        //添加公共参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        //创建Request
        Request request = new Request.Builder().url(url).post(builder.build()).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }


    /**
     * 公共参数拦截器
     */
    private static class CommonParamsInterceptor implements Interceptor {

        //拦截的方法
        @Override
        public Response intercept(Chain chain) throws IOException {

            //获取到请求
            Request request = chain.request();
            //获取请求的方式
            String method = request.method();
            //获取请求的路径
            String oldUrl = request.url().toString();

            Log.e("---拦截器", request.url() + "---" + request.method() + "--" + request.header("User-agent"));

            //要添加的公共参数...map
            Map<String, String> map = new HashMap<>();
            map.put("source", "android");

            if ("GET".equals(method)) {
                // 1.http://www.baoidu.com/login                --------？ key=value&key=value
                // 2.http://www.baoidu.com/login?               --------- key=value&key=value
                // 3.http://www.baoidu.com/login?mobile=11111    -----&key=value&key=value

                StringBuilder stringBuilder = new StringBuilder();//创建一个stringBuilder

                stringBuilder.append(oldUrl);

                if (oldUrl.contains("?")) {
                    //?在最后面....2类型
                    if (oldUrl.indexOf("?") == oldUrl.length() - 1) {

                    } else {
                        //3类型...拼接上&
                        stringBuilder.append("&");
                    }
                } else {
                    //不包含? 属于1类型,,,先拼接上?号
                    stringBuilder.append("?");
                }

                //添加公共参数....
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    //拼接
                    stringBuilder.append(entry.getKey())
                            .append("=")
                            .append(entry.getValue())
                            .append("&");
                }

                //删掉最后一个&符号
                if (stringBuilder.indexOf("&") != -1) {
                    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
                }

                String newUrl = stringBuilder.toString();//新的路径

                //拿着新的路径重新构建请求
                request = request.newBuilder()
                        .url(newUrl)
                        .build();


            } else if ("POST".equals(method)) {
                //先获取到老的请求的实体内容
                RequestBody oldRequestBody = request.body();//....之前的请求参数,,,需要放到新的请求实体内容中去

                //如果请求调用的是上面doPost方法
                if (oldRequestBody instanceof FormBody) {
                    FormBody oldBody = (FormBody) oldRequestBody;

                    //构建一个新的请求实体内容
                    FormBody.Builder builder = new FormBody.Builder();
                    //1.添加老的参数
                    for (int i = 0; i < oldBody.size(); i++) {
                        builder.add(oldBody.name(i), oldBody.value(i));
                    }
                    //2.添加公共参数
                    for (Map.Entry<String, String> entry : map.entrySet()) {

                        builder.add(entry.getKey(), entry.getValue());
                    }

                    FormBody newBody = builder.build();//新的请求实体内容

                    //构建一个新的请求
                    request = request.newBuilder()
                            .url(oldUrl)
                            .post(newBody)
                            .build();
                }


            }


            Response response = chain.proceed(request);

            return response;
        }
    }

}
