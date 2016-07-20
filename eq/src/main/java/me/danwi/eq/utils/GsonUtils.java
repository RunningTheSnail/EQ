package me.danwi.eq.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

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

    public static <T> List<T> toListBean(String listBean) {
        return gson.fromJson(listBean, new TypeToken<List<T>>() {
        }.getType());
    }
}
