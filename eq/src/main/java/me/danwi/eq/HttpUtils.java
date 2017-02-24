package me.danwi.eq;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import me.danwi.eq.entity.Param;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/7/27
 * Time: 下午4:04
 * <p>
 * 文件上传辅助类,避免麻烦的参数拼接
 */
public class HttpUtils {

    private HttpUtils() {

    }

    /**
     * 单个或多个文件上传,可以携带其他参数
     * 当value和filePath都包含值时,优先级:非文本(普通键值对),本地文件,字节数组
     *
     * @param paramList
     * @return
     */
    public static Map<String, RequestBody> combine(Param... paramList) {
        Map<String, RequestBody> params = new HashMap<>();
        if (paramList != null) {
            for (Param param : paramList) {
                RequestBody requestBody;
                if (param.value != null) {
                    requestBody = RequestBody.create(null, param.value);
                    //普通键值对添加
                    params.put(param.key, requestBody);
                } else {
                    if (param.filePath != null) {
                        requestBody = RequestBody.create(MultipartBody.FORM, new File(param.filePath));
                    } else {
                        requestBody = RequestBody.create(MultipartBody.FORM, param.bytes);
                    }
                    //包含文件
                    params.put(param.key + "\";fileName=" + param.fileName, requestBody);
                }
            }
        }
        return params;
    }

}
