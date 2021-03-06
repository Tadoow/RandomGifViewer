package com.example.myapplication.data.repository;

import com.example.myapplication.data.model.RandomImageResponse;

import java.io.IOException;

public interface DevelopersLifeRepository {

    RandomImageResponse getRandomImage() throws IOException;

    RandomImageResponse getPreviousImage(int id) throws IOException;

    RandomImageResponse getNextImage(int id) throws IOException;
}
