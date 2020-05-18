package io.namoosori.travelclub.phase7.spec.util.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object obj){
        String json = gson.toJson(obj);
        return json;
    }

    public static<T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

    public static<T> List<T> fromJsonList(String json,Class<T> classOfT){
        return gson.fromJson(json,new TypeToken<List<T>>(){}.getType());
    }
}
