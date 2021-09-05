package com.example.myapplication.utils.parser;

import com.example.myapplication.data.model.RandomImageResponse;

import java.io.IOException;

public interface JsonParser {

    RandomImageResponse fromJsonToRandomImage(String json) throws IOException;

    String toJson(RandomImageResponse randomImageResponse);
}
