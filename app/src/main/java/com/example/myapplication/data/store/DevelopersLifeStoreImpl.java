package com.example.myapplication.data.store;

import android.content.SharedPreferences;

import com.example.myapplication.data.model.RandomImageResponse;
import com.example.myapplication.utils.parser.JsonParser;

import java.io.IOException;

public class DevelopersLifeStoreImpl implements DevelopersLifeStore {

    private final SharedPreferences mPreferences;
    private final JsonParser mParser;

    public DevelopersLifeStoreImpl(SharedPreferences preferences, JsonParser parser) {
        mPreferences = preferences;
        mParser = parser;
    }

    @Override
    public void saveImage(RandomImageResponse randomImageResponse) {
        mPreferences.edit()
                .putString(String.valueOf(randomImageResponse.getId()), mParser.toJson(randomImageResponse))
                .apply();
    }

    @Override
    public RandomImageResponse getSavedImage(int id) throws IOException {
        String cache = mPreferences.getString(String.valueOf(id), null);
        return cache != null ? mParser.fromJsonToRandomImage(cache) : null;
    }
}
