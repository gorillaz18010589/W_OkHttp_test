package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String url = "https://jsonplaceholder.typicode.com/posts";
    private Gson gson;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            doPostRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void sendRequestWithOkHttp() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                JSONObject obj = new JSONObject();
//                try {
//                    obj.put("userId","3");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                MediaType type = MediaType.parse("application/json;charset=utf-8");
//                RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            // 指定访问的服务器地址
//                            .url("https://jsonplaceholder.typicode.com/posts").post(RequestBody2)
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    Gson gson = new Gson();
//                    String data = gson.toJson(responseData);
//                    String str = response.body().string();
//                    Log.v("hank","data:" + response.body().string());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    private void doPostRequest() throws IOException {
        //1.帶入鑰給的參數
        String url = "https://jsonplaceholder.typicode.com/posts";
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse(" application/json;charset=utf-8");


//        Gson gson = new Gson();
//        Map<String,String> map  = new HashMap<>();
//        map.put("userId","5");
//        gson.toJson(map);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "25");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //2.準備post.body帶入
        final RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //3.response回來
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {


//                    Gson gson1 = new Gson();
//                    String body = gson1.toJson(response.body());
                    String data = response.body().string();
                    Log.v("hank", "Response:" + data);
                }
            }
        });
    }


//    private void v (){
//        OkHttpClient okHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url(url).build();
//
//        gson = new Gson();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                String body = gson.toJson(response.body());
//
//                Log.v("hank","Response:" + body);
//            }
//        });
//    }
}



