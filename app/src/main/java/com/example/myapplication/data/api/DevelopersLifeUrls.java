package com.example.myapplication.data.api;

public enum DevelopersLifeUrls {

    RANDOM("https://developerslife.ru/random?json=true");

    private final String mUrl;

    DevelopersLifeUrls(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }
}
