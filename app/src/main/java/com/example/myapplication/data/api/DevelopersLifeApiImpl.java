package com.example.myapplication.data.api;

import android.util.Log;

import com.example.myapplication.data.model.RandomImageResponse;
import com.example.myapplication.utils.parser.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DevelopersLifeApiImpl implements DevelopersLifeApi {

    private static final String TAG = DevelopersLifeApiImpl.class.getSimpleName();

    private final OkHttpClient mHttpClient;
    private final JsonParser mParser;

    public DevelopersLifeApiImpl(OkHttpClient httpClient, JsonParser parser) {
        mHttpClient = httpClient;
        mParser = parser;
    }

    private Request createGetRequest(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

    @Override
    public RandomImageResponse getRandomImage() {
        Request request = createGetRequest(DevelopersLifeUrls.RANDOM.getUrl());
        try (Response response = mHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                return body != null ? mParser.fromJsonToRandomImage(body.string()) : null;
            } else {
                Log.d(TAG, "Response code: " + response.code());
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Request failed: ", e);
            return null;
        }
    }
}
