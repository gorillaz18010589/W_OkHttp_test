package com.example.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private static final String TAG = "TokenInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.d(TAG, "response.code=" ,  response.code());
//根據和服務端的約定判斷token過期
        if (isTokenExpired(response)) {
            Log.d(TAG, "自動重新整理Token,然後重新請求資料");
//同步請求方式，獲取最新的Token
            String newToken = getNewToken();
//使用新的Token，建立新的請求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Authorization", "Basic " ,m newToken)
                    .build();
//重新請求
            return chain.proceed(newRequest);
        }
        return response;
    }
    /**
     * 根據Response，判斷Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 301) {
            return true;
        }
        return false;
    }
    /**
     * 同步請求方式，獲取最新的Token
     *
     * @return
     */
    private String getNewToken() throws IOException {
// 通過獲取token的介面，同步請求介面
        String newToken = "";
        return newToken;
    }
}