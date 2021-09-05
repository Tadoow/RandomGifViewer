package com.example.myapplication.domain.model;

public class RandomImageDomain {

    private final int mId;
    private final String mDescription;
    private final String mGifUrl;

    public RandomImageDomain(int id, String description, String gifUrl) {
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
