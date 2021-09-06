package com.example.myapplication.data.store;

import android.content.SharedPreferences;

import com.example.myapplication.data.model.RandomImageResponse;
import com.example.myapplication.utils.parser.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersLifeStoreImpl implements DevelopersLifeStore {

    private final SharedPreferences mPreferences;
    private final JsonParser mParser;
    private final List<Integer> mIds;

    public DevelopersLifeStoreImpl(SharedPreferences preferences, JsonParser parser) {
        mPreferences = preferences;
        mParser = parser;
        mIds = new ArrayList<>();
    }

    @Override
    public void saveImage(RandomImageResponse randomImageResponse) {
        mPreferences.edit()
                .putString(String.valueOf(randomImageResponse.getId()), mParser.toJson(randomImageResponse))
                .apply();
        mIds.add(randomImageResponse.getId());
    }

    @Override
    public RandomImageResponse getSavedImage(int id) throws IOException {
        String cache = mPreferences.getString(String.valueOf(id), null);
        return cache != null ? mParser.fromJsonToRandomImage(cache) : null;
    }

    @Override
    public RandomImageResponse getPreviousImage(int id) throws IOException {
        if (mIds.indexOf(id) - 1 >= 0) {
            String key = String.valueOf(mIds.get(mIds.indexOf(id) - 1));
            String cache = mPreferences.getString(key, null);
            return cache != null ? mParser.fromJsonToRandomImage(cache) : null;
        }
        return null;
    }

    @Override
    public RandomImageResponse getNextImage(int id) throws IOException {
        if (mIds.indexOf(id) + 1 < mIds.size()) {
            String key = String.valueOf(mIds.get(mIds.indexOf(id) + 1));
            String cache = mPreferences.getString(key, null);
            return cache != null ? mParser.fromJsonToRandomImage(cache) : null;
        }
        return null;
    }
}
