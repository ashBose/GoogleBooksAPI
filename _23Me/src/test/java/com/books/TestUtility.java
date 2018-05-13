package com.books;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestUtility {

    public static JsonObject getJsonObject(String json) {
        JsonElement element = new Gson().fromJson (json, JsonElement.class);
        return element.getAsJsonObject();
    }
}
