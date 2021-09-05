package com.example.myapplication.data.model;

import com.squareup.moshi.Json;

public class RandomImageResponse {

    @Json(name = "id")
    private final int mId;
    @Json(name = "description")
    private final String mDescription;
    @Json(name = "gifURL")
    private final String mGifUrl;

    public RandomImageResponse(int id, String description, String gifUrl) {
        mId = id;
        mDescription = description;
        mGifUrl = gifUrl;
    }

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getGifUrl() {
        return mGifUrl;
    }
}
