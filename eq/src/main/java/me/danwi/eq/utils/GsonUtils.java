package me.danwi.eq.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import me.danwi.eq.ParameterizedTypeImpl;
import me.danwi.eq.entity.HttpResult;

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

    // 解析data是object的情况
    public static <T> HttpResult<T> fromJsonObject(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(HttpResult.class, new Class[]{clazz});
        return gson.fromJson(json, type);
    }

    // 解析data是array的情况
    public static <T> HttpResult<List<T>> fromJsonArray(String json, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(HttpResult.class, new Type[]{listType});
        return gson.fromJson(json, type);
    }
}
