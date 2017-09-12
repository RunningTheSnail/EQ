package me.danwi.eq;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/9/12
 * Time: 上午11:30
 */

//自定义解析器
public class BaseConvertFactory extends Converter.Factory {
    @Override
    public Converter<okhttp3.ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //遍历方法上面的注解
        for (Annotation annotation : annotations) {
            if (annotation instanceof EQ) {
                return null;
            }
        }
        return null;
    }

    //非Retrofit不会解析,所以目测该接口不会被调用
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return null;
    }

    public static class BaseConvert<T> implements Converter<ResponseBody, T> {

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            return null;
        }
    }
}
