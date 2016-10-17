package me.danwi.eq.utils;

import com.google.gson.Gson;

/**
 * Created by RunningSnail on 16/7/8.
 */
public class GsonUtils {
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T toBean(String bean, Class<T> t) {
        return gson.fromJson(bean, t);
    }
}
