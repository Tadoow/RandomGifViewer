package com.example.myapplication.domain.interactor;

import com.example.myapplication.data.model.RandomImageResponse;
import com.example.myapplication.data.repository.DevelopersLifeRepository;
import com.example.myapplication.domain.model.RandomImageDomain;

import java.io.IOException;

public class DevelopersLifeInteractor {

    private static final String TAG = DevelopersLifeInteractor.class.getSimpleName();

    private final DevelopersLifeRepository mDevelopersLifeRepository;

    public DevelopersLifeInteractor(DevelopersLifeRepository developersLifeRepository) {
        mDevelopersLifeRepository = developersLifeRepository;
    }

    public RandomImageDomain getPrevImage(int id) throws IOException {
        RandomImageResponse image = mDevelopersLifeRepository.getPreviousImage(id);
        return new RandomImageDomain(image.getId(), image.getDescription(), image.getGifUrl());
    }

    public RandomImageDomain getNextImage(int id) throws IOException {
        RandomImageResponse image = mDevelopersLifeRepository.getNextImage(id);
        return new RandomImageDomain(image.getId(), image.getDescription(), image.getGifUrl());
    }
}
