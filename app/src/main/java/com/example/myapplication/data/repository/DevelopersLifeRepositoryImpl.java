package com.example.myapplication.data.repository;

import com.example.myapplication.data.api.DevelopersLifeApi;
import com.example.myapplication.data.model.RandomImageResponse;
import com.example.myapplication.data.store.DevelopersLifeStore;

import java.io.IOException;

public class DevelopersLifeRepositoryImpl implements DevelopersLifeRepository {

    private final DevelopersLifeApi mDevelopersLifeApi;
    private final DevelopersLifeStore mDevelopersLifeStore;

    public DevelopersLifeRepositoryImpl(DevelopersLifeApi developersLifeApi, DevelopersLifeStore developersLifeStore) {
        mDevelopersLifeApi = developersLifeApi;
        mDevelopersLifeStore = developersLifeStore;
    }

    @Override
    public RandomImageResponse getRandomImage() throws IOException {
        RandomImageResponse randomImageResponse = mDevelopersLifeApi.getRandomImage();
        if (mDevelopersLifeStore.getSavedImage(randomImageResponse.getId()) == null) {
            mDevelopersLifeStore.saveImage(randomImageResponse);
        }
        return randomImageResponse;
    }

    @Override
    public RandomImageResponse getPreviousImage(int id) throws IOException {
        return mDevelopersLifeStore.getPreviousImage(id);
    }

    @Override
    public RandomImageResponse getNextImage(int id) throws IOException {
        if (mDevelopersLifeStore.getNextImage(id) != null) {
            return mDevelopersLifeStore.getNextImage(id);
        } else {
            return getRandomImage();
        }
    }
}
