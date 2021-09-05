package com.example.myapplication.utils.parser;

import com.example.myapplication.data.model.RandomImageResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class JsonParserImpl implements JsonParser {

    @Override
    public RandomImageResponse fromJsonToRandomImage(String json) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<RandomImageResponse> adapter = moshi.adapter(RandomImageResponse.class);
        return adapter.fromJson(json);
    }

    @Override
    public String toJson(RandomImageResponse randomImageResponse) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<RandomImageResponse> adapter = moshi.adapter(RandomImageResponse.class);
        return adapter.toJson(randomImageResponse);
    }
}
