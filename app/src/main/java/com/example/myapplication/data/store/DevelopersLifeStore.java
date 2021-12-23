package com.example.myapplication.data.store;

import com.example.myapplication.data.model.RandomImageResponse;

import java.io.IOException;

public interface DevelopersLifeStore {

    void saveImage(RandomImageResponse randomImageResponse);

    RandomImageResponse getSavedImage(int id) throws IOException;

    RandomImageResponse getPreviousImage(int id) throws IOException;

    RandomImageResponse getNextImage(int id) throws IOException;
}
